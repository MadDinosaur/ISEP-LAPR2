package app.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

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
            showTestResultsScene1UI.setMainApp(this.mainApp);
            showTestResultsScene1UI.setParent(this);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void toUpdateInfoScene(ActionEvent event) {
        try {
            UpdateClientDataUI updateClientDataUI =
                    (UpdateClientDataUI) this.mainApp.replaceSceneContent("/fxml/UpdateClientInfo.fxml");
            updateClientDataUI.setMainApp(this.mainApp);
            updateClientDataUI.setParent(this);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toClientMenu() {
        try {
            ClientMenuUI clientMenuUI= (ClientMenuUI) mainApp.replaceSceneContent("/fxml/Client.fxml");
            clientMenuUI.setMainApp(this.mainApp);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayClientName();
    }

    private void displayClientName() {
        String clientEmail = app.controller.App.getInstance().getCurrentUserSession().getUserId().toString();
        String clientName = app.controller.App.getInstance().getCompany().getClientStore().getClientByEmail(clientEmail).getName();

        lbClientName.setText(clientName);
    }
}
