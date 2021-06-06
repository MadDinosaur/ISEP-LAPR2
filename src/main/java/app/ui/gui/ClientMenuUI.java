package app.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientMenuUI implements Initializable {
    App mainApp;

    @FXML
    private ImageView iconUser;

    @FXML
    private Label lbClientName;

    @FXML
    private Button btnUpdateInfo;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnTestResults;

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void btnBack(ActionEvent event) {
        this.mainApp.toMainScene();
    }

    @FXML
    void toShowTestResultsScene(ActionEvent event) {
        try {
            ShowTestResultsScene1UI showTestResultsScene1UI =
                    (ShowTestResultsScene1UI) this.mainApp.
                            replaceSceneContent("/fxml/ShowTestResultsScene1.fxml");
            showTestResultsScene1UI.setClientMenuUI(this);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void toUpdateInfoScene(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayClientName();
        displayClientIcon();
    }

    private void displayClientName() {
        lbClientName.setText(app.controller.App.getInstance().getCurrentUserSession().getUserName());
    }

    private void displayClientIcon() {
        FileInputStream input = null;
        try {
            input = new FileInputStream("src/main/resources/images/user_icon.png");
            Image image = new Image(input);
            iconUser = new ImageView(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
