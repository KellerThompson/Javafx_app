package Vista.Base;

import Vista.Base.LateralPanel.LateralPanel;
import Vista.Base.PanelSuperior.PanelSuperior;
import Vista.Base.UserPane.TableController;
import Vista.Base.UserPane.RegisterController;
import Vista.Base.UserPane.UpdateController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import static Vista.Base.UserPane.UpdateController.updateType.*;

public class BaseController
{
    @FXML private Pane panelSuperior;

    @FXML private Label labelX;
    @FXML private Rectangle shadowLabelX;

    @FXML private Label labelM;
    @FXML private Rectangle shadowLabelM;

    @FXML private JFXButton userButton;
    @FXML private JFXButton examenButton;
    @FXML private JFXButton resultButton;

    @FXML private Pane userPane;
    @FXML private JFXTextField nombreTextfield;
    @FXML private JFXTextField apellidoTextfield;
    @FXML private JFXTextField usernameTextfield;
    @FXML private JFXTextField passwordTextfield;
    @FXML private Button registerUserButton;
    @FXML private TableView tablaUsuarios;

    @FXML private Label nombrelabel;
    @FXML private Label apellidolabel;
    @FXML private Label usernamelabel;
    @FXML private Label passwordlabel;

    @FXML private TextField updateNameTextfield;
    @FXML private Button updateNameButton;
    @FXML private TextField updateApellidposTextfield;
    @FXML private Button updateApellidposButton;
    @FXML private TextField updateUserameTextfield;
    @FXML private Button updateUserameButton;
    @FXML private TextField updatePasswordTextfield;
    @FXML private Button updatePaswordButton;

    @FXML private Button deleteButton;

    public void initialize()
    {
        PanelSuperior.initialize(labelX, shadowLabelX, 1);
        PanelSuperior.initialize(labelM, shadowLabelM, 2);
        PanelSuperior.initialize(panelSuperior);

        LateralPanel.initialize(userButton, examenButton, resultButton, userPane);

        RegisterController.initialize(nombreTextfield, apellidoTextfield,
                usernameTextfield, passwordTextfield, registerUserButton);

        TableController.initTableUser(tablaUsuarios);

        UpdateController.initialize(nombrelabel, apellidolabel, usernamelabel, passwordlabel, deleteButton);
        UpdateController.initUpdate(updateNameButton, updateNameTextfield, name);
        UpdateController.initUpdate(updateApellidposButton, updateApellidposTextfield, lastname);
        UpdateController.initUpdate(updateUserameButton, updateUserameTextfield, username);
        UpdateController.initUpdate(updatePaswordButton, updatePasswordTextfield, password);
    }
}


