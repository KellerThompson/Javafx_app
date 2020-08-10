package Vista.UserPane;

import Controlador.DataBase.Database;
import Controlador.model.Persona.PersonaControl;
import Controlador.model.User.UserControl;
import Vista.AsignacionPane.AsignacionRegisterController;
import Vista.Table.TableController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UpdateController
{
    public static int idPersonaSelected;
    public static Label nombrelabel;
    public static Label apellidolabel;
    public static Label usernamelabel;
    public static Label passwordlabel;

    public static void initialize(Label nombrelabel1, Label apellidolabel1, Label usernamelabel1, Label passwordlabel1, Button deleteButton)
    {
        nombrelabel = nombrelabel1;
        apellidolabel = apellidolabel1;
        usernamelabel = usernamelabel1;
        passwordlabel = passwordlabel1;

        initDeleteButton(deleteButton);
    }

    private static void initDeleteButton(Button deleteButton)
    {
           deleteButton.onActionProperty().setValue(event ->
           {
               if(idPersonaSelected > 0)
               {
                   Database db = new Database();
                   db.conectar("UpdateController.initDeleteButton()");
                   PersonaControl.delete(db, idPersonaSelected);
                   TableController.actualizarTablaUser(db);
                   db.cerrarConexion();
                   AsignacionRegisterController.actualizarUserCombobox();
                   idPersonaSelected = -1;
                   nombrelabel.setText("");
                   apellidolabel.setText("");
                   usernamelabel.setText("");
                   passwordlabel.setText("");
               }
           });
    }

    public static void initUpdate(Button button, TextField textField, updateType type)
    {
        button.onActionProperty().setValue(event -> {
            if(!textField.getText().equals(""))
            {
                Database db = new Database();
                db.conectar("UpdateController.initUpdate");
                switch (type)
                {
                    case name:
                        PersonaControl.updateName(db, idPersonaSelected, textField.getText());
                        nombrelabel.setText(textField.getText());
                        break;
                    case lastname:
                        PersonaControl.updateLastname(db, idPersonaSelected, textField.getText());
                        apellidolabel.setText(textField.getText());
                        break;
                    case username:
                        UserControl.updateUsername(db, idPersonaSelected, textField.getText());
                        usernamelabel.setText(textField.getText());
                        AsignacionRegisterController.actualizarUserCombobox();
                        break;
                    case password:
                        UserControl.updatePassword(db, idPersonaSelected, textField.getText());
                        passwordlabel.setText(textField.getText());
                        break;
                    default:
                        break;
                }
                TableController.actualizarTablaUser(db);
                db.cerrarConexion();
            }
            textField.setText("");
        });
    }

    public enum updateType
    {
        name, lastname, username, password
    }
}
