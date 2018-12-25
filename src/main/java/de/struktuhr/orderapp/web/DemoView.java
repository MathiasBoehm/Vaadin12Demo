package de.struktuhr.orderapp.web;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route("view-with-parameter")
public class DemoView extends VerticalLayout implements HasUrlParameter<String>, AfterNavigationObserver {

    private final static Logger log = LoggerFactory.getLogger(DemoView.class);

    private String urlParameter;

    private Label urlParameterLabel;

    public DemoView() {
        log.info("DemoView Constructor");
        Button button = new Button("Back To Main");
        button.addClickListener(click -> UI.getCurrent().navigate(""));

        urlParameterLabel = new Label();
        Label label = new Label("Hello in Demo View");
        add(button, label, urlParameterLabel);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String s) {
        log.info("beforeEvent = {}, parameter = {}", beforeEvent,  s);
        urlParameter = s;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        log.info("afterNavigationEvent = {}", afterNavigationEvent);
        if (urlParameter != null) {
            urlParameterLabel.setText(urlParameter);
        }
    }
}
