package app.ui.gui;

import app.controller.AuthController;
import app.ui.console.*;
import app.ui.console.utils.Utils;
import auth.mappers.dto.UserRoleDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

public class AuthUI implements Initializable {
    private App mainApp;
    private AuthController ctrl = new AuthController();

    @FXML
    private TextField txtUserId;
    @FXML
    private TextField txtUserPwd;
    @FXML
    private Button btnLogin;

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void btnLogin(ActionEvent event) {

        String id = txtUserId.getText();
        String pwd = txtUserPwd.getText();

        if (!ctrl.doLogin(id, pwd)) {
            AlertDialog.throwError("Invalid Login!", "Invalid UserId and/or Password. Please try again.");
        } else {
            List<UserRoleDTO> roles = this.ctrl.getUserRoles();
            if ((roles == null) || (roles.isEmpty()) )
            {
                AlertDialog.throwError( "No Role!", "User has not any role assigned");
            }
            else
            {
                UserRoleDTO role = selectsRole(roles);
                if (!Objects.isNull(role))
                {
                    List<MenuItem> rolesUI = getMenuItemForRoles();
                    this.redirectToRoleUI(rolesUI,role);
                }
                else
                {
                    AlertDialog.throwError( "No Role!", "User did not select a role.");
                }
            }
        }
    }

    private List<MenuItem> getMenuItemForRoles()
    {
        List<MenuItem> rolesUI = new ArrayList<>();
        rolesUI.add(new MenuItem("Administrator", new AdminUI()));
        rolesUI.add(new MenuItem("Receptionist", new ReceptionistUI()));
        rolesUI.add(new MenuItem("Medical Lab Technician", new MedLabTechUI()));
        rolesUI.add(new MenuItem("Clinical Chemistry Technologist", new CliChemTechUI()));
        rolesUI.add(new MenuItem("Laboratory Coordinator", new LabCoordUI(mainApp)));
        rolesUI.add(new MenuItem("Specialist Doctor", new SpecDocUI()));
        rolesUI.add(new MenuItem("Client", new ClientUI(mainApp)));
        return rolesUI;
    }

    private UserRoleDTO selectsRole(List<UserRoleDTO> roles)
    {
        if (roles.size() == 1)
            return roles.get(0);
        else
            return (UserRoleDTO) Utils.showAndSelectOne(roles, "Select the role you want to adopt in this session:");
    }

    private void redirectToRoleUI(List<MenuItem> rolesUI, UserRoleDTO role)
    {
        boolean found = false;
        Iterator<MenuItem> it = rolesUI.iterator();
        while (it.hasNext() && !found)
        {
            MenuItem item = it.next();
            found = item.hasDescription(role.getDescription());
            if (found)
                item.run();
        }
        if (!found)
            System.out.println("There is no UI for users with role '" + role.getDescription() + "'");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //empty
    }
}
