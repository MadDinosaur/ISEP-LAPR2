package app.ui.gui;

import app.controller.UpdateClientDataController;
import app.domain.shared.Constants;
import app.mappers.dto.ClientDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateClientDataUI implements Initializable {
    private App mainApp;
    private ClientMenuUI parent;
    private UpdateClientDataController controller = new UpdateClientDataController();

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtCardNumber;

    @FXML
    private TextField txtNhsId;

    @FXML
    private TextField txtBirthDate;

    @FXML
    private TextField txtTIN;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtEmail;

    @FXML
    private ComboBox<String> cmbBoxSex;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSave;

    @FXML
    void btnBack(ActionEvent event) {
        this.parent.toClientMenu();
    }

    @FXML
    void btnSaveChanges(ActionEvent event) {
        ClientDTO clientDTO = new ClientDTO(txtName.getText(),
                txtCardNumber.getText(),
                txtNhsId.getText(),
                txtBirthDate.getText(),
                txtTIN.getText(),
                txtPhoneNumber.getText(),
                txtEmail.getText(),
                cmbBoxSex.getSelectionModel().getSelectedItem());

        try {
            if (AlertDialog.requestConfirmation( "Save Changes?", "Please confirm if you wish to alter your personal data.")) {
                controller.updateClientData(clientDTO);
                AlertDialog.success("Changed saved!", "Your information has been updated.");
            }
        } catch (Exception ex) {
            AlertDialog.throwError( "Invalid Data!", ex.getMessage());
        }
    }

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    public void setParent(ClientMenuUI parent) {
        this.parent = parent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillComboBox();
        displayClientInfo();
    }

    private void fillComboBox() {
        cmbBoxSex.getItems().add(Constants.SEX_OPTION_1);
        cmbBoxSex.getItems().add(Constants.SEX_OPTION_2);
        cmbBoxSex.getItems().add(Constants.SEX_BY_DEFAULT);
    }

    private void displayClientInfo() {
        ClientDTO client = controller.getClient(app.controller.App.getInstance().getCurrentUserSession().getUserId().toString());

        txtName.setText(client.getName());
        txtCardNumber.setText(client.getCardNumber());
        txtNhsId.setText(client.getNhsId());
        txtBirthDate.setText(client.getDateBirth());
        txtTIN.setText(client.getTIN());
        txtPhoneNumber.setText(client.getPhoneNumber());
        txtEmail.setText(client.getEmail());
        cmbBoxSex.getSelectionModel().select(client.getSex());
    }
}
