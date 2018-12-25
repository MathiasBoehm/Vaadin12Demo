package de.struktuhr.orderapp.beans;

import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@UIScope
public class UIBean {

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
