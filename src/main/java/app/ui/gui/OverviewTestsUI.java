package app.ui.gui;

import app.controller.OverviewTestsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OverviewTestsUI implements Initializable {

    /**
     * Main application
     */
    private App mainApp;

    /**
     * The parent from this scene
     */
    private LabCoordMenuUI parent;

    /**
     * The controller for this scene
     */
    private OverviewTestsController overviewTestsController = new OverviewTestsController();

    /**
     * Observable list containing both of the algorithms possible to use on the application
     */
    ObservableList<String> choiceBoxList = FXCollections.observableArrayList("Benchmark", "BruteForce");

    /**
     * Chart for the tests waiting for results
     */
    @FXML
    private LineChart<String, Integer> testsWaitingForResultsChart;

    /**
     * Y-Axis for the tests validated chart
     */
    @FXML
    private NumberAxis testsValidated;

    /**
     * Title of the scene
     */
    @FXML
    private Label titleLabel;

    /**
     * Chart for the tests waiting for report
     */
    @FXML
    private LineChart<String, Integer> testsWaitingForReportChart;

    /**
     * Text field where the user inputs the beginning date
     */
    @FXML
    private TextField beginningDateTF;

    /**
     * Text field where the user inputs the ending date
     */
    @FXML
    private TextField endingDateTF;

    /**
     * Chart for the tests validated
     */
    @FXML
    private LineChart<String, Integer> testsValidatedChart;

    /**
     * Text area where all the info shows up
     */
    @FXML
    private TextArea outputTextArea;

    /**
     * Y-axis for the tests waiting for report chart
     */
    @FXML
    private NumberAxis testsWaitingForReport;

    /**
     * Overview button
     */
    @FXML
    private Button overviewBtn;

    /**
     * Back button
     */
    @FXML
    private Button backBtn;

    /**
     * Choice box containing the observable list
     */
    @FXML
    private ChoiceBox<String> choiceBox;

    /**
     * X-axis for all the charts
     */
    @FXML
    private CategoryAxis days;

    /**
     * Y-axis for the tests waiting for results chart
     */
    @FXML
    private NumberAxis testsWaitingForResults;


    /**
     * Setter for the main application
     * @param mainApp main application
     */
    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Setter for the parent of this scene
     * @param parent parent
     */
    public void setParent(LabCoordMenuUI parent) {
        this.parent = parent;
    }

    /**
     * Method that is activated when the back button is pressed
     * @param event when the button is pressed with a left-click from the mouse
     */
    @FXML
    void backScene(ActionEvent event) {
        parent.toLabCoordMenu();
    }

    /**
     * Method containing all the necessary things that show up on the menu
     * @param event when the button is pressed with a left-click from the mouse
     */
    @FXML
    void overviewTests(ActionEvent event) {
        LocalDate beginningDate = stringToLocalDate(beginningDateTF.getText());
        LocalDate endingDate = stringToLocalDate(endingDateTF.getText());
        List<Date> dateInterval = overviewTestsController.getDatesBetweenDateInterval(beginningDate, endingDate);
        String code = choiceBox.getValue();
        String totalNumberOfClients = String.valueOf(overviewTestsController.getTotalNumberOfClients());
        String totalNumberOfValidatedTests = String.valueOf(overviewTestsController.getTotalNumberOfValidatedTests());
        outputTextArea.appendText(String.format("Total number of clients: %s\nTotal number of validated tests: %s\n", totalNumberOfClients, totalNumberOfValidatedTests));
        XYChart.Series<String, Integer> testsWaitingForResults = new XYChart.Series<>();
        testsWaitingForResults.setName("Tests waiting for results");
        XYChart.Series<String, Integer> testsWaitingForReport = new XYChart.Series<>();
        testsWaitingForReport.setName("Tests waiting for report");
        XYChart.Series<String, Integer> testsValidated = new XYChart.Series<>();
        testsValidated.setName("Tests validated");
        for (Date date : dateInterval) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = formatter.format(date);
            outputTextArea.appendText(String.format(
                    "Date: %s\n" +
                            "Number of tests waiting for results: %s\n" +
                            "Number of tests waiting for report: %s\n" +
                            "Number of validated tests: %s\n", strDate, overviewTestsController.getNumberOfTestsWaitingForResultsInDate(date), overviewTestsController.getNumberOfTestsWaitingForReportInDate(date), overviewTestsController.getNumberOfTestsValidatedInDate(date)));
            testsWaitingForResults.getData().add(new XYChart.Data<>(strDate, overviewTestsController.getNumberOfTestsWaitingForResultsInDate(date)));
            testsWaitingForReport.getData().add(new XYChart.Data<>(strDate, overviewTestsController.getNumberOfTestsWaitingForReportInDate(date)));
            testsValidated.getData().add(new XYChart.Data<>(strDate, overviewTestsController.getNumberOfTestsValidatedInDate(date)));
        }
        testsWaitingForResultsChart.animatedProperty().set(false);
        testsWaitingForResultsChart.getData().add(testsWaitingForResults);
        testsWaitingForReportChart.animatedProperty().set(false);
        testsWaitingForReportChart.getData().add(testsWaitingForReport);
        testsValidatedChart.animatedProperty().set(false);
        testsValidatedChart.getData().add(testsValidated);
        int[] sequence = overviewTestsController.getDifferenceOfNewAndValidatedTests(dateInterval);
        outputTextArea.appendText(String.format("Sequence: %s\n", Arrays.toString(sequence)));
        outputTextArea.appendText(String.format("Sub Sequence: %s\n", Arrays.toString(overviewTestsController.getBiggestContiguousSubSequence(sequence, code))));

    }

    /**
     * Transforms a date that is in string format into a LocalDate
     * @param date date in string
     * @return LocalDate
     */
    private LocalDate stringToLocalDate(String date) {
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

