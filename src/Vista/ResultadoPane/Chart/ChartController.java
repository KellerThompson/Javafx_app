package Vista.ResultadoPane.Chart;

import Controlador.DataBase.Database;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.Arrays;

public class ChartController
{
    private static BarChart barChart;
    private static CategoryAxis categoryAxis;
    private static NumberAxis numberAxis;

    public static void initialize(BarChart graph, CategoryAxis categoryAxis1, NumberAxis numberAxis1)
    {
        barChart = graph;
        barChart.setLegendVisible(false);

        categoryAxis = categoryAxis1;
        categoryAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16")));
        numberAxis = numberAxis1;
    }

    public static void updateGraph(String examenSelected, String materiaSelected)
    {

        Database db = new Database();
        db.conectar("ChartController.updateGraph");
        int idExamen = db.getIntegerAt("idExamen", "Examen", "titulo", examenSelected);
        int idMateria = db.getIntegerAt("idMaterias", "Materias", "Materia", materiaSelected);
        setSeries(db, idExamen, idMateria);
        db.cerrarConexion();
    }

    private static void setSeries(Database db, int idExamen, int idMateria)
    {
        barChart.getData().clear();
        categoryAxis.getCategories().clear();

        if(idMateria == 1 || idMateria == 3)
        {
            categoryAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
                    "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16")));
        }
        else
            categoryAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
                    "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")));

        XYChart.Series series1 = new XYChart.Series();

        String[][] errores = getErrores(db, idExamen, idMateria);
        for(int i = 0; i < errores.length; i++)
        {
            series1.getData().add(new XYChart.Data(errores[i][0], Double.parseDouble(errores[i][1])));
        }
        barChart.getData().add(series1);

        for(Node n:barChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: #29574E;");
        }
    }

    private static String[][] getErrores(Database db, int idExamen, int idMateria)
    {
        db.executeQuery(
                "select Resultados.numPregunta, " +
                "(1 - (SUM(Resultados.resultado)/count(Resultados.resultado))) as Error " +
                "from bfkbonwrvl7atwiehbto.Resultados " +
                "where Resultados.idExamen = "+idExamen+" and Resultados.idMateria = "+idMateria+" group by Resultados.numPregunta;");
        return db.obtenerDatosTabla();
    }
}
