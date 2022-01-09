package app.ui.gui;

import app.controller.ShowTestResultController;
import app.mappers.dto.TestDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowTestResultsScene1UI implements Initializable {
    ShowTestResultController controller = new ShowTestResultController();

    app.ui.gui.App mainApp;

    ClientMenuUI parentClient;
    ShowClientListUI parentCliChem;

    @FXML
    private ListView<TestDTO> lstViewTests = new ListView<>();

    @FXML
    private Button btnBack;

    @FXML
    private Button btwView;

    @FXML
    void btnBack(ActionEvent event) {
        if (parentClient == null)
            parentCliChem.toShowClientList();
        if (parentCliChem == null)
            parentClient.toClientMenu();
    }

    @FXML
    void btnView(ActionEvent event) {
        try {
            ShowTestResultsScene2UI showTestResultsScene2UI =
                    (ShowTestResultsScene2UI) this.mainApp.
                            openNewWindow("/fxml/ShowTestResultsScene2.fxml");
            showTestResultsScene2UI.setParent(this);
            showTestResultsScene2UI.setMainApp(this.mainApp);
            showTestResultsScene2UI.setTest(lstViewTests.getSelectionModel().getSelectedItem());
            showTestResultsScene2UI.displayTestInfo();
            showTestResultsScene2UI.displayTestResultInfo();
        } catch (Exception ex) {
            Logger.getLogger(app.ui.gui.App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setMainApp(app.ui.gui.App mainApp) {
        this.mainApp = mainApp;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setParent(Initializable parent) {
        if (parent instanceof ClientMenuUI)
            this.parentClient = (ClientMenuUI) parent;
        if (parent instanceof ShowClientListUI)
            this.parentCliChem = (ShowClientListUI) parent;
    }

    public void displayTestList(String email) {
        List<TestDTO> tests = controller
                .displayClientTests(email);

        Comparator<TestDTO> byRegDate = new Comparator<TestDTO>() {
            @Override
            public int compare(TestDTO t1, TestDTO t2) {
                try {
                    return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(t1.getDateTimeRegister())
                            .compareTo(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(t2.getDateTimeRegister()));
                } catch (ParseException e) {
                    return -1;
                }
            }
        };
        tests.sort(byRegDate);
        lstViewTests.getItems().addAll(tests);
    }
}
