package de.struktuhr.orderapp.web;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import de.struktuhr.orderapp.web.layout.PolymerAppLayout;

@Route(value = "", layout = PolymerAppLayout.class)
public class MainView extends VerticalLayout {


    public MainView()  {

        add(new Label("Main View"));

    }


}