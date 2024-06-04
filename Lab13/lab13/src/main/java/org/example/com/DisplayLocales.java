package org.example.com;

import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayLocales {
    private final ResourceBundle messages;

    public DisplayLocales(ResourceBundle messages) {
        this.messages = messages;
    }

    public void display() {
        Locale[] locales = Locale.getAvailableLocales();
        System.out.println(messages.getString("locales"));
        for (Locale locale : locales) {
            System.out.println(locale.getDisplayName());
        }
    }
}
