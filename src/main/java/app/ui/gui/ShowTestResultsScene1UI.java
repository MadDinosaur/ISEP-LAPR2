package app.ui.gui;

import app.controller.App;
import app.controller.ShowTestResultController;
import app.domain.model.Test;
import app.mappers.dto.TestDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ShowTestResultsScene1UI implements Initializable {
    ShowTestResultController controller = new ShowTestResultController();
    app.ui.gui.App mainApp;
    ClientMenuUI parent;

    @FXML
    private ListView<TestDTO> lstViewTests;

    @FXML
    private Button btnBack;

    @FXML
    private Button btwView;

    @FXML
    void btnBack(ActionEvent event) {
        parent.toClientMenu();
    }

    @FXML
    void btnView(ActionEvent event) {
        try {
            ShowTestResultsScene2UI showTestResultsScene2UI =
                    (ShowTestResultsScene2UI) this.mainApp.
                            replaceSceneContent("/fxml/ShowTestResultsScene1.fxml");
            showTestResultsScene2UI.setParent(this);
            showTestResultsScene2UI.setMainApp(this.mainApp);
            showTestResultsScene2UI.setTest(lstViewTests.getSelectionModel().getSelectedItem());
        } catch (Exception ex) {
            Logger.getLogger(app.ui.gui.App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setMainApp(app.ui.gui.App mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayTestList();
    }

    public void setParent(ClientMenuUI parent) {
        this.parent = parent;
    }

    private void displayTestList() {
        lstViewTests = new ListView<>();

        List<TestDTO> tests = controller
                .displayClientTests(App.getInstance().getCurrentUserSession().getUserId().toString());
        lstViewTests.getItems().addAll(tests);
    }

    public void toScene1() {
        try {
            ShowTestResultsScene1UI showTestResultsScene1UI= (ShowTestResultsScene1UI) mainApp.replaceSceneContent("/fxml/ShowTestResultsScene1.fxml");
            showTestResultsScene1UI.setMainApp(this.mainApp);
        } catch (Exception ex) {
            Logger.getLogger(app.ui.gui.App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
