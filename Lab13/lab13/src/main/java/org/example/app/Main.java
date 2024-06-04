package org.example.app;

import org.example.com.DisplayLocales;
import org.example.com.Info;
import org.example.com.SetLocale;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {
    private static ResourceBundle messages;

    private static void loadResourceBundle() {
        Locale locale = Locale.getDefault();
        messages = ResourceBundle.getBundle("res/Messages", locale);
    }

    public static void main(String[] args) {
        loadResourceBundle();

        Scanner scanner = new Scanner(System.in);
        DisplayLocales displayLocales = new DisplayLocales(messages);
        SetLocale setLocale = new SetLocale(messages);
        Info info = new Info(messages);

        System.out.println(messages.getString("prompt"));
        while (true) {
            String command = scanner.nextLine();
            switch (command) {
                case "display":
                    displayLocales.display();
                    break;
                case "set":
                    System.out.println("Enter language tag:");
                    String languageTag = scanner.nextLine();
                    setLocale.set(languageTag);
                    loadResourceBundle();
                    break;
                case "info":
                    info.display(Locale.getDefault());
                    break;
                case "exit":
                    return;
                default:
                    System.out.println(messages.getString("invalid"));
                    break;
            }
        }
    }
}
