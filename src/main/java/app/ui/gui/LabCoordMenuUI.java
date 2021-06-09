package app.ui.gui;

import com.sun.org.apache.xml.internal.security.Init;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class LabCoordMenuUI implements Initializable {
    private App mainApp;

    @FXML
    private Button importCSVFileMenu;

    public void setMainApp(App mainApp) { this.mainApp = mainApp; }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
