package app.ui.gui;

import app.ui.console.RecordTestResultUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CliChemTechMenuUI {
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
            CliChemTechMenuUI cliChemTechMenuUI= (CliChemTechMenuUI) mainApp.replaceSceneContent("/fxml/CliChemTech.fxml");
            cliChemTechMenuUI.setMainApp(this.mainApp);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void recordTestResults(ActionEvent event){
        mainApp.getStage().setIconified(true);
        new RecordTestResultUI().run();
    }
}
