package Vista.Base;

import Vista.Base.LateralPanel.LateralPanel;
import Vista.Base.PanelSuperior.PanelSuperior;
import Vista.Base.UserPane.Register.RegisterController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML private JFXTextField nombreTextfield;
    @FXML private JFXTextField apellidoTextfield;
    @FXML private JFXTextField usernameTextfield;
    @FXML private JFXTextField passwordTextfield;
    @FXML private Button registerUserButton;

    public void initialize()
    {
        PanelSuperior.initialize(labelX, shadowLabelX, 1);
        PanelSuperior.initialize(labelM, shadowLabelM, 2);
        PanelSuperior.initialize(panelSuperior);

        LateralPanel.initialize(userButton, examenButton, resultButton, userPane);

        RegisterController.initialize(nombreTextfield, apellidoTextfield,
                usernameTextfield, passwordTextfield, registerUserButton);
    }
}


