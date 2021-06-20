package app.ui.gui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminUI implements Runnable {
    private App mainApp;

    public AdminUI(App app) {
        this.mainApp = app;
    }

    @Override
    public void run() {
        try {
            AdminMenuUI adminMenuUI = (AdminMenuUI) this.mainApp.replaceSceneContent("/fxml/AdminScene.fxml");
            adminMenuUI.setMainApp(this.mainApp);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
