package Vista.Base.SelectionPane;

import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.Pane;

public class SelectionPane
{
    private static JFXButton userButton;
    private static Pane userPane;

    private static JFXButton examenButton;
    private static Pane examenPane;

    private static JFXButton asignacionButton;
    private static Pane asignacionPane;

    private static JFXButton resultButton;

    private static buttonType buttonselected;

    public static void initialize(JFXButton userButton1, JFXButton examenButton1, JFXButton asigancionButton1, JFXButton resultButton1,
                                  Pane userPane1, Pane examenPane1, Pane asigancionPane1)
    {
        userButton = userButton1;
        userPane = userPane1;

        examenButton = examenButton1;
        examenPane = examenPane1;

        asignacionButton = asigancionButton1;
        asignacionPane = asigancionPane1;

        resultButton = resultButton1;

        initlateralButton(userButton, buttonType.user);
        initlateralButton(examenButton, buttonType.examen);
        initlateralButton(asignacionButton, buttonType.asignacion);
        initlateralButton(resultButton, buttonType.result);
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
                    buttonselected = SelectionPane.buttonType.user;

                    userButton.setStyle("-fx-background-color: #81B0A9;");
                    userPane.visibleProperty().setValue(true);

                    examenButton.setStyle("-fx-background-color: #102027;");
                    examenPane.visibleProperty().setValue(false);

                    asignacionButton.setStyle("-fx-background-color: #102027;");
                    asignacionPane.visibleProperty().setValue(false);

                    resultButton.setStyle("-fx-background-color: #102027;");
                    break;

                case examen:
                    buttonselected = SelectionPane.buttonType.examen;

                    userButton.setStyle("-fx-background-color: #102027;");
                    userPane.visibleProperty().setValue(false);

                    examenButton.setStyle("-fx-background-color: #81B0A9;");
                    examenPane.visibleProperty().setValue(true);

                    asignacionButton.setStyle("-fx-background-color: #102027;");
                    asignacionPane.visibleProperty().setValue(false);

                    resultButton.setStyle("-fx-background-color: #102027;");
                    break;

                case asignacion:
                    buttonselected = SelectionPane.buttonType.asignacion;

                    userButton.setStyle("-fx-background-color: #102027;");
                    userPane.visibleProperty().setValue(false);

                    examenButton.setStyle("-fx-background-color: #102027;");
                    examenPane.visibleProperty().setValue(false);

                    asignacionButton.setStyle("-fx-background-color: #81B0A9;");
                    asignacionPane.visibleProperty().setValue(true);

                    resultButton.setStyle("-fx-background-color: #102027;");
                    break;

                case result:
                    buttonselected = SelectionPane.buttonType.result;

                    userButton.setStyle("-fx-background-color: #102027;");
                    userPane.visibleProperty().setValue(false);

                    examenButton.setStyle("-fx-background-color: #102027;");
                    examenPane.visibleProperty().setValue(false);

                    asignacionButton.setStyle("-fx-background-color: #102027;");
                    asignacionPane.visibleProperty().setValue(false);

                    resultButton.setStyle("-fx-background-color: #81B0A9;");
                    break;

                default:
                    break;
            }
        });

        buttonselected = SelectionPane.buttonType.user;
        userButton.setStyle("-fx-background-color: #81B0A9;");
        userPane.visibleProperty().setValue(true);
        examenPane.visibleProperty().setValue(false);
        asignacionPane.visibleProperty().setValue(false);
    }

    enum buttonType
    {
        user, examen, asignacion, result
    }
}
