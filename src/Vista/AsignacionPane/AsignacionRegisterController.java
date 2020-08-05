package Vista.AsignacionPane;

import Controlador.DataBase.Database;
import Vista.Table.TableController;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import static Vista.Table.TableController.*;
import static Vista.AsignacionPane.AsignacionUpdateController.updateExamenSelected;

public class AsignacionRegisterController
{
    public static ComboBox asignacionUsuarioCombobox;
    public static ComboBox asignacionExamenCombobox;
    public static ComboBox updateAsignacionExamenCombobox;

    private static String usuarioSelected = "";
    private static String exameneSelected = "";

    public static void initialize(ComboBox asignacionUsuarioCombobox1, ComboBox asignacionExamenCombobox1, Button asignacionRegisterButton)
    {
        asignacionUsuarioCombobox = asignacionUsuarioCombobox1;
        asignacionExamenCombobox = asignacionExamenCombobox1;

        initComboBox(asignacionUsuarioCombobox, comboBoxType.usuario);
        actualizarComboBox(asignacionUsuarioCombobox, usersData, 3);

        initComboBox(asignacionExamenCombobox, comboBoxType.examen);
        actualizarComboBox(asignacionExamenCombobox, ExamData, 1);

        asignacionRegisterButton.onActionProperty().setValue((ActionEvent event) ->
                registrar());
    }

    public static void initialize(ComboBox updateAsignacionExamenCombobox1)
    {
        updateAsignacionExamenCombobox = updateAsignacionExamenCombobox1;
        initComboBox(updateAsignacionExamenCombobox, comboBoxType.update);
        actualizarComboBox(updateAsignacionExamenCombobox, ExamData, 1);
    }

    private static void registrar()
    {
        if(!usuarioSelected.equals("") && !exameneSelected.equals(""))
        {
            Database db = new Database();
            db.conectar();
            String idUser = db.getStringAt("idUser", "User", "username", usuarioSelected);
            String idExamen = db.getStringAt("idExamen", "Examen", "titulo", exameneSelected);
            db.executeInsert(
                    "insert into bfkbonwrvl7atwiehbto.Asignacion (idUser, idExamen, estado) " +
                    "values ("+idUser+","+idExamen+", false);");
            TableController.actualizarTablaAsignacion(db);
            db.cerrarConexion();
        }
    }


    private static void initComboBox(ComboBox comboBox, comboBoxType type)
    {
       comboBox.getSelectionModel().selectedItemProperty().addListener(
               (ObservableValue options, Object oldValue, Object newValue) ->
               {
                   switch (type)
                   {
                       case usuario:
                           usuarioSelected = newValue.toString();
                           break;
                       case examen:
                           exameneSelected = newValue.toString();
                           break;
                       case update:
                           updateExamenSelected = newValue.toString();
                           break;
                       default:
                           break;
                   }
               });
    }

    public static void actualizarExamenCombobox()
    {
        actualizarComboBox(asignacionExamenCombobox, ExamData, 1);
        actualizarComboBox(updateAsignacionExamenCombobox, ExamData, 1);
    }

    public static void actualizarUserCombobox()
    {
        actualizarComboBox(asignacionUsuarioCombobox, usersData, 3);
    }

    private static void actualizarComboBox(ComboBox comboBox, String[][] data, int indexColumn)
    {
        ObservableList<String> IDs = FXCollections.observableArrayList();
        for(int i = 0; i < data.length; i++)
            IDs.add(data[i][indexColumn]);
        comboBox.itemsProperty().setValue(IDs);
    }

    enum comboBoxType
    {
        usuario, examen, update
    }
}
