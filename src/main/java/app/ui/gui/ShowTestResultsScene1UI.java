package app.ui.gui;

import app.controller.App;
import app.controller.ShowTestResultController;
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
import java.util.stream.Collectors;

public class ShowTestResultsScene1UI implements Initializable {
    ShowTestResultController controller = new ShowTestResultController();
    ClientMenuUI parent;

    @FXML
    private Label lbClientName;

    @FXML
    private ListView<String> lstViewTests;

    @FXML
    private Button btnBack;

    @FXML
    private Button btwView;

    @FXML
    void btnBack(ActionEvent event) {

    }

    @FXML
    void btnView(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayClientName();
        displayTestList();
    }

    public void setClientMenuUI(ClientMenuUI parent) {
        this.parent = parent;
    }

    private void displayClientName() {
        lbClientName.setText(App.getInstance().getCurrentUserSession().getUserName() + ",");
    }

    private void displayTestList() {
        lstViewTests = new ListView<>();

        List<String> tests = controller
                .displayClientTests(App.getInstance().getCurrentUserSession().getUserId().toString())
                .stream()
                .map(TestDTO :: toString)
                .collect(Collectors.toList());
        lstViewTests.getItems().addAll(tests);
    }
}
