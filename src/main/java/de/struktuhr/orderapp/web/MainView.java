package de.struktuhr.orderapp.web;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainAppLayout.class)
public class MainView extends VerticalLayout {


    public MainView()  {

        add(new Label("Main View"));

    }


}