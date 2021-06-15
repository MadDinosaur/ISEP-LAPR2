package app.ui.gui;

import app.controller.ImportCSVFileController;
import app.domain.model.Exceptions.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
        fileNameTF.setAlignment(Pos.BASELINE_LEFT);

    }

    @FXML
    void importFile(ActionEvent event) {
            importingFileSteps();
    }

    void importingFileSteps() {
        do {
            try {
                importCSVFileController.readTestTypeCode();
            } catch (InvalidTestCodeException e) {
                e.printStackTrace();
                importCSVFileController.goOneLineForward();
                continue;
            }
            try {
                importCSVFileController.readCategoryName();
            } catch (InvalidCategoryException e) {
                e.printStackTrace();
                importCSVFileController.goOneLineForward();
                continue;
            }
            try {
                importCSVFileController.readParameterCodeAndResult();
            } catch (InvalidParameterException e) {
                e.printStackTrace();
                importCSVFileController.goOneLineForward();
                continue;
            }
            importCSVFileController.readNhsId();
            System.out.println(importCSVFileController.nhsID);
            importCSVFileController.readCardNumber();
            importCSVFileController.readTin();
            importCSVFileController.readbirthday();
            importCSVFileController.readPhoneNumber();
            try {
                importCSVFileController.readName();
                importCSVFileController.readEmail();
            } catch (InvalidNameException | InvalidEmailException e) {
                importCSVFileController.goOneLineForward();
                continue;
            }
            importCSVFileController.readAddress();

            try {
                importCSVFileController.createClient();
            } catch (NullPointerException e) {
                e.printStackTrace();
                importCSVFileController.goOneLineForward();
                continue;
            }
            importCSVFileController.saveClient();
            try {
                importCSVFileController.readLabId();
            } catch (InvalidLaboratoryIDException e) {
                e.printStackTrace();
                importCSVFileController.goOneLineForward();
                continue;
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
        } while (importCSVFileController.fileHasNextLine());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
