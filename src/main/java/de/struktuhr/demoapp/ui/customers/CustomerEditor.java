package de.struktuhr.demoapp.ui.customers;

import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToBigDecimalConverter;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import de.struktuhr.demoapp.DatePickerUtils;
import org.springframework.beans.factory.annotation.Autowired;

import de.struktuhr.demoapp.entity.Customer;
import de.struktuhr.demoapp.repo.CustomerRepository;

@SpringComponent
@UIScope
public class CustomerEditor extends VerticalLayout implements KeyNotifier {
    
    private final CustomerRepository repo;

    private Customer customer;

    private Binder<Customer> binder;

    private ChangeHandler changeHandler;

    @Autowired
    public CustomerEditor(CustomerRepository repo) {
        this.repo = repo;

        // Fields to edit
        TextField firstName = new TextField("First name");
        TextField lastName = new TextField("Last name");
        Checkbox manager = new Checkbox("Manager");
        DatePicker birthday = new DatePicker("Birthday");
        DatePickerUtils.prepareI18N(birthday);

        ComboBox<String> salutation = new ComboBox<>("Salutation", "Mr.", "Mrs.", "Ms.");
        TextField salary = new TextField("Salary");
        TextField imageUrl = new TextField("Image URL");
        imageUrl.setWidth("30em");

        // Actions
        Button save = new Button("Save", VaadinIcon.CHECK.create());
        Button cancel = new Button("Cancel", VaadinIcon.UNLOCK.create());
        Button delete = new Button("Delete", VaadinIcon.TRASH.create());
        HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

        binder = new Binder<>(Customer.class);

        FormLayout form = new FormLayout();
        form.add(firstName, lastName, manager, birthday, salutation, salary, imageUrl);

        form.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("21em", 2),
                new FormLayout.ResponsiveStep("22em", 3));


        add(form, actions);

        // bind using naming conventions
        binder.forField(firstName)
                .asRequired("First name is required")
                .bind(Customer::getFirstName, Customer::setFirstName);

        binder.forField(lastName)
                .asRequired("Last name is required")
                .bind(Customer::getLastName, Customer::setLastName);

        binder.forField(manager).bind(Customer::isManager, Customer::setManager);

        binder.forField(birthday).bind(Customer::getBirthday, Customer::setBirthday);

        binder.forField(salutation).bind(Customer::getSalutation, Customer::setSalutation);

        binder.forField(salary)
                .withConverter(new StringToBigDecimalConverter("Keine Umwandlung in Big Decimal möglich"))
                .bind(Customer::getSalary, Customer::setSalary);

        binder.forField(imageUrl).bind(Customer::getImageUrl, Customer::setImageUrl);

        // configure and sytle components
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        // wire actions
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> cancel());
        setVisible(false);
    }

    public interface ChangeHandler {
        void onChange();
    }

    void delete() {
        repo.delete(customer);
        changeHandler.onChange();
    }

    void save() {
        try {
            binder.writeBean(customer);
            repo.save(customer);
            changeHandler.onChange();
        }
        catch (ValidationException e) {
            // Invalid Data
        }
    }

    void cancel() {
        customer = null;
        setVisible(false);
    }

    public final void editCustomer(Customer c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        final boolean persistent = c.getId() != null;
        if (persistent) {
            customer = repo.findById(c.getId()).get();        
        }
        else {
            customer = c;
        }

        binder.readBean(customer);

        setVisible(true);

    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }
}