package Vista.ExamenPane;

import Controlador.DataBase.Database;
import Vista.AsignacionPane.AsignacionRegisterController;
import Vista.Table.TableController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UpdateExamenController
{
    public static int idExamenSelected;
    public static Label linklabel;
    public static Label titulolabel;

    public static void initialize(Label linklabel1, Label titulolabel1, Button deleteButto)
    {
        linklabel = linklabel1;
        titulolabel = titulolabel1;

        initDeleteButton(deleteButto);
    }

    private static void initDeleteButton(Button deleteButton)
    {
        deleteButton.onActionProperty().setValue(event ->
        {
            if(idExamenSelected > 0)
            {
                Database db = new Database();
                db.conectar();
                db.borrarRegistro("Examen", "idExamen", idExamenSelected + "");
                db.executeQuery("SELECT * FROM bfkbonwrvl7atwiehbto.Examen;");
                TableController.actualizarTablaExamen(db);
                db.cerrarConexion();
                AsignacionRegisterController.actualizarExamenCombobox();
                //idExamenSelected = -1;
                linklabel.setText("");
            }
        });
    }

    public static void initUpdate(Button button, TextField textField, updateType updateType)
    {
        button.onActionProperty().setValue(event -> {
            if(!textField.getText().equals(""))
            {
                Database db = new Database();
                db.conectar();
                switch (updateType)
                {
                    case titulo:
                        db.actualizarCampo("Examen", "titulo", textField.getText(), "idExamen", idExamenSelected+"");
                        titulolabel.setText(textField.getText());
                        AsignacionRegisterController.actualizarExamenCombobox();
                        break;
                    case link:
                        db.actualizarCampo("Examen", "link", textField.getText(), "idExamen", idExamenSelected+"");
                        linklabel.setText(textField.getText());
                        break;
                    default:
                        break;
                }
                TableController.actualizarTablaExamen(db);
                db.cerrarConexion();
            }
            textField.setText("");
        });
    }

    public enum updateType
    {
        titulo, link
    }
}
