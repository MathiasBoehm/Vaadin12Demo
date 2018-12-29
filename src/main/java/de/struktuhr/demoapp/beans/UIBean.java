package de.struktuhr.demoapp.beans;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import java.io.Serializable;
import java.time.LocalTime;

@SpringComponent
@UIScope
public class UIBean implements Serializable {

    private final String created;
    private String message;

    public UIBean() {
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
