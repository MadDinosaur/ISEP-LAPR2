package app.ui.gui;

import app.controller.ImportCSVFileController;
import app.domain.model.Exceptions.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

public class ImportCSVFileUI implements Initializable {

    private App mainApp;

    private ImportCSVFileController importCSVFileController = new ImportCSVFileController();


    @FXML
    private TextField fileNameTF;

    @FXML
    private Button selectBtn;

    @FXML
    private Button importBtn;


    public ImportCSVFileUI() throws FileNotFoundException {
    }

    @FXML
    void selectFile(ActionEvent event) throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        importCSVFileController.setFile(selectedFile);
        fileNameTF.setText(importCSVFileController.getFile().getPath());

    }

    @FXML
    void importFile(ActionEvent event) {
        while (importCSVFileController.goOneLineForward()) {
                importingFileSteps();
                System.out.println("Test was imported");
        }
    }

    void importingFileSteps() {
        try {
            importCSVFileController.readTestTypeCode();
        } catch (InvalidTestCodeException e) {
            importingFileSteps();
        }
        try {
            importCSVFileController.readCategoryName();
        } catch (InvalidCategoryException e) {
            importingFileSteps();
        }
        try {
            importCSVFileController.readParameterCodeAndResult();
        } catch (InvalidParameterException e) {
            importingFileSteps();
        }
        try {
            importCSVFileController.readNhsId();
        } catch (InvalidClientException e) {
            importingFileSteps();
        }
        try {
            importCSVFileController.readLabId();
        } catch (InvalidLaboratoryIDException e) {
            importingFileSteps();
        }
        importCSVFileController.readTestCode();
        importCSVFileController.readNhsCode();
        importCSVFileController.readDateTimeRegister();
        importCSVFileController.readDateTimeResults();
        importCSVFileController.readDateTimeReport();
        importCSVFileController.readDateTimeValidation();
        importCSVFileController.createTest();
        importCSVFileController.saveTest();
        importCSVFileController.goOneLineForward();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
