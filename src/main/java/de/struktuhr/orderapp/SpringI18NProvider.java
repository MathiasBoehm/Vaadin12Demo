package de.struktuhr.orderapp;

import com.vaadin.flow.i18n.I18NProvider;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Component
public class SpringI18NProvider implements I18NProvider {

    private MessageSourceAccessor messageSourceAccessor;

    public SpringI18NProvider(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @Override
    public List<Locale> getProvidedLocales() {
        final List<Locale> providedLocales = Collections.unmodifiableList(Arrays.asList(Locale.getAvailableLocales()));
        return providedLocales;
    }

    @Override
    public String getTranslation(String key, Locale locale, Object... params) {
        return messageSourceAccessor.getMessage(key, params, locale);
    }

}
