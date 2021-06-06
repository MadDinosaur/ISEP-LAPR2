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

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ShowTestResultsScene2UI implements Initializable {
    private TestDTO testDto;

    public ShowTestResultsScene2UI(TestDTO test) {
        this.testDto = test;
    }

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
    private TableView<TestParameter> tViewResults;

    @FXML
    private TableColumn<TestParameter, String> tColParameter;

    @FXML
    private TableColumn<TestParameter, String> tColValue;

    @FXML
    private TableColumn<TestParameter, String> tColRefValue;

    @FXML
    void btnBack(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayTestInfo();
        displayTestResultInfo();
    }

    private void displayTestInfo() {
        lbTestCode.setText(testDto.getTestCode());
        lbNhsCode.setText(testDto.getNhsCode());
        lbDateRegistration.setText(testDto.getDateOfCreation());
        lbDateValidation.setText(testDto.getDateValidation());
    }

    private void displayTestResultInfo() {
        tColParameter.setCellValueFactory(new PropertyValueFactory<>("param"));
        tColRefValue.setCellValueFactory(new PropertyValueFactory<>("refValue"));
        tColValue.setCellValueFactory(new PropertyValueFactory<>("result"));

        TestParamList testParams = testDto.getTestParamList();
        tViewResults.getItems().addAll(testParams.getTestParameters());
    }
}
