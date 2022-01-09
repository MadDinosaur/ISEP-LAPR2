package app.ui.gui;

import app.ui.console.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminMenuUI implements Initializable {
    App mainApp;

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    public void toAdminMenu() {
        try {
            AdminMenuUI adminMenuUI= (AdminMenuUI) mainApp.replaceSceneContent("/fxml/AdminScene.fxml");
            adminMenuUI.setMainApp(this.mainApp);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void validateTest(ActionEvent actionEvent) {
        mainApp.getStage().setIconified(true);
        new ValidateTestUI().run();
    }

    public void registerEmployee(ActionEvent actionEvent) {
        mainApp.getStage().setIconified(true);
        new CreateEmployeeUI().run();
    }

    public void registerLab(ActionEvent actionEvent) {
        mainApp.getStage().setIconified(true);
        new RegisterNewLabUI().run();
    }

    public void registerTestResult(ActionEvent actionEvent) {
        mainApp.getStage().setIconified(true);
        new RecordTestResultUI().run();
    }

    public void registerTest(ActionEvent actionEvent) {
        mainApp.getStage().setIconified(true);
        new RegisterTestUI().run();
    }

    public void createTestType(ActionEvent actionEvent) {
        mainApp.getStage().setIconified(true);
        new CreateTestTypeUI().run();
    }

    public void makeRegression(ActionEvent actionEvent) {
        try {
            MakeRegressionUI makeRegressionUI = (MakeRegressionUI) this.mainApp.replaceSceneContent("/fxml/MakeRegressionUI.fxml");
            makeRegressionUI.setMainApp(this.mainApp);
            makeRegressionUI.setParent(this);
        } catch (Exception ex) {
            Logger.getLogger(app.ui.gui.App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registerNewReport(ActionEvent actionEvent) {
        mainApp.getStage().setIconified(true);
        new CreateReportUI().run();
    }

    public void registerSamples(ActionEvent actionEvent) {
        mainApp.getStage().setIconified(true);
        new SampleUI().run();
    }

    public void registerClient(ActionEvent actionEvent) {
        mainApp.getStage().setIconified(true);
        new RegisterClientUI().run();
    }

    public void createParameter(ActionEvent actionEvent) {
        mainApp.getStage().setIconified(true);
        new CreateNewParameterUI().run();
    }

    public void backScene(ActionEvent actionEvent) {
        mainApp.getStage().setIconified(true);
        mainApp.toMainScene();
    }
}
