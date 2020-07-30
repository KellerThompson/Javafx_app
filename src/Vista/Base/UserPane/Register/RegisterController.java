package Vista.Base.UserPane.Register;

import Controlador.model.Persona.PersonaControl;
import Controlador.model.User.UserControl;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;

import javax.swing.*;
import static Vista.Inicio.frame;

public class RegisterController
{
    public static void init(JFXTextField nombreTextfield, JFXTextField apellidoTextfield,
                            JFXTextField usernameTextfield, JFXTextField passwordTextfield,
                            Button registerUserButton)
    {

        setValidators(nombreTextfield);
        setValidators(apellidoTextfield);
        setValidators(usernameTextfield);
        setValidators(passwordTextfield);

        registerUserButton.onActionProperty().setValue(event -> {
            registrar(nombreTextfield, apellidoTextfield, usernameTextfield, passwordTextfield);
        });
    }

    private static void registrar(JFXTextField nombreTextfield, JFXTextField apellidoTextfield,
                                  JFXTextField usernameTextfield, JFXTextField passwordTextfield)
    {

        try
        {
            if(nombreTextfield.validate() && apellidoTextfield.validate() && usernameTextfield.validate() && passwordTextfield.validate())
            {
                String nombre = nombreTextfield.getText();
                String apellidos = apellidoTextfield.getText();
                String username = usernameTextfield.getText();
                String password = passwordTextfield.getText();

                System.out.println(UserControl.registrar(PersonaControl.registrar(nombre, apellidos), username, password).toString());

                nombreTextfield.setText("");
                apellidoTextfield.setText("");
                usernameTextfield.setText("");
                passwordTextfield.setText("");
            }
            else
            {
                System.out.println("No valido");
            }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private static void setValidators(JFXTextField textField)
    {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        textField.getValidators().add(validator);
        validator.setMessage("Campo obligatorio");
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
