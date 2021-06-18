package app.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class OverviewTestsUI implements Initializable {

    private App mainApp;

    private LabCoordMenuUI parent;

    @FXML
    private Button backBtn;

    @FXML
    private Label titleLabel;

    @FXML
    private TextArea outputTextArea;

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    public void setParent(LabCoordMenuUI parent) {
        this.parent = parent;
    }

    @FXML
    void backScene(ActionEvent event) {
        parent.toLabCoordMenu();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

