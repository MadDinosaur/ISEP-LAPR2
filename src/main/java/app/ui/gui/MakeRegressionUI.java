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

    private MainMenuUI parent;
    private App mainApp;
    private CreateNhsReportController createNhsReportController = new CreateNhsReportController();

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
    public MenuItem weeks;

    @FXML
    private Button backBtn;

    @FXML
    private TextField currentDay;

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
        independentVar.setText(both.getText());
    }

    public void meanAge(ActionEvent actionEvent) {
        independentVar.setText(meanAge.getText());
    }

    public void totalTests(ActionEvent actionEvent) {
        independentVar.setText(totalTests.getText());
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
        if(typeOfPoints.equalsIgnoreCase("days")){
        }else{
            if(typeOfPoints.equalsIgnoreCase("weeks")){
                historicalPointsInt = historicalPointsInt*7;
            }
        }
        /*if(){

        }*/
        System.out.println(typeOfPoints);

    }

    public void daysAsPoints(ActionEvent actionEvent) {
        typeOfPoints.setText(days.getText());
    }

    public void weeksAsPoints(ActionEvent actionEvent) {
        typeOfPoints.setText(weeks.getText());
    }

    public void numberPoints(ActionEvent actionEvent) {
    }

    public void currentDay(ActionEvent actionEvent) {
    }

    public void initialDay(ActionEvent actionEvent) {
    }

    public void finalDay(ActionEvent actionEvent) {
    }
}
