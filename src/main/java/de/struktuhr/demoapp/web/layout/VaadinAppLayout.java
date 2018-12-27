package de.struktuhr.demoapp.web.layout;

import com.vaadin.flow.component.applayout.AbstractAppRouterLayout;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayoutMenu;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;

@Theme(Material.class)
@Push
public class VaadinAppLayout extends AbstractAppRouterLayout {

    @Override
    protected void configure(AppLayout appLayout, AppLayoutMenu appLayoutMenu) {
        appLayout.setBranding(new H3("Vaadin App Layout"));

        appLayoutMenu.addMenuItem(VaadinIcon.HOME.create(), "Home", "");
        appLayoutMenu.addMenuItem(VaadinIcon.TABLE.create(), "Customers", "customers");

        appLayoutMenu.addMenuItem(VaadinIcon.DESKTOP.create(), "Demo", "demo");
        appLayoutMenu.addMenuItem(VaadinIcon.PICTURE.create(), "Images", "images");
        appLayoutMenu.addMenuItem(VaadinIcon.GLOBE.create(), "Beans", "beans");
    }
}
