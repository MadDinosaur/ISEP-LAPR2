package app.ui.gui;

import javafx.scene.control.Alert;

public class AlertDialog {
    public static void throwError(String title, String message) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText(title);
        a.setContentText(message);
    }
}
