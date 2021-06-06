package app.ui.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class AlertDialog {
    public static void throwError (String title, String message) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText(title);
        a.setContentText(message);
        a.showAndWait();
    }

    public static void success (String title, String message) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(title);
        a.setContentText(message);
        a.showAndWait();
    }

    public static boolean requestConfirmation(String title, String message) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText(title);
        a.setContentText(message);
        return (a.showAndWait().get() == ButtonType.OK);
    }
}
