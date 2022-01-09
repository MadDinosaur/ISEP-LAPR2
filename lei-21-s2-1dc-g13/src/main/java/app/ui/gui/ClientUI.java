package app.ui.gui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientUI implements Runnable {
    private App mainApp;

    public ClientUI(App app) {
        this.mainApp = app;
    }

    @Override
    public void run() {
        try {
            ClientMenuUI clientMenuUI = (ClientMenuUI) this.mainApp.replaceSceneContent("/fxml/Client.fxml");
            clientMenuUI.setMainApp(this.mainApp);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
