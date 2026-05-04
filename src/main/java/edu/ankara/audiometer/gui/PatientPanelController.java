package edu.ankara.audiometer.gui;

import edu.ankara.audiometer.i18n.I18nManager;
import edu.ankara.audiometer.model.Patient;
import edu.ankara.audiometer.model.TestSession;
import edu.ankara.audiometer.util.Validation;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class PatientPanelController {

    @FXML private Label titleLabel;
    @FXML private Label fullNameLabel;
    @FXML private TextField fullNameField;
    @FXML private Label ageLabel;
    @FXML private TextField ageField;
    @FXML private Label genderLabel;
    @FXML private ComboBox<String> genderCombo;
    @FXML private Label dateLabel;
    @FXML private DatePicker datePicker;
    @FXML private Label audiologistLabel;
    @FXML private TextField audiologistField;
    @FXML private Button saveBtn;

    private TestSession currentSession;

    @FXML
    public void initialize() {
        I18nManager i18n = I18nManager.getInstance();

        // Bind Labels
        i18n.bind(titleLabel, "nav.patient");
        i18n.bind(fullNameLabel, "field.fullName");
        i18n.bind(ageLabel, "field.age");
        i18n.bind(genderLabel, "field.gender");
        i18n.bind(dateLabel, "field.date");
        i18n.bind(audiologistLabel, "field.audiologist");
        i18n.bind(saveBtn, "btn.savePatient");

        // Setup Gender Combo (we store keys, display translated text)
        List<String> genderKeys = Arrays.asList("gender.male", "gender.female", "gender.other");
        genderCombo.setItems(FXCollections.observableArrayList(genderKeys));
        genderCombo.setConverter(new StringConverter<>() {
            @Override
            public String toString(String key) {
                return key == null ? "" : i18n.get(key);
            }

            @Override
            public String fromString(String string) {
                return null; // Not needed
            }
        });
        
        // Force refresh combo items on language change
        i18n.bundleProperty().addListener((obs, oldV, newV) -> {
            String selected = genderCombo.getValue();
            genderCombo.setItems(null);
            genderCombo.setItems(FXCollections.observableArrayList(genderKeys));
            genderCombo.setValue(selected);
        });
        
        genderCombo.getSelectionModel().selectFirst();
        datePicker.setValue(LocalDate.now());

        // Numeric constraint for Age field
        ageField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                ageField.setText(newVal.replaceAll("[^\\d]", ""));
            }
        });

        // Validation logic
        saveBtn.setOnAction(e -> handleSave());
    }

    private void handleSave() {
        boolean valid = true;
        
        // Name validation
        if (Validation.isBlank(fullNameField.getText())) {
            fullNameField.setStyle("-fx-border-color: #EF4444; -fx-border-width: 1px; -fx-border-radius: 6px;");
            valid = false;
        } else {
            fullNameField.setStyle("");
        }

        // Age validation
        int age = 0;
        try {
            age = Integer.parseInt(ageField.getText());
            if (!Validation.isValidAge(age)) throw new NumberFormatException();
            ageField.setStyle("");
        } catch (NumberFormatException e) {
            ageField.setStyle("-fx-border-color: #EF4444; -fx-border-width: 1px; -fx-border-radius: 6px;");
            valid = false;
        }
        
        if (valid) {
            Patient patient = new Patient(
                fullNameField.getText().trim(),
                age,
                genderCombo.getValue(),
                datePicker.getValue(),
                audiologistField.getText().trim()
            );
            
            this.currentSession = new TestSession(patient);
            
            // Visual feedback
            saveBtn.textProperty().unbind();
            saveBtn.setText("✓ " + I18nManager.getInstance().get("btn.savePatient"));
            if (!saveBtn.getStyleClass().contains("bg-emerald-500")) {
                saveBtn.getStyleClass().add("bg-emerald-500");
                saveBtn.getStyleClass().add("text-white");
            }
        }
    }
    
    public TestSession getCurrentSession() {
        return currentSession;
    }
}
