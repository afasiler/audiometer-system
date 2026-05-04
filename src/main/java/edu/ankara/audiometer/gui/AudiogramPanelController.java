package edu.ankara.audiometer.gui;

import edu.ankara.audiometer.i18n.I18nManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AudiogramPanelController {
    
    @FXML private Label titleLabel;
    @FXML private Button exportPngBtn;
    @FXML private Button exportPdfBtn;

    @FXML
    public void initialize() {
        I18nManager i18n = I18nManager.getInstance();
        
        i18n.bind(titleLabel, "audiogram.title");
        exportPngBtn.textProperty().bind(i18n.createStringBinding("btn.exportPNG"));
        exportPdfBtn.textProperty().bind(i18n.createStringBinding("btn.exportPDF"));
    }
}
