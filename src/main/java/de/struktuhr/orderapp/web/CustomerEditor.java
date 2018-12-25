package de.struktuhr.orderapp.web;

import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToBigDecimalConverter;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import org.springframework.beans.factory.annotation.Autowired;

import de.struktuhr.orderapp.entity.Customer;
import de.struktuhr.orderapp.repo.CustomerRepository;

@SpringComponent
@UIScope
public class CustomerEditor extends VerticalLayout implements KeyNotifier {
    
    private final CustomerRepository repo;


    private Customer customer;

    // Fields to edit
    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    Checkbox manager = new Checkbox("Manager");
    DatePicker birthday = new DatePicker("Birthday");
    ComboBox<String> salutation = new ComboBox<>("Salutation", "Mr.", "Mrs.", "Ms.");
    TextField salary = new TextField("Salary");

    // Actions
    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel", VaadinIcon.UNLOCK.create());
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<Customer> binder = new Binder<>(Customer.class);

    private ChangeHandler changeHandler;

    @Autowired
    public CustomerEditor(CustomerRepository repo) {
        this.repo = repo;

        add(firstName, lastName, manager, birthday, salutation, salary, actions);

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

        // configure and sytle components
        setSpacing(true);

        //this.setResponsiveSteps(
        //        new ResponsiveStep("0", 1),
        //        new ResponsiveStep("21em", 2),
        //        new ResponsiveStep("22em", 3));

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        // wire actions
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editCustomer(customer));
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
        cancel.setVisible(persistent);

        binder.readBean(customer);

        setVisible(true);

        firstName.focus();

    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }
}