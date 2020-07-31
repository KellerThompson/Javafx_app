package Vista.Base.UserPane.Consulta;

import Controlador.model.User.UserControl;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class userTableController
{
    private static TableView tablaUsuarios;
    private static String[] columnNames = {"Nombre", "Apellidos", "Usuario", "Password", "Examen", "Registro"};

    public static void initialize(TableView tabla)
    {
        tablaUsuarios = tabla;
        tablaUsuarios.setEditable(false);

        addColumns(columnNames);
        actualizarTabla();
    }

    private static void actualizarTabla()
    {
        ObservableList<ObservableList> data = FXCollections.observableArrayList();

        String[][] users = UserControl.consultarUsuarios();
        for(int i = 0; i < users.length; i++) // filas
        {
            ObservableList<String> row = FXCollections.observableArrayList();
            for(int e = 0; e < users[0].length; e++) //columnas
            {
                row.add(users[i][e]);
            }
            row.forEach(value -> System.out.print(value + " "));
            System.out.println();
            data.add(row);
        }
        tablaUsuarios.setItems(data);
    }

    private static void addColumns(String[] column)
    {
        for (int i = 0; i < column.length; i++)
        {
            final int j = i;

            /*
            TableColumn<ObservableList<String>, String> col = new TableColumn<>(columnNames[i]);
            col.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>());
            */
            TableColumn col = new TableColumn(column[i]);
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>()
            {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param)
                {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });

            tablaUsuarios.getColumns().addAll(col);
        }
    }
}
