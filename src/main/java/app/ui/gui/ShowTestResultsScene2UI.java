package app.ui.gui;

import app.controller.ShowTestResultController;
import app.mappers.dto.TestDTO;
import app.mappers.dto.TestParameterDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ShowTestResultsScene2UI implements Initializable {
    private ShowTestResultController showTestResultController = new ShowTestResultController();

    private App mainApp;
    private ShowTestResultsScene1UI parent;
    private TestDTO testDto;

    @FXML
    private Button btnBack;

    @FXML
    private Label lbTestCode;

    @FXML
    private Label lbNhsCode;

    @FXML
    private Label lbTestType;

    @FXML
    private Label lbDateRegistration;

    @FXML
    private Label lbDateCollection;

    @FXML
    private Label lbDateValidation;

    @FXML
    private GridPane gridTests;

    public void setParent(ShowTestResultsScene1UI parent) {
        this.parent = parent;
    }

    public void setTest(TestDTO test) {
        this.testDto = test;
    }

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void btnBack(ActionEvent event) {
        this.parent.toScene1();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void displayTestInfo() {
        lbTestCode.setText(testDto.getTestCode());
        lbNhsCode.setText(testDto.getNhsCode());
        lbTestType.setText(testDto.getTestType());
        lbDateRegistration.setText(testDto.getDateTimeRegister());
        lbDateValidation.setText(testDto.getDateTimeValidation());
        lbDateCollection.setText(testDto.getDateTimeSamples());
    }

    public void displayTestResultInfo() {
        Label lbParam = new Label(), lbValue = new Label(), lbRefValue = new Label();
        int line = 1;

        List<TestParameterDTO> testParams = showTestResultController.displayTestResults(testDto.getTestCode());

        for(TestParameterDTO tp: testParams) {
            lbParam.setText(tp.getParameter());
            lbValue.setText(tp.getResultValue() + tp.getResultMetric());
            lbRefValue.setText(tp.getRefValueMin() + tp.getRefValueMetric()
                    + " - " + tp.getRefValueMax() + tp.getRefValueMetric());

            gridTests.add(lbParam, 0, line);
            gridTests.add(lbValue, 1, line);
            gridTests.add(lbRefValue, 2, line);

            line++;
        }
    }
}
