package de.struktuhr.demoapp;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.datepicker.DatePicker;

import java.text.DateFormatSymbols;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Utility class in order to localize a Vaadin Date Picker
 */
public class DatePickerUtils {


    /**
     * Prepare I18N for a given DatePicker using the locale of the UI-instance
     * @param datePicker DatePicker
     */
    public static void prepareI18N(DatePicker datePicker) {
        final Locale locale = UI.getCurrent() != null ? UI.getCurrent().getLocale() : null;
        prepareI18N(datePicker, locale);
    }

    /**
     * Prepare I18N for a given DatePicker and locale
     * @param datePicker DatePicker
     * @param locale Locale
     */
    public static void prepareI18N(DatePicker datePicker, Locale locale)  {
        Objects.requireNonNull(datePicker, "DatePicker must not be null");
        Objects.requireNonNull(locale, "locale must not be null");


        final DatePicker.DatePickerI18n c = new DatePicker.DatePickerI18n();

        // Java Weekdays are one-based, but underlying json structure uses a zero-based index
        final Calendar cal = Calendar.getInstance(locale);
        c.setFirstDayOfWeek(cal.getFirstDayOfWeek() - 1);

        // Months and weeks contains empty elements. Remove them
        final DateFormatSymbols dfs = DateFormatSymbols.getInstance(locale);
        c.setMonthNames(filterEmpty(dfs.getMonths()));
        c.setWeekdays(filterEmpty(dfs.getWeekdays()));
        c.setWeekdaysShort(filterEmpty(dfs.getShortWeekdays()));

        // Localize strings
        final ResourceBundle bundle = ResourceBundle.getBundle("nls/datepicker", locale,
                ResourceBundle.Control.getNoFallbackControl(ResourceBundle.Control.FORMAT_DEFAULT));

        c.setToday(bundle.getString("datepicker.today"));
        c.setClear(bundle.getString("datepicker.clear"));
        c.setCancel(bundle.getString("datepicker.cancel"));
        c.setCalendar(bundle.getString("datepicker.calendar"));
        c.setWeek(bundle.getString("datepicker.week"));

        datePicker.setI18n(c);
    }

    /**
     * Filter empty elements of a given array
     * @param arr Array with elements
     * @return List of non-empty elements
     */
    private static List<String> filterEmpty(String[] arr) {
        return Arrays.asList(arr)
                .stream()
                .filter(s -> s != null && s.trim().length() > 0)
                .collect(Collectors.toList());
    }
}
