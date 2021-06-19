package app.ui.gui;

import app.ui.console.ValidateTestUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LabCoordMenuUI implements Initializable {
    private App mainApp;

    @FXML
    private Button importCSVFileBtn;

    @FXML
    private Button overviewTestsBtn;

    @FXML
    private Button validateResults;

    @FXML
    private Button backBtn;

    @FXML
    void importCSVFileMenu(ActionEvent event) {
        try {
            ImportCSVFileUI importCSVFileUI = (ImportCSVFileUI) this.mainApp.replaceSceneContent("/fxml/ImportCSVFileScene.fxml");
            importCSVFileUI.setMainApp(this.mainApp);
            importCSVFileUI.setParent(this);
        } catch (Exception ex) {
            Logger.getLogger(app.ui.gui.App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void overviewTestsMenu(ActionEvent event) {
        try {
            OverviewTestsUI overviewTestsUI = (OverviewTestsUI) this.mainApp.replaceSceneContent("/fxml/OverviewTestsScene.fxml");
            overviewTestsUI.setMainApp(this.mainApp);
            overviewTestsUI.setParent(this);
        } catch (Exception ex) {
            Logger.getLogger(app.ui.gui.App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    public void toLabCoordMenu() {
        try {
            LabCoordMenuUI labCoordMenuUI = (LabCoordMenuUI) mainApp.replaceSceneContent("/fxml/LabCoordMenuScene.fxml");
            labCoordMenuUI.setMainApp(this.mainApp);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void validateResults(ActionEvent event) {
        mainApp.getStage().setIconified(true);
        new ValidateTestUI().run();
    }

    @FXML
    void backScene(ActionEvent event) {
        this.mainApp.toMainScene();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //empty
    }
}
