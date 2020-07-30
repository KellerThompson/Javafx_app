package Vista.Base;


import java.awt.*;
//import javafx.scene.paint.Color;

public class ColorK
{
    public static Color principalDark;
    public static Color principal;
    public static Color principalLight;
    public static Color secondaryDark;
    public static Color secondary;
    public static Color secondaryLight;
    public static Color error;

    public static void initDefaultColor()
    {

        principalDark = new Color(16, 32, 39);
        principal = new Color(55, 71, 79);
        principalLight = new Color(98, 114, 123);

        secondaryDark = new Color(41, 87, 78);
        secondary = new Color(84, 130, 122);
        secondaryLight = new Color(129, 176, 169);

        error = new Color(230,38,39);
    }


    /*
    public static void initDefaultColor()
    {
        principalDark = new Color(16, 32, 39);
        principal = new Color(55, 71, 79);
        principalLight = new Color(98, 114, 123);

        secondaryDark = new Color(41, 87, 78);
        secondary = new Color(84, 130, 122);
        secondaryLight = new Color(129, 176, 169);

        error = new Color(230,38,39);
    }
    */
}
