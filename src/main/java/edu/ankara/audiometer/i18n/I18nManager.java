package edu.ankara.audiometer.i18n;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Labeled;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18nManager {

    private static final I18nManager instance = new I18nManager();
    private final ObjectProperty<ResourceBundle> bundleProperty;
    
    private I18nManager() {
        Locale defaultLocale = Locale.of("tr");
        ResourceBundle initialBundle = ResourceBundle.getBundle("i18n.messages", defaultLocale);
        bundleProperty = new SimpleObjectProperty<>(initialBundle);
    }
    
    public static I18nManager getInstance() {
        return instance;
    }
    
    public void setLocale(Locale locale) {
        bundleProperty.set(ResourceBundle.getBundle("i18n.messages", locale));
    }
    
    public String get(String key) {
        return bundleProperty.get().getString(key);
    }
    
    public StringBinding createStringBinding(String key) {
        return Bindings.createStringBinding(() -> get(key), bundleProperty);
    }
    
    public void bind(Labeled labeled, String key) {
        labeled.textProperty().bind(createStringBinding(key));
    }
    
    public Locale getCurrentLocale() {
        return bundleProperty.get().getLocale();
    }
    
    public ObjectProperty<ResourceBundle> bundleProperty() {
        return bundleProperty;
    }
}
