package org.example.com;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Info {
    private final ResourceBundle messages;

    public Info(ResourceBundle messages) {
        this.messages = messages;
    }

    public void display(Locale locale) {
        try {
            System.out.println(messages.getString("info").replace("{0}", locale.getDisplayName()));
            System.out.println("Country: " + locale.getDisplayCountry());
            System.out.println("Language: " + locale.getDisplayLanguage());

            try {
                System.out.println("Currency: " + Currency.getInstance(locale).getDisplayName());
            } catch (Exception e) {
                System.out.println("Currency: N/A");
            }

            try {
                String[] weekdays = new DateFormatSymbols(locale).getWeekdays();
                System.out.println("Week Days: " + String.join(", ", weekdays));
            } catch (Exception e) {
                System.out.println("Week Days: N/A");
            }

            try {
                String[] months = new DateFormatSymbols(locale).getMonths();
                System.out.println("Months: " + String.join(", ", months));
            } catch (Exception e) {
                System.out.println("Months: N/A");
            }

            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, locale);
            String today = dateFormat.format(new Date());
            System.out.println("Today: " + today);
        } catch (Exception e) {
            System.out.println("An error occurred while displaying locale information: " + e.getMessage());
        }
    }
}
