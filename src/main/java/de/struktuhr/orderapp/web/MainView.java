package de.struktuhr.orderapp.web;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;
import de.struktuhr.orderapp.control.CalculationService;
import de.struktuhr.orderapp.repo.CustomerRepository;

@Push
@Theme(Material.class)
@Route
public class MainView extends VerticalLayout {


    public MainView(CustomerRepository repo, CustomerEditor editor, CalculationService calculationService)  {

        Button navCustomersButton = new Button("Customers");
        navCustomersButton.addClickListener(click -> UI.getCurrent().navigate("customers"));


        Button navButton = new Button("Demo");
        navButton.addClickListener(click -> UI.getCurrent().navigate("demo"));

        Button navButton2 = new Button("Demo With Param");
        navButton2.addClickListener(click -> UI.getCurrent().navigate(DemoView.class, "33"));

        // Build layout
        HorizontalLayout actions = new HorizontalLayout(navCustomersButton, navButton, navButton2);
        add(actions);

    }


}