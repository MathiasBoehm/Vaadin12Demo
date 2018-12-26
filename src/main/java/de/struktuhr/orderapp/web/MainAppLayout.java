package de.struktuhr.orderapp.web;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.appmenu.MenuHeaderComponent;
import com.github.appreciated.app.layout.component.appmenu.left.LeftNavigationComponent;
import com.github.appreciated.app.layout.component.appmenu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.appmenu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.entity.DefaultBadgeHolder;
import com.github.appreciated.app.layout.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.notification.component.AppBarNotificationButton;
import com.github.appreciated.app.layout.router.AppLayoutRouterLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;

import static com.github.appreciated.app.layout.entity.Section.HEADER;

//@Theme(Material.class) // Material Design seems to clash with App Layout
@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class MainAppLayout extends AppLayoutRouterLayout {
    private Behaviour variant;
    private DefaultNotificationHolder notifications;
    private DefaultBadgeHolder badge;


    @Override
    public AppLayout createAppLayoutInstance() {

        notifications = new DefaultNotificationHolder(newStatus -> {});
        badge = new DefaultBadgeHolder();

        return AppLayoutBuilder
                .get(Behaviour.LEFT_HYBRID)
                .withTitle("Vaadin 12 Demo Application")
                .withAppBar(AppBarBuilder
                        .get()
                        .add(new AppBarNotificationButton(VaadinIcon.BELL, notifications))
                        .build())
                .withAppMenu(LeftAppMenuBuilder
                        .get()
                        .addToSection(new MenuHeaderComponent("Section Header",
                                "Version 1.0.0",
                                null
                        ), HEADER)
                        .add(new LeftNavigationComponent("Home", VaadinIcon.HOME.create(), MainView.class))
                        .add(new LeftNavigationComponent("Customers", VaadinIcon.TABLE.create(), CustomersView.class))
                        .add(LeftSubMenuBuilder
                                .get("My Submenu", VaadinIcon.PLUS.create())
                                .add(new LeftNavigationComponent("Demo View", VaadinIcon.CHECK.create(), DemoView.class))
                                .add(new LeftNavigationComponent("Image View", VaadinIcon.FILE_PICTURE.create(), ImageView.class))
                                .build())
                        .build())
                .build();

    }

}