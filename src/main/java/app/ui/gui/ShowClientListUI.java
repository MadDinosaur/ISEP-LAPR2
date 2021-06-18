package app.ui.gui;

import app.controller.App;
import app.controller.ShowClientListController;
import app.controller.ShowTestResultController;
import app.domain.model.Test;
import app.mappers.dto.TestDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowClientListUI {
    
    private ShowClientListController control;
    app.ui.gui.App mainApp;
    ClinicalChemistryTechnologistMenuUI parent;

    public void setMainApp(app.ui.gui.App mainApp) {
        this.mainApp = mainApp;
    }

    public void setParent(ClinicalChemistryTechnologistMenuUI parent) {
        this.parent = parent;
    }
}
