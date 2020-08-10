package Vista.Table;

import Controlador.DataBase.Database;
import Controlador.model.User.UserControl;
import Vista.ResultadoPane.ResultController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import static Vista.UserPane.UpdateController.idPersonaSelected;
import static Vista.UserPane.UpdateController.nombrelabel;
import static Vista.UserPane.UpdateController.apellidolabel;
import static Vista.UserPane.UpdateController.usernamelabel;
import static Vista.UserPane.UpdateController.passwordlabel;

import static Vista.ExamenPane.UpdateExamenController.idExamenSelected;
import static Vista.ExamenPane.UpdateExamenController.linklabel;
import static Vista.ExamenPane.UpdateExamenController.titulolabel;

import static Vista.AsignacionPane.AsignacionUpdateController.idAsignacionSelected;
import static Vista.AsignacionPane.AsignacionUpdateController.examenAsignadoLabel;

public class TableController
{
    public static TableView tablaUsuarios;
    public static String[] columnasUser = {"ID Persona", "Nombre", "Apellidos", "Usuario", "Password", "Registro"};
    public static String[][] usersData;

    public static TableView tablaExamen;
    public static String[] columnasExamen = {"ID Examen", "Titulo", "Link"};
    public static String[][] ExamData;

    public static TableView tablaAsignacion;
    public static String[] columnasAsignacion = {"ID Asignacion", "User", "Examen", "Estado"};
    public static String[][] AsignacionData;

    public static String[][] MateriasData;

    public static void initTables(TableView tablaUsuer, TableView tablaExam, TableView tablaAsig)
    {
        tablaUsuarios = tablaUsuer;
        tablaExamen = tablaExam;
        tablaAsignacion = tablaAsig;

        addColumns(tablaUsuarios, columnasUser);
        addColumns(tablaExam, columnasExamen);
        addColumns(tablaAsignacion, columnasAsignacion);

        Database db = new Database();
        db.conectar("TableController.initTables");
        actualizarTablaUser(db);
        actualizarTablaExamen(db);
        actualizarTablaAsignacion(db);
        db.executeQuery("SELECT Materia FROM bfkbonwrvl7atwiehbto.Materias;");
        MateriasData = db.obtenerDatosTabla();
        db.cerrarConexion();

        tablaUsuer.setRowFactory(tv ->
        {
            TableRow<String> row = new TableRow<>();
            row.setOnMouseClicked(event ->
            {
                String[] values = tablaUsuer.getSelectionModel().getSelectedItem().toString().split(",");
                idPersonaSelected = Integer.parseInt(values[0].substring(1));
                nombrelabel.setText(values[1]);
                apellidolabel.setText(values[2]);
                usernamelabel.setText(values[3]);
                passwordlabel.setText(values[4]);
            });
            return row;
        });

        tablaExam.setRowFactory(tv ->
        {
            TableRow<String> row = new TableRow<>();
            row.setOnMouseClicked(event ->
            {
                String[] values = tablaExam.getSelectionModel().getSelectedItem().toString().split(",");
                idExamenSelected = Integer.parseInt(values[0].substring(1));
                titulolabel.setText(values[1]);
                linklabel.setText(values[2].substring(0, values[2].length() - 1));
            });
            return row;
        });

        tablaAsig.setRowFactory(tv ->
        {
            TableRow<String> row = new TableRow<>();
            row.setOnMouseClicked(event ->
            {
                String[] values = tablaAsig.getSelectionModel().getSelectedItem().toString().split(",");
                idAsignacionSelected = Integer.parseInt(values[0].substring(1));
                examenAsignadoLabel.setText(values[2]);
            });
            return row;
        });
    }

    public static void actualizarTablaUser(Database db)
    {
        usersData = UserControl.consultarUsuarios(db);
        actualizarTabla(tablaUsuarios, usersData);
    }

    public static void actualizarTablaExamen(Database db)
    {
        db.executeQuery("SELECT * FROM bfkbonwrvl7atwiehbto.Examen;");
        ExamData = db.obtenerDatosTabla();
        actualizarTabla(tablaExamen, ExamData);
    }

    public static void actualizarTablaAsignacion(Database db)
    {
        db.executeQuery(
                "select Asignacion.idAsignacion, User.username, Examen.titulo, Asignacion.estado " +
                        "from bfkbonwrvl7atwiehbto.Asignacion " +
                        "inner join User on Asignacion.idUser = User.idUser " +
                        "inner join Examen on Asignacion.idExamen = Examen.idExamen;");
        AsignacionData = db.obtenerDatosTabla();
        for(int i = 0; i < AsignacionData.length; i++)
        {
            if(AsignacionData[i][3].equals("1"))
                AsignacionData[i][3] = "Hecho";
            else
                AsignacionData[i][3] = "Sin realizar";
        }
        actualizarTabla(tablaAsignacion, AsignacionData);
    }

    private static void actualizarTabla(TableView tabla, String[][] datos)
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
