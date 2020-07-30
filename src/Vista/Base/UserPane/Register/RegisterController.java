package Vista.Base.UserPane.Register;

import Controlador.model.Persona.PersonaControl;
import Controlador.model.User.UserControl;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Button;

import javax.swing.*;

public class RegisterController
{
    public static void init(JFXTextField nombreTextfield, JFXTextField apellidoTextfield,
                            JFXTextField usernameTextfield, JFXTextField passwordTextfield,
                            Button registerUserButton)
    {
        registerUserButton.onActionProperty().setValue(event ->
        {
            String nombre = nombreTextfield.getText();
            String apellidos = apellidoTextfield.getText();
            String username = usernameTextfield.getText();
            String password = passwordTextfield.getText();
            try
            {
                System.out.println(UserControl.registrar(PersonaControl.registrar(nombre, apellidos), username, password).toString());
                nombreTextfield.setText("");
                apellidoTextfield.setText("");
                usernameTextfield.setText("");
                passwordTextfield.setText("");
                JOptionPane.showMessageDialog(null, "Registro Completo");
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex);
            }
        });
    }
}
