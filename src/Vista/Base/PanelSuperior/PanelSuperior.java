package Vista.Base.PanelSuperior;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static Vista.Inicio.frame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class PanelSuperior
{
    private static double posX;
    private static double posY;

    public static void initialize(Label label, Rectangle rectangle, int i)
    {
        rectangle.setVisible(false);

        label.onMouseEnteredProperty().setValue(event -> {
            rectangle.setVisible(true);
            label.textFillProperty().setValue(Color.WHITE);
        });

        label.onMouseExitedProperty().setValue(event -> {
            rectangle.setVisible(false);
            label.textFillProperty().setValue(Color.BLACK);
        });

        label.setOnMousePressed(event ->
        {
            switch (i)
            {
                case 1:
                    System.exit(DISPOSE_ON_CLOSE);
                    break;
                case 2:
                    frame.setIconified(true);
                    break;
                default:
                    break;
            }
        });
    }

    public static void initialize(Pane panelSuperior)
    {
        panelSuperior.setOnMousePressed(event ->
        {
            posX = event.getSceneX();
            posY = event.getSceneY();
        });

        panelSuperior.setOnMouseDragged(event ->
        {
            frame.setX(event.getScreenX() - posX);
            frame.setY(event.getScreenY() - posY);
        });
    }
}
