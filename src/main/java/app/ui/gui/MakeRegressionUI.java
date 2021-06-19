package app.ui.gui;

import app.controller.CreateNhsReportController;
import app.ui.console.MainMenuUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MakeRegressionUI implements Initializable {

    public MenuButton independentVar;
    public MenuItem totalTests;
    public MenuItem meanAge;
    public MenuItem both;
    public TextField initialDay;
    public TextField finalDay;
    public Button sendReport;
    public TextField historicalPoints;
    public MenuButton typeOfPoints;
    public MenuItem days;
    public SeparatorMenuItem weeks;

    private App mainApp;

    private CreateNhsReportController createNhsReportController = new CreateNhsReportController();

    @FXML
    private Button backBtn;

    @FXML
    private TextField currentDay;

    private MainMenuUI parent;

    public void setParent(MainMenuUI parent) {
        this.parent = parent;
    }

    @FXML
    void backScene(ActionEvent event) {
        new MainMenuUI();
    }

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void multiLinear(ActionEvent actionEvent) {
    }

    public void meanAge(ActionEvent actionEvent) {
    }

    public void totalTests(ActionEvent actionEvent) {
    }

    public void indVariable(ActionEvent actionEvent) {
    }


    public void typePoints(ActionEvent actionEvent) {
    }

    public void sendReport(ActionEvent actionEvent) {
        String historicalPoints = this.historicalPoints.getText();
        int historicalPointsInt = Integer.parseInt(historicalPoints);
        String currentDay = this.currentDay.getText();
        String intialDay = this.initialDay.getText();
        String finalDay = this.finalDay.getText();
        String typeOfPoints = this.typeOfPoints.getText();
        System.out.println(typeOfPoints);


    }

}
