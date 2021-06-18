package app.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ClinicalChemistryTechnologistMenuUI {
    App mainApp;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClientsTests;

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void btnBack(ActionEvent event) {
        this.mainApp.toMainScene();
    }

    @FXML
    void toShowClientListScene(ActionEvent event) {
        try {
            ShowClientListUI showClientListUI = (ShowClientListUI) this.mainApp.replaceSceneContent("/fxml/ShowClientList.fxml");
            showClientListUI.setMainApp(this.mainApp);
            showClientListUI.setParent(this);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
