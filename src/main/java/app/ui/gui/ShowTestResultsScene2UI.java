package app.ui.gui;

import app.domain.model.TestParameter;
import app.domain.store.TestParamList;
import app.mappers.dto.TestDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowTestResultsScene2UI implements Initializable {
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
        lbDateRegistration.setText(testDto.getDateTimeRegister());
        lbDateValidation.setText(testDto.getDateTimeValidation());
    }

    public void displayTestResultInfo() {
        Label lbParam = new Label("NA");
        Label lbValue = new Label("NA");
        Label lbRefValue =  new Label("NA");
        int line = 1;


        TestParamList testParams = testDto.getTestParamList();
        for(TestParameter tp: testParams.getTestParameters()) {
            if (tp.getParameter() != null)
            lbParam.setText(tp.getParameter().getParameterName());
            if (tp.getResult() != null)
            lbValue.setText(tp.getResult().getValue() + tp.getResult().getMetric());
            if (tp.getReferenceValue() != null)
            lbRefValue.setText(tp.getReferenceValue().getMinValue() + tp.getReferenceValue().getMetric()
                    + " - " + tp.getReferenceValue().getMaxValue() + tp.getReferenceValue().getMetric());

            gridTests.add(lbParam, 0, line);
            gridTests.add(lbValue, 1, line);
            gridTests.add(lbRefValue, 2, line);

            line++;
        }
    }
}
