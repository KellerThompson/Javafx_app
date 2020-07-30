package Vista.Base.LateralPanel;

import com.jfoenix.controls.JFXButton;

public class LateralPanel
{
    private static JFXButton userButton;
    private static JFXButton examenButton;
    private static JFXButton resultButton;
    private static buttonType buttonselected;

    public static void initialize(JFXButton userButton1, JFXButton examenButton1, JFXButton resultButton1)
    {
        userButton = userButton1;
        examenButton = examenButton1;
        resultButton = resultButton1;
        initlateralButton(userButton, LateralPanel.buttonType.user);
        initlateralButton(examenButton, LateralPanel.buttonType.examen);
        initlateralButton(resultButton, LateralPanel.buttonType.result);
    }

    private static void initlateralButton(JFXButton button, buttonType buttonType)
    {
        button.setStyle("-fx-background-color: #102027;");

        button.onMouseEnteredProperty().setValue(event ->
        {
            if(buttonselected != buttonType)
                button.setStyle("-fx-background-color: #62727B;");
        });

        button.onMouseExitedProperty().setValue(event ->
        {
            if(buttonselected != buttonType)
                button.setStyle("-fx-background-color: #102027;");
        });

        button.onActionProperty().setValue(event -> {
            switch (buttonType)
            {
                case user:
                    buttonselected = LateralPanel.buttonType.user;
                    userButton.setStyle("-fx-background-color: #81B0A9;");
                    examenButton.setStyle("-fx-background-color: #102027;");
                    resultButton.setStyle("-fx-background-color: #102027;");
                    break;

                case examen:
                    buttonselected = LateralPanel.buttonType.examen;
                    userButton.setStyle("-fx-background-color: #102027;");
                    examenButton.setStyle("-fx-background-color: #81B0A9;");
                    resultButton.setStyle("-fx-background-color: #102027;");
                    break;

                case result:
                    buttonselected = LateralPanel.buttonType.result;
                    userButton.setStyle("-fx-background-color: #102027;");
                    examenButton.setStyle("-fx-background-color: #102027;");
                    resultButton.setStyle("-fx-background-color: #81B0A9;");
                    break;

                default:
                    break;
            }
        });

        buttonselected = LateralPanel.buttonType.user;
        userButton.setStyle("-fx-background-color: #81B0A9;");
    }

    enum buttonType
    {
        user, examen, result
    }
}
