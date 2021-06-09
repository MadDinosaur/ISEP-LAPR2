package app.ui.gui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LabCoordUI implements Runnable{
    private App mainApp;

    public LabCoordUI(App app) {
        this.mainApp = app;
    }

    @Override
    public void run() {
        try {
            LabCoordMenuUI labCoordMenuUI = (LabCoordMenuUI) this.mainApp.replaceSceneContent("/fxml/LabCoordMenuScene.fxml");
            labCoordMenuUI.setMainApp(this.mainApp);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
