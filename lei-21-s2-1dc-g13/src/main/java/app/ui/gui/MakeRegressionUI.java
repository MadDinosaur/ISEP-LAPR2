package app.ui.gui;

import app.controller.CreateNhsReportController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MakeRegressionUI implements Initializable {

    public MenuButton varForTest;
    public MenuItem varX1;
    public MenuItem varX2;
    public MenuItem intercept;
    public TextField signiLevel;
    public TextField confLevel;
    private AdminMenuUI parent;
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

    public void setParent(AdminMenuUI parent) {
        this.parent = parent;
    }

    @FXML
    void backScene(ActionEvent event) {
        parent.toAdminMenu();
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
        String intialDayString = this.initialDay.getText();
        String finalDay = this.finalDay.getText();
        String typeOfPoints = this.typeOfPoints.getText();
        String confidenceLevel = this.confLevel.getText();
        String significanceLevel = this.signiLevel.getText();
        int significance = Integer.parseInt(significanceLevel);
        int confidence = Integer.parseInt(confidenceLevel);

        String varToTest = this.varForTest.getText();
        String varForTesting = "0";
        if(varToTest.equalsIgnoreCase(varX1.getText())){
            varForTesting = "1";
        }else{
            if(varToTest.equalsIgnoreCase(varX2.getText())){
                varForTesting = "2";
            }else{
                if(varToTest.equalsIgnoreCase(intercept.getText())){
                    varForTesting = "3";
                }
            }
        }


        if(typeOfPoints.equalsIgnoreCase("days")){
        }else{
            if(typeOfPoints.equalsIgnoreCase("weeks")){
                historicalPointsInt = historicalPointsInt*7;
            }
        }

        String independentVariable = null;
        if(independentVar.getText().equalsIgnoreCase(meanAge.getText())){
            independentVariable = "mean age";
        }else{
            if(independentVar.getText().equalsIgnoreCase(totalTests.getText())){
                independentVariable = "total tests";
            }else{
                if(independentVar.getText().equalsIgnoreCase(both.getText())){
                    independentVariable = "multilinear";
                }
            }
        }

        if (varForTesting.equals("2") && !independentVariable.equals("multilinear")) {
            System.out.println("Can't test var X2 in simple linear regression!");
        }



        createNhsReportController.setHistoricalPoints(historicalPointsInt);
        createNhsReportController.setCurrentDay(currentDay);
        createNhsReportController.setInitialDay(intialDayString);
        createNhsReportController.setFinalDay(finalDay);
        createNhsReportController.setIndependentVariable(independentVariable);
        createNhsReportController.setConfidence(confidence);
        createNhsReportController.setSignificance(significance);
        createNhsReportController.setVarTest(varForTesting);


        createNhsReportController.makeRegression();



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

    public void varX1Btn(ActionEvent actionEvent) {
        varForTest.setText(varX1.getText());
    }

    public void varX2Btn(ActionEvent actionEvent) {
        varForTest.setText(varX2.getText());
    }

    public void interceptBtn(ActionEvent actionEvent) {
        varForTest.setText(intercept.getText());
    }
}
