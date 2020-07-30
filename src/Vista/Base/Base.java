package Vista.Base;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Base extends Stage
{

    public static String titulo;

    public Base(String title) throws IOException
    {
        titulo = title;
        Parent root = FXMLLoader.load(getClass().getResource("Base.fxml"));

        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("BaseStyles.css").toExternalForm());

        this.initStyle(StageStyle.UNDECORATED);
        this.setTitle(title);
        this.resizableProperty().set(false);
        this.setScene(scene);
    }
}
