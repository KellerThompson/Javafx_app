package Vista.ResultadoPane;

import Vista.AsignacionPane.AsignacionRegisterController;
import Vista.ResultadoPane.Chart.ChartController;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;


import static Vista.Table.TableController.*;

public class ResultController
{
    private static ComboBox resultExamenCombobox;
    private static ComboBox resultMateriaCombobox;

    private static String examenSelected = "";
    private static String materiaSelected = "Todos";

    public static void initialize(ComboBox resultExamenCombobox1, ComboBox resultMateriaCombobox1,
                                  Button updateGrafica)
    {
        resultExamenCombobox = resultExamenCombobox1;
        resultMateriaCombobox = resultMateriaCombobox1;

        initComboBox(resultExamenCombobox, comboBoxType.examen);
        actualizarResultExamenComboBox();
        try { examenSelected = ExamData[0][1]; }
        catch (Exception ex) { }

        initComboBox(resultMateriaCombobox, comboBoxType.materia);
        AsignacionRegisterController.actualizarComboBox(resultMateriaCombobox, MateriasData, 0);

        updateGrafica.onActionProperty().setValue((ActionEvent event) ->
                ChartController.updateGraph(examenSelected, materiaSelected));
    }

    private static void initComboBox(ComboBox comboBox, comboBoxType type)
    {
        comboBox.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue options, Object oldValue, Object newValue) ->
                {
                    switch (type)
                    {
                        case examen:
                            try {
                                examenSelected = newValue.toString();
                            } catch (Exception ex) { }
                            break;
                        case materia:
                            try {
                                materiaSelected = newValue.toString();
                            } catch (Exception ex) { }
                            break;
                        default:
                            break;
                    }
                });
    }

    public static void actualizarResultExamenComboBox()
    {
        AsignacionRegisterController.actualizarComboBox(resultExamenCombobox, ExamData, 1);
    }

    enum comboBoxType
    {
        examen, materia
    }
}
