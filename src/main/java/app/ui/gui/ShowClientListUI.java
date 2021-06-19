package app.ui.gui;

import app.controller.ShowClientListController;
import app.mappers.dto.ClientDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowClientListUI implements Initializable{
    
    ShowClientListController control = new ShowClientListController();
    app.ui.gui.App mainApp;
    CliChemTechMenuUI parent;

    @FXML
    private ListView<ClientDTO> lstViewClients;

    @FXML
    private Button btnSortByName;

    @FXML
    private Button btnSortByTin;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnViewClientTest;

    public void setMainApp(app.ui.gui.App mainApp) {
        this.mainApp = mainApp;
    }

    public void setParent(CliChemTechMenuUI parent) {
        this.parent = parent;
    }

    @FXML
    void btnSortByName(ActionEvent event){
        String sortMethod = "name";

        try {
            lstViewClients.getItems().addAll(control.getSortedClientList(sortMethod));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnSortByTin(ActionEvent event){
        String sortMethod = "tin";

        try {
            lstViewClients.getItems().addAll(control.getSortedClientList(sortMethod));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void showClientTests(ActionEvent event){
        try{
            ShowTestResultsScene1UI showTestResultsScene1UI = new ShowTestResultsScene1UI();
            showTestResultsScene1UI.setMainApp(this.mainApp);
            showTestResultsScene1UI.setParent2(this);
            showTestResultsScene1UI.displayTestList(lstViewClients.getSelectionModel().getSelectedItem().getEmail());
        } catch (Exception e){
            Logger.getLogger(app.ui.gui.App.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void toShowClientList(){
        try {
            ShowClientListUI showClientListUI= (ShowClientListUI) mainApp.replaceSceneContent("/fxml/ShowClientList.fxml");
            showClientListUI.setMainApp(this.mainApp);
            showClientListUI.setParent(this.parent);
        } catch (Exception e) {
            Logger.getLogger(app.ui.gui.App.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    @FXML
    void btnBack(ActionEvent event) {parent.toCliChemTechMenu();}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}
}
