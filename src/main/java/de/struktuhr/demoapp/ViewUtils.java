package de.struktuhr.demoapp;

import com.vaadin.flow.component.UI;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class ViewUtils {


    public static String formatCurrency(BigDecimal value) {
        NumberFormat nf = NumberFormat.getNumberInstance(getLocale());
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        return value != null ? nf.format(value.doubleValue()) : null;
    }

    public static String formatDate(LocalDate date) {
        return date != null
                ? DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(getLocale()).format(date)
                : null;
    }

    private static Locale getLocale() {
        return UI.getCurrent() != null ? UI.getCurrent().getLocale() : Locale.getDefault();
    }
}
