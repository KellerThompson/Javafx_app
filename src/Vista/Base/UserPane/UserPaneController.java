package Vista.Base.UserPane;

import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.Pane;

public class UserPaneController
{
    private static JFXButton topUserRegisterButton;
    private static JFXButton topUserConsultaButton;
    private static Pane userRegisterPane;
    private static Pane userConsultaPane;

    private static buttonType buttonselected;

    public static void initialize(JFXButton topUserRegisterButton1, JFXButton topUserConsultaButton1,
                                  Pane userRegisterPane1, Pane userConsultaPane1)
    {
        topUserRegisterButton = topUserRegisterButton1;
        topUserConsultaButton = topUserConsultaButton1;
        userRegisterPane = userRegisterPane1;
        userConsultaPane = userConsultaPane1;

        initButton(topUserRegisterButton, buttonType.registro);
        initButton(topUserConsultaButton, buttonType.consulta);
    }

    private static void initButton(JFXButton button, buttonType buttonType)
    {
        button.setStyle("-fx-background-color: #ffffff;");

        button.onActionProperty().setValue(event -> {
            switch (buttonType)
            {
                case registro:
                    buttonselected = buttonType;

                    topUserRegisterButton.setStyle("-fx-background-color: #ffffff;");
                    userRegisterPane.visibleProperty().setValue(true);

                    topUserConsultaButton.setStyle("-fx-background-color: rgba(16,32,39,0.43);");
                    userConsultaPane.visibleProperty().setValue(false);
                    break;

                case consulta:
                    buttonselected = buttonType;

                    topUserConsultaButton.setStyle("-fx-background-color: #ffffff;");
                    userConsultaPane.visibleProperty().setValue(true);

                    topUserRegisterButton.setStyle("-fx-background-color: rgba(16,32,39,0.43);");
                    userRegisterPane.visibleProperty().setValue(false);
                    break;

                default:
                    break;
            }
        });

        buttonselected = buttonType.registro;
        topUserRegisterButton.setStyle("-fx-background-color: #ffffff;");
        userRegisterPane.visibleProperty().setValue(true);
        topUserConsultaButton.setStyle("-fx-background-color: rgba(16,32,39,0.43);");
        userConsultaPane.visibleProperty().setValue(false);
    }

    enum buttonType
    {
        registro, consulta
    }
}
