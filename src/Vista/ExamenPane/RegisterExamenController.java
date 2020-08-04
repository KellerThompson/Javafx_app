package Vista.ExamenPane;

import Controlador.DataBase.Database;
import Vista.AsignacionPane.AsignacionRegisterController;
import Vista.UserPane.RegisterController;
import Vista.Table.TableController;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class RegisterExamenController
{

    public static void initialize(JFXTextField examenLinkTextfield, JFXTextField examenTituloTextfield, Button examenRegisterButton)
    {
        RegisterController.setValidators(examenLinkTextfield);
        RegisterController.setValidators(examenTituloTextfield);
        examenRegisterButton.onActionProperty().setValue((ActionEvent event) ->
                registrar(examenLinkTextfield, examenTituloTextfield));
    }

    private static void registrar(JFXTextField examenLinkTextfield, JFXTextField examenTituloTextfield)
    {
        if(examenLinkTextfield.validate() && examenTituloTextfield.validate())
        {
            String link = examenLinkTextfield.getText();
            String titulo = examenTituloTextfield.getText();

            Database db = new Database();
            db.conectar();
            db.executeInsert("insert into bfkbonwrvl7atwiehbto.Examen (titulo, link) values ('"+titulo+"','"+link+"');");
            examenLinkTextfield.setText("");
            examenTituloTextfield.setText("");
            TableController.actualizarTablaExamen(db);
            db.cerrarConexion();
            AsignacionRegisterController.actualizarExamenCombobox();
        }
    }
}
