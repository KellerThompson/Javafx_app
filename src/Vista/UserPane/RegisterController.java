package Vista.UserPane;

import Controlador.DataBase.Database;
import Controlador.model.Persona.Persona;
import Controlador.model.Persona.PersonaControl;
import Controlador.model.User.UserControl;
import Vista.AsignacionPane.AsignacionRegisterController;
import Vista.Table.TableController;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class RegisterController
{
    public static void initialize(JFXTextField nombreTextfield, JFXTextField apellidoTextfield,
                                  JFXTextField usernameTextfield, JFXTextField passwordTextfield,
                                  Button registerUserButton)
    {
        setValidators(nombreTextfield);
        setValidators(apellidoTextfield);
        setValidators(usernameTextfield);
        setValidators(passwordTextfield);

        registerUserButton.onActionProperty().setValue((ActionEvent event) ->
            registrar(nombreTextfield, apellidoTextfield, usernameTextfield, passwordTextfield));
    }

    private static void registrar(JFXTextField nombreTextfield, JFXTextField apellidoTextfield,
                                  JFXTextField usernameTextfield, JFXTextField passwordTextfield)
    {
        if(nombreTextfield.validate() && apellidoTextfield.validate()
                && usernameTextfield.validate() && passwordTextfield.validate())
        {
            String nombre = nombreTextfield.getText();
            String apellidos = apellidoTextfield.getText();
            String username = usernameTextfield.getText();
            String password = passwordTextfield.getText();

            Database db = new Database();
            db.conectar();
            Persona persona = PersonaControl.registrar(db, nombre, apellidos);
            UserControl.registrar(db, persona, username, password);
            nombreTextfield.setText("");
            apellidoTextfield.setText("");
            usernameTextfield.setText("");
            passwordTextfield.setText("");
            TableController.actualizarTablaUser(db);
            db.cerrarConexion();
            AsignacionRegisterController.actualizarUserCombobox();
        }
    }

    public static void setValidators(JFXTextField textField)
    {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Campo obligatorio");
        textField.getValidators().add(validator);

        textField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {
                if(!newValue)
                {
                    textField.validate();
                }
            }
        });
    }
}
