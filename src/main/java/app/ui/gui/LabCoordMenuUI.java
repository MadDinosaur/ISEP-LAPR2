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

    /**
     * Button to go to the "Import CSV File" menu
     */
    @FXML
    private Button importCSVFileBtn;

    /**
     * Button to go to the "Overview Tests" menu
     */
    @FXML
    private Button overviewTestsBtn;

    /**
     * Button to go to the "Validate Results" menu
     */
    @FXML
    private Button validateResults;

    /**
     * Button to go back
     */
    @FXML
    private Button backBtn;

    /**
     * Method that is activated when the importCSVFileBtn is pressed: replaces the current scene with the "Import CSV File" menu
     * @param event when the button is pressed with a left-click from the mouse
     */
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

    /**
     * Method that is activated when the overviewTestsBtn is pressed: replaces the current scene with the "Overview Tests" menu
     * @param event when the button is pressed with a left-click from the mouse
     */
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


    /**
     * Setter for the main application
     * @param mainApp the very first scene
     */
    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Used to go back to this menu
     */
    public void toLabCoordMenu() {
        try {
            LabCoordMenuUI labCoordMenuUI = (LabCoordMenuUI) mainApp.replaceSceneContent("/fxml/LabCoordMenuScene.fxml");
            labCoordMenuUI.setMainApp(this.mainApp);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Runs the validate tests UI
     * @param event when the button is pressed with a left-click from the mouse
     */
    @FXML
    void validateResults(ActionEvent event) {
        mainApp.getStage().setIconified(true);
        new ValidateTestUI().run();
    }

    /**
     * Goes back a scene on the application
     * @param event when the button is pressed with a left-click from the mouse
     */
    @FXML
    void backScene(ActionEvent event) {
        this.mainApp.toMainScene();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //empty
    }
}
