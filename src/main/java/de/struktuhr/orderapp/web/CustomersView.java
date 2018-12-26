package de.struktuhr.orderapp.web;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import de.struktuhr.orderapp.control.CalculationService;
import de.struktuhr.orderapp.entity.Customer;
import de.struktuhr.orderapp.repo.CustomerRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Route("customers")
public class CustomersView extends VerticalLayout {

    private final static Logger log = LoggerFactory.getLogger(CustomersView.class);

    private final CustomerRepository repo;
    private final CustomerEditor editor;
    private final CalculationService calculationService;
    private final Grid<Customer> grid;
    private final TextField filter;
    private final Button addNewBtn;
    private LookupThread lookupThread;
    private final Label infoLabel;


    public CustomersView(CustomerRepository repo, CustomerEditor editor, CalculationService calculationService) {
        this.repo = repo;
        this.editor = editor;
        this.calculationService = calculationService;
        this.grid = new Grid<>(Customer.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New Customer", VaadinIcon.PLUS.create());


        Button calcButton = new Button("Start Calculation", VaadinIcon.CALC.create());
        calcButton.addClickListener(click -> calculationService.runCalculation(UUID.randomUUID().toString()));

        infoLabel = new Label();

        // Build layout
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn, calcButton);
        add(actions, grid, editor, infoLabel);

        grid.setHeightByRows(true);
        grid.setColumns("id", "firstName", "lastName", "manager", "birthday", "salutation", "salary");
        grid.addComponentColumn((customer) -> {
            Image image = new Image(customer.getImageUrl(), "Alt Text");
            image.setHeight("64px");
            image.setWidth("64px");
            image.setTitle(customer.getFirstName());
            return image;
        }).setHeader("Image");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        filter.setPlaceholder("Filter by last name");

        // Hook logic
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listCustomers(e.getValue()));

        grid.asSingleSelect().addValueChangeListener(e -> editor.editCustomer(e.getValue()));

        addNewBtn.addClickListener(e -> editor.editCustomer(new Customer("", "", false, LocalDate.now(), "Mr.", BigDecimal.ZERO)));

        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listCustomers(filter.getValue());
        });

        // Initialize listing
        listCustomers(null);
    }

    private static class LookupThread extends Thread {
        private final UI ui;
        private final CustomersView view;
        private final CalculationService calculationService;

        public LookupThread(UI ui, CustomersView view, CalculationService calculationService) {
            this.ui = ui;
            this.view = view;
            this.calculationService = calculationService;
        }

        @Override
        public void run() {
            try {
                // Update the data for a while
                while (true) {
                    // Sleep to emulate background work
                    Thread.sleep(500);

                    Map<String, LocalDateTime> results = calculationService.getResults();
                    if (!results.isEmpty()) {
                        ui.access(() -> view.updateResults(results));
                    }
                }
            }
            catch (InterruptedException e) {
                log.info("Lookup Thread interrupted");
            }
        }
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        lookupThread = new LookupThread(attachEvent.getUI(), this, calculationService);
        lookupThread.start();
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        super.onDetach(detachEvent);
        lookupThread.interrupt();
        lookupThread = null;
    }

    public void updateResults(Map<String, LocalDateTime> results ) {
        log.info("results = {}", results);

        infoLabel.setText(results.toString());
    }

    private void listCustomers(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(repo.findAll());
        }
        else {
            grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText));
        }
    }
}
