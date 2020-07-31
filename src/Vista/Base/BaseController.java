package Vista.Base;

import Vista.Base.LateralPanel.LateralPanel;
import Vista.Base.PanelSuperior.PanelSuperior;
import Vista.Base.UserPane.Consulta.userTableController;
import Vista.Base.UserPane.Register.RegisterController;
import Vista.Base.UserPane.UserPaneController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

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

    @FXML private JFXButton topUserRegisterButton;
    @FXML private Pane userRegisterPane;
    @FXML private JFXTextField nombreTextfield;
    @FXML private JFXTextField apellidoTextfield;
    @FXML private JFXTextField usernameTextfield;
    @FXML private JFXTextField passwordTextfield;
    @FXML private Button registerUserButton;

    @FXML private JFXButton topUserConsultaButton;
    @FXML private Pane userConsultaPane;
    @FXML private TableView tablaUsuarios;

    public void initialize()
    {
        PanelSuperior.initialize(labelX, shadowLabelX, 1);
        PanelSuperior.initialize(labelM, shadowLabelM, 2);
        PanelSuperior.initialize(panelSuperior);

        LateralPanel.initialize(userButton, examenButton, resultButton, userPane);

        UserPaneController.initialize(topUserRegisterButton, topUserConsultaButton, userRegisterPane, userConsultaPane);

        RegisterController.initialize(nombreTextfield, apellidoTextfield,
                usernameTextfield, passwordTextfield, registerUserButton);

        UserPaneController.initialize(topUserRegisterButton, topUserConsultaButton,
                userRegisterPane, userConsultaPane);

        userTableController.initialize(tablaUsuarios);
    }
}


