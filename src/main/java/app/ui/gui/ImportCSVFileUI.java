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

    private LabCoordMenuUI parent;

    @FXML
    private TextField fileNameTF;

    @FXML
    private Button selectBtn;

    @FXML
    private Button importBtn;

    @FXML
    private Button backBtn;


    public ImportCSVFileUI() {
        //empty constructor
    }

    public void setParent(LabCoordMenuUI parent) {
        this.parent = parent;
    }

    @FXML
    void selectFile(ActionEvent event) throws FileNotFoundException {
        fileNameTF.setDisable(true);
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if(selectedFile != null) {
            importCSVFileController.setFile(selectedFile);
            fileNameTF.setText(importCSVFileController.getFile().getPath());
            fileNameTF.setAlignment(Pos.BASELINE_LEFT);
        }
    }

    @FXML
    void importFile(ActionEvent event) {
        importingFileSteps();
    }

    void importingFileSteps() {
        while (importCSVFileController.fileHasNextLine()) {
            importCSVFileController.createNewControllers();
            try {
                importCSVFileController.goOneLineForward();
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            try {
                importCSVFileController.readTestTypeCode();
            } catch (InvalidTestCodeException | InvalidTestType e) {
                System.out.println(e.getMessage());
                continue;
            }

            try {
                importCSVFileController.readCategoryName();
            } catch (InvalidCategoryException e) {
                System.out.println(e.getMessage());
                continue;
            }

            try {
                importCSVFileController.readParameterCodeAndResult();
            } catch (InvalidParameterException e) {
                System.out.println(e.getMessage());
                continue;
            }

            importCSVFileController.readNhsId();
            importCSVFileController.readCardNumber();


            importCSVFileController.readTin();
            importCSVFileController.readbirthday();
            importCSVFileController.readPhoneNumber();
            try {
                importCSVFileController.readName();
            } catch (InvalidNameException e) {
                System.out.println(e.getMessage());
                continue;
            }

            try {
                importCSVFileController.readEmail();
            } catch (InvalidEmailException e) {
                System.out.println(e.getMessage());
                continue;
            }
            importCSVFileController.readAddress();
            importCSVFileController.readLabId();
            importCSVFileController.readTestCode();
            importCSVFileController.readNhsCode();
            importCSVFileController.readDateTimeRegister();
            importCSVFileController.readDateTimeResults();
            importCSVFileController.readDateTimeReport();
            importCSVFileController.readDateTimeValidation();
            try {
                importCSVFileController.createClient();
            } catch (NullPointerException e) {
                e.printStackTrace();
                continue;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
            importCSVFileController.saveClient();
            importCSVFileController.createTest();
            importCSVFileController.saveTest();

        }
        System.out.println("The CSV file was imported!");
    }

    @FXML
    void backScene(ActionEvent event) {
        parent.toLabCoordMenu();
    }

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //empty
    }
}
