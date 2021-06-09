package app.ui.gui;

import app.controller.ImportCSVFileController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;

public class ImportCSVFileUI {

    private App mainApp;

    private ImportCSVFileController importCSVFileController = new ImportCSVFileController();


    @FXML
    private TextField fileNameTF;

    @FXML
    private Button importBtn;

    public ImportCSVFileUI() throws FileNotFoundException {
    }

    @FXML
    void importFile(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        importCSVFileController.setFile(selectedFile);

    }

    @FXML
    void showFileName(ActionEvent event) {
        fileNameTF.setText(importCSVFileController.getFile().getName());
    }

}
