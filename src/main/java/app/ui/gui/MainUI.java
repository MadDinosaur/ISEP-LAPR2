package app.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainUI implements Initializable {
    private App mainApp;

    @FXML
    private Button btnLogin;
    @FXML
    private Button btnDevTeam;

    /**
     * Initializes the UI class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }


    @FXML
    public void btnLogin(ActionEvent event) {
        try {
            AuthUI authUI = (AuthUI) this.mainApp.replaceSceneContent("/fxml/Auth.fxml");
            authUI.setMainApp(this.mainApp);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void btnDevTeam(ActionEvent event) {
        DevTeamUI devTeamUI = null;
        try {
            devTeamUI = (DevTeamUI) this.mainApp.replaceSceneContent("/fxml/DevTeam.fxml");
            devTeamUI.setMainApp(this.mainApp);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
