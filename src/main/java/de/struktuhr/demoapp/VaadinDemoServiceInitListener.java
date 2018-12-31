package de.struktuhr.demoapp;

import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VaadinDemoServiceInitListener implements VaadinServiceInitListener {

    private final Logger log = LoggerFactory.getLogger(VaadinDemoServiceInitListener.class);

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(initEvent -> {
            log.info("A new UI has been initialized");
            initEvent.getUI().addBeforeEnterListener(beforeEnter -> {
               log.info("Before Enter To {}", beforeEnter.getNavigationTarget());
            });
        });
    }
}
