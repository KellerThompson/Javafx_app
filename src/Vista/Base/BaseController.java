package Vista.Base;

import Vista.Base.LateralPanel.LateralPanel;
import Vista.Base.PanelSuperior.PanelSuperior;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
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

    public void initialize()
    {
        PanelSuperior.initialize(labelX, shadowLabelX, 1);
        PanelSuperior.initialize(labelM, shadowLabelM, 2);
        PanelSuperior.initialize(panelSuperior);

        LateralPanel.initialize(userButton, examenButton, resultButton);
    }
}


