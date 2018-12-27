package de.struktuhr.demoapp.beans;


import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;

import java.time.LocalTime;

@SpringComponent
@VaadinSessionScope
public class SessionBean {

    private final String created;
    private String message;

    public SessionBean() {
        created = "Created at " + LocalTime.now();
        message = "Message from " + LocalTime.now();
    }

    public void updateMessage() {
        message = "Updated at " + LocalTime.now();
    }

    @Override
    public String toString() {
        return created + " -> " + message;
    }
}
