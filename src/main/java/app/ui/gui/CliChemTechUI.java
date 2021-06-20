package app.ui.gui;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CliChemTechUI implements Initializable, Runnable{
    private App mainApp;

    public CliChemTechUI(App app) {
        this.mainApp = app;
    }

    @Override
    public void run() {
        try {
            CliChemTechMenuUI cliChemTechMenuUI = (CliChemTechMenuUI) this.mainApp.replaceSceneContent("/fxml/CliChemTechMenuScene.fxml");
            cliChemTechMenuUI.setMainApp(this.mainApp);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //empty
    }
}
