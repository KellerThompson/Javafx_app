package Vista.Base.UserPane;

import Controlador.DataBase.Database;
import Controlador.model.Persona.PersonaControl;
import Controlador.model.User.UserControl;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static Vista.Base.UserPane.TableController.usersTable;
import static Vista.Base.UserPane.TableController.tablaUsuarios;

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
           deleteButton.onActionProperty().setValue(event -> {
               Database db = new Database();
               db.conectar();
               PersonaControl.delete(db, idPersonaSelected);
               usersTable = UserControl.consultarUsuarios(db);
               db.cerrarConexion();
               TableController.actualizarTabla(tablaUsuarios, usersTable);
               idPersonaSelected = -1;
               nombrelabel.setText("");
               apellidolabel.setText("");
               usernamelabel.setText("");
               passwordlabel.setText("");
           });
    }

    public static void initUpdate(Button button, TextField textField, updateType type)
    {
        button.onActionProperty().setValue(event -> {
            if(!textField.getText().equals(""))
            {
                Database db = new Database();
                db.conectar();
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
                        break;
                    case password:
                        UserControl.updatePassword(db, idPersonaSelected, textField.getText());
                        passwordlabel.setText(textField.getText());
                        break;
                    default:
                        break;
                }
                usersTable = UserControl.consultarUsuarios(db);
                db.cerrarConexion();
                TableController.actualizarTabla(tablaUsuarios, usersTable);
            }
            textField.setText("");
        });
    }

    public enum updateType
    {
        name, lastname, username, password
    }

    /*
    private static void initComboBox()
    {
       comboBox.getSelectionModel().selectedItemProperty().addListener(
               (ObservableValue options, Object oldValue, Object newValue) ->
                   System.out.println(newValue));
    }
    public static void actualizarComboBox()
    {
        ObservableList<String> IDs = FXCollections.observableArrayList();
        for(int i = 0; i < usersTable.length; i++)
            IDs.add(usersTable[i][0]);
        comboBox.itemsProperty().setValue(IDs);
    }
    */
}
