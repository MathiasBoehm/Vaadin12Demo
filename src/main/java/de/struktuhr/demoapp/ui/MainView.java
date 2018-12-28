package de.struktuhr.demoapp.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteData;
import com.vaadin.flow.router.Router;

import java.util.List;

@Route(value = "", layout = MainAppLayout.class)
public class MainView extends VerticalLayout {


    public MainView()  {

        add(new Label("Main View"));

        Router router = UI.getCurrent().getRouter();
        List<RouteData> routes = router.getRoutes();

        routes.forEach(System.out::println);
        System.out.println();
    }


}