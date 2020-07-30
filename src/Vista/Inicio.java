package Vista;

import Vista.Base.Base;
import javafx.application.Application;
import javafx.stage.Stage;

public class Inicio extends Application
{
    public static Base frame;

    public static void main(String[] args) {
        launch(args); // metodo Start
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        try
        {
            frame = new Base("Base de Datos");
            frame.show();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
