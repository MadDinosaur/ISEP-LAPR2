package app.ui.console;

import app.controller.RegisterClientController;
import app.domain.model.Exceptions.InvalidNameException;

public class RegisterClientUI {
    public static void main(String[] args) {
        boolean valido = false;
        while(!valido) {
            try {
                RegisterClientController.setNameClient("");
                valido =true;
            } catch (InvalidNameException e) {
                System.out.println(e);
            }
        }
    }
}
