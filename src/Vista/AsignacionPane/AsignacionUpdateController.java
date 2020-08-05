package Vista.AsignacionPane;

import Controlador.DataBase.Database;
import Vista.Table.TableController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AsignacionUpdateController
{

    public static int idAsignacionSelected;
    public static Label examenAsignadoLabel;
    public static String updateExamenSelected;

    public static void initialize(Button deleteAsignacionButton, Label examenAsignadoLabel1)
    {
        examenAsignadoLabel = examenAsignadoLabel1;
        initDeleteButton(deleteAsignacionButton);
    }

    private static void initDeleteButton(Button deleteButton)
    {
        deleteButton.onActionProperty().setValue(event ->
        {
            if(idAsignacionSelected > 0)
            {
                Database db = new Database();
                db.conectar();
                db.borrarRegistro("Asignacion", "idAsignacion", idAsignacionSelected+"");
                TableController.actualizarTablaAsignacion(db);
                db.cerrarConexion();
                idAsignacionSelected = -1;
                examenAsignadoLabel.setText("");
            }
        });
    }

    public static void initUpdate(Button button)
    {
        button.onActionProperty().setValue(event ->
        {
            Database db = new Database();
            db.conectar();
            String idExamen = db.getStringAt("idExamen", "Examen", "titulo", updateExamenSelected);
            db.actualizarCampo("Asignacion", "idExamen", idExamen, "idAsignacion", idAsignacionSelected+"");
            TableController.actualizarTablaAsignacion(db);
            db.cerrarConexion();
            examenAsignadoLabel.setText(updateExamenSelected);
        });
    }
}
