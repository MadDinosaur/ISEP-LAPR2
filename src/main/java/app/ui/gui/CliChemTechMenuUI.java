package app.ui.gui;

import app.ui.console.RecordTestResultUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CliChemTechMenuUI implements Initializable {
    App mainApp;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnShowClientList;

    @FXML
    private Button btnRecordTestResults;

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void btnBack(ActionEvent event) {
        this.mainApp.toMainScene();
    }

    @FXML
    void showClientListScene(ActionEvent event) {
        try {
            ShowClientListUI showClientListUI = (ShowClientListUI) this.mainApp.replaceSceneContent("/fxml/ShowClientList.fxml");
            showClientListUI.setMainApp(this.mainApp);
            showClientListUI.setParent(this);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void toCliChemTechMenu() {
        try {
            CliChemTechMenuUI cliChemTechMenuUI = (CliChemTechMenuUI) mainApp.replaceSceneContent("/fxml/CliChemTechMenuScene.fxml");
            cliChemTechMenuUI.setMainApp(this.mainApp);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void recordTestResults(ActionEvent event){
        new RecordTestResultUI().run();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //empty
    }
}
