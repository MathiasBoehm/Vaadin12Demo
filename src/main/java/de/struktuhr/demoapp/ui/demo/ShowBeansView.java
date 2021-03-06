package de.struktuhr.demoapp.ui.demo;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import de.struktuhr.demoapp.beans.SessionBean;
import de.struktuhr.demoapp.beans.UIBean;
import de.struktuhr.demoapp.ui.MainAppLayout;

@Route(value = "beans", layout = MainAppLayout.class)
public class ShowBeansView extends VerticalLayout {

    private final UIBean uiBean;
    private final SessionBean sessionBean;

    public ShowBeansView(UIBean uiBean, SessionBean sessionBean) {
        this.uiBean = uiBean;
        this.sessionBean = sessionBean;

        add(new Label("UIBean: " + uiBean.toString()));
        add(new Label("SessionBean: " + sessionBean.toString()));

        uiBean.updateMessage();
        sessionBean.updateMessage();
    }


}
