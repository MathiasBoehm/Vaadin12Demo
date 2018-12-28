package de.struktuhr.demoapp.ui.customers;

import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RoutePrefix;
import com.vaadin.flow.router.RouterLayout;
import de.struktuhr.demoapp.ui.MainAppLayout;


/**
 * Nested routes does not work
 */
@RoutePrefix("cc")
@ParentLayout(MainAppLayout.class)
public class CustomersLayout extends HorizontalLayout implements RouterLayout {

    private VerticalLayout contentArea;

    public CustomersLayout() {

        // Menue
        add(new H1("Customers Menu"));

        // Content
        contentArea = new VerticalLayout();
        add(contentArea);

        setClassName("main-layout");
    }


    @Override
    public void showRouterLayoutContent(HasElement content) {
        if (content != null) {
            contentArea.getElement().removeAllChildren();
            contentArea.getElement().appendChild(content.getElement());
        }
    }
}
