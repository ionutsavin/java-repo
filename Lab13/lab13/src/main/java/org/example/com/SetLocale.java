package org.example.com;

import java.util.Locale;
import java.util.ResourceBundle;

public class SetLocale {
    private final ResourceBundle messages;

    public SetLocale(ResourceBundle messages) {
        this.messages = messages;
    }

    public void set(String languageTag) {
        Locale locale = Locale.forLanguageTag(languageTag);
        if (isSupportedLocale(locale)) {
            Locale.setDefault(locale);
            System.out.println(messages.getString("locale.set").replace("{0}", locale.getDisplayName()));
        } else {
            System.out.println(messages.getString("invalid"));
            suggestValidLocales();
        }
    }

    private boolean isSupportedLocale(Locale locale) {
        for (Locale availableLocale : Locale.getAvailableLocales()) {
            if (availableLocale.equals(locale)) {
                return true;
            }
        }
        return false;
    }

    private void suggestValidLocales() {
        System.out.println("Supported locales are:");
        for (Locale availableLocale : Locale.getAvailableLocales()) {
            System.out.println(availableLocale.toLanguageTag() + " (" + availableLocale.getDisplayName() + ")");
        }
    }
}
