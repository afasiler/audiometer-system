package com.audiometer.ui;

import com.audiometer.i18n.I18nManager;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Locale;

import javafx.scene.shape.Circle;

public class MainController {

    @FXML private Label titleLabel;
    @FXML private Label connectionStatusLabel;
    @FXML private Label testStatusLabel;
    @FXML private Circle connectionStatusDot;
    @FXML private Button btnTr;
    @FXML private Button btnEn;

    @FXML
    public void initialize() {
        I18nManager i18n = I18nManager.getInstance();
        
        // Bind basic texts
        i18n.bind(titleLabel, "app.title");
        
        i18n.bind(connectionStatusLabel, "status.disconnected");
        
        // Listen to service connection status
        com.audiometer.service.ServiceRegistry.serialService.addOnConnectionStatusChanged(status -> {
            javafx.application.Platform.runLater(() -> {
                if (status == com.audiometer.model.enums.ConnectionStatus.CONNECTED) {
                    connectionStatusLabel.textProperty().bind(i18n.createStringBinding("status.connected"));
                    connectionStatusDot.getStyleClass().remove("status-indicator-red");
                    if (!connectionStatusDot.getStyleClass().contains("status-indicator-green")) {
                        connectionStatusDot.getStyleClass().add("status-indicator-green");
                    }
                } else if (status == com.audiometer.model.enums.ConnectionStatus.CONNECTING) {
                    connectionStatusLabel.textProperty().bind(i18n.createStringBinding("btn.connecting"));
                } else {
                    connectionStatusLabel.textProperty().bind(i18n.createStringBinding("status.disconnected"));
                    connectionStatusDot.getStyleClass().remove("status-indicator-green");
                    if (!connectionStatusDot.getStyleClass().contains("status-indicator-red")) {
                        connectionStatusDot.getStyleClass().add("status-indicator-red");
                    }
                }
            });
        });
        
        // Complex bindings (combining static text with translated string)
        testStatusLabel.textProperty().bind(Bindings.createStringBinding(
            () -> i18n.get("status.testPrefix") + " " + i18n.get("status.idle"), 
            i18n.bundleProperty()
        ));
        
        // Setup language toggle actions
        btnTr.setOnAction(e -> setLanguage("tr"));
        btnEn.setOnAction(e -> setLanguage("en"));
        
        // Initial setup and listener for button styles
        updateToggleButtons();
        i18n.bundleProperty().addListener((obs, oldV, newV) -> updateToggleButtons());
    }
    
    private void setLanguage(String lang) {
        I18nManager.getInstance().setLocale(Locale.of(lang));
    }
    
    private void updateToggleButtons() {
        String lang = I18nManager.getInstance().getCurrentLocale().getLanguage();
        if ("tr".equals(lang)) {
            if (!btnTr.getStyleClass().contains("toggle-btn-selected")) {
                btnTr.getStyleClass().add("toggle-btn-selected");
            }
            btnEn.getStyleClass().remove("toggle-btn-selected");
        } else {
            if (!btnEn.getStyleClass().contains("toggle-btn-selected")) {
                btnEn.getStyleClass().add("toggle-btn-selected");
            }
            btnTr.getStyleClass().remove("toggle-btn-selected");
        }
    }
}
