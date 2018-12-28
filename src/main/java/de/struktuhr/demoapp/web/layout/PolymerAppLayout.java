package de.struktuhr.demoapp.web.layout;

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
import de.struktuhr.demoapp.web.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.appreciated.app.layout.entity.Section.HEADER;

//@Theme(Material.class) // Material Design seems to clash with App Layout
@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class PolymerAppLayout extends AppLayoutRouterLayout {

    private final String VERSION = "1.0.0";

    private final static Logger log = LoggerFactory.getLogger(PolymerAppLayout.class);

    private DefaultNotificationHolder notifications;

    private DefaultBadgeHolder badge;


    @Override
    public AppLayout createAppLayoutInstance() {

        log.info("createAppLayoutInstance locale = {}", getLocale());

        notifications = new DefaultNotificationHolder(newStatus -> {
        });
        badge = new DefaultBadgeHolder();

        return AppLayoutBuilder
                .get(Behaviour.LEFT_HYBRID)
                .withTitle(getTranslation("app.title"))
                .withAppBar(AppBarBuilder
                        .get()
                        .add(new AppBarNotificationButton(VaadinIcon.BELL, notifications))
                        .build())
                .withAppMenu(LeftAppMenuBuilder
                        .get()
                        .addToSection(new MenuHeaderComponent(getTranslation("nav.header.title"),
                                getTranslation("nav.header.version", getVersion()),
                                "/frontend/images/logo.png"
                        ), HEADER)
                        .add(new LeftNavigationComponent(getTranslation("nav.home"), VaadinIcon.HOME.create(), MainView.class))
                        .add(new LeftNavigationComponent(getTranslation("nav.customers"), VaadinIcon.TABLE.create(), CustomersView.class))
                        .add(LeftSubMenuBuilder
                                .get(getTranslation("nav.submenu.1"), VaadinIcon.PLUS.create())
                                .add(new LeftNavigationComponent(getTranslation("nav.demo"), VaadinIcon.DESKTOP.create(), DemoView.class))
                                .add(new LeftNavigationComponent(getTranslation("nav.images"), VaadinIcon.PICTURE.create(), ImageView.class))
                                .build())
                        .add(LeftSubMenuBuilder
                                .get(getTranslation("nav.submenu.2"), VaadinIcon.PLUS.create())
                                .add(new LeftNavigationComponent(getTranslation("nav.beans"), VaadinIcon.GLOBE.create(), ShowBeansView.class))
                                .build())
                        .build())
                .build();

    }


    private String getVersion() {
        return VERSION;
    }

}