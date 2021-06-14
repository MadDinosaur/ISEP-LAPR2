package app.ui.gui;

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
    private Button importCSVFileMenu;

    @FXML
    private Button overviewTests;

    @FXML
    private Button validateResults;

    @FXML
    void importCSVFileMenu(ActionEvent event) {
        try {
            ImportCSVFileUI importCSVFileUI =
                    (ImportCSVFileUI) this.mainApp.
                            replaceSceneContent("/fxml/ImportCSVFileScene.fxml");
        } catch (Exception ex) {
            Logger.getLogger(app.ui.gui.App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setMainApp(App mainApp) { this.mainApp = mainApp; }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
