package app.ui.gui;

import app.controller.OverviewTestsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OverviewTestsUI implements Initializable {

    private App mainApp;

    private LabCoordMenuUI parent;

    private OverviewTestsController overviewTestsController = new OverviewTestsController();

    ObservableList<String> choiceBoxList = FXCollections.observableArrayList("Benchmark", "BruteForce");

    @FXML
    private Button overviewBtn;

    @FXML
    private TextField endingDateTF;

    @FXML
    private Label titleLabel;

    @FXML
    private TextArea outputTextArea;

    @FXML
    private Button backBtn;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField beginningDateTF;


    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    public void setParent(LabCoordMenuUI parent) {
        this.parent = parent;
    }

    @FXML
    void backScene(ActionEvent event) {
        parent.toLabCoordMenu();
    }

    @FXML
    void overviewTests(ActionEvent event) {
        LocalDate beginningDate = stringToLocalDate(beginningDateTF.getText());
        LocalDate endingDate = stringToLocalDate(endingDateTF.getText());
        List<Date> dateInterval = overviewTestsController.getDatesBetweenDateInterval(beginningDate, endingDate);
        String code = choiceBox.getValue();
        String totalNumberOfClients = String.valueOf(overviewTestsController.getTotalNumberOfClients());
        String totalNumberOfValidatedTests = String.valueOf(overviewTestsController.getTotalNumberOfValidatedTests());
        outputTextArea.appendText(String.format("Total number of clients: %s\nTotal number of validated tests: %s\n", totalNumberOfClients, totalNumberOfValidatedTests));
        for (Date date : dateInterval) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = formatter.format(date);
            outputTextArea.appendText(String.format(
                    "Date: %s\n" +
                    "Number of tests waiting for results: %s\n" +
                    "Number of tests waiting for report: %s\n" +
                    "Number of validated tests: %s\n", strDate, overviewTestsController.getNumberOfTestsWaitingForResultsInDate(date), overviewTestsController.getNumberOfTestsWaitingForReportInDate(date), overviewTestsController.getNumberOfTestsValidatedInDate(date)));
        }
        int[] sequence = overviewTestsController.getDifferenceOfNewAndValidatedTests(dateInterval);
        outputTextArea.appendText(Arrays.toString(sequence));
        outputTextArea.appendText(Arrays.toString(overviewTestsController.getBiggestContiguousSubSequence(sequence, code)));

    }

    LocalDate stringToLocalDate(String date) {
        String[] anoMesDia = date.split("/");
        int dia = Integer.parseInt(anoMesDia[0]);
        int mes = Integer.parseInt(anoMesDia[1]);
        int ano = Integer.parseInt(anoMesDia[2]);
        return LocalDate.of(ano, mes, dia);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.setItems(choiceBoxList);
        outputTextArea.setEditable(false);
    }
}

