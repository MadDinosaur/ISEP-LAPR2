package app.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class DevTeamUI implements Initializable {
    private App mainApp;

    @FXML
    private Button btnBack;

    @FXML
    void back(ActionEvent event) {
        mainApp.toMainScene();
    }

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //empty
    }
}

