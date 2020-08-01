package Vista.Base.UserPane;

import Controlador.model.User.UserControl;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import static Vista.Base.UserPane.UpdateController.idPersonaSelected;
import static Vista.Base.UserPane.UpdateController.nombrelabel;
import static Vista.Base.UserPane.UpdateController.apellidolabel;
import static Vista.Base.UserPane.UpdateController.usernamelabel;
import static Vista.Base.UserPane.UpdateController.passwordlabel;

public class TableController
{
    public static TableView tablaUsuarios;
    public static String[] columnasUser = {"ID", "Nombre", "Apellidos", "Usuario", "Password", "Examen", "Registro"};
    public static String[][] usersTable;

    public static void initTableUser(TableView tabla)
    {
        tablaUsuarios = tabla;

        usersTable = UserControl.consultarUsuarios();
        addColumns(tablaUsuarios, columnasUser);
        actualizarTabla(tablaUsuarios, usersTable);
        tabla.setRowFactory(tv ->
        {
            TableRow<String> row = new TableRow<>();
            row.setOnMouseClicked(event ->
            {
                String[] values = tabla.getSelectionModel().getSelectedItem().toString().split(",");
                idPersonaSelected = Integer.parseInt(values[0].substring(1, values[0].length()));
                nombrelabel.setText(values[1]);
                apellidolabel.setText(values[2]);
                usernamelabel.setText(values[3]);
                passwordlabel.setText(values[4]);
            });
            return row;
        });
    }

    public static void actualizarTabla(TableView tabla, String[][] datos)
    {
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        for(int i = 0; i < datos.length; i++) // filas
        {
            ObservableList<String> row = FXCollections.observableArrayList();
            for(int e = 0; e < datos[0].length; e++) //columnas
            {
                row.add(datos[i][e]);
            }
            data.add(row);
        }
        tabla.setItems(data);
    }

    private static void addColumns(TableView tabla, String[] column)
    {
        for (int i = 0; i < column.length; i++)
        {
            final int j = i;

            TableColumn col = new TableColumn(column[i]);
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>()
            {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param)
                {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });

            tabla.getColumns().addAll(col);
        }
    }
}
