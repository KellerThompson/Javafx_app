package Vista.ResultadoPane;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import static Vista.Table.TableController.*;

public class ResultController
{
    private static ComboBox resultExamenCombobox;
    private static ComboBox resultUserCombobox;
    private static ComboBox resultMateriaCombobox;

    private static String examenSelected = "Todos";
    private static String userSelected = "Todos";
    private static String materiaSelected = "Todos";

    public static void initialize(ComboBox resultExamenCombobox1, ComboBox resultUserCombobox1,
                                  ComboBox resultMateriaCombobox1, Button updateGrafica)
    {
        resultExamenCombobox = resultExamenCombobox1;
        resultUserCombobox = resultUserCombobox1;
        resultMateriaCombobox = resultMateriaCombobox1;

        initComboBox(resultExamenCombobox, comboBoxType.examen);
        actualizarComboBox(resultExamenCombobox, ExamData, 1);

        initComboBox(resultUserCombobox, comboBoxType.user);
        actualizarComboBox(resultUserCombobox, usersData, 3);

        initComboBox(resultMateriaCombobox, comboBoxType.materia);
        actualizarComboBox(resultMateriaCombobox, MateriasData, 0);

        updateGrafica.onActionProperty().setValue((ActionEvent event) ->
                GraphController.updateGraph(examenSelected, userSelected, materiaSelected));
    }

    private static void initComboBox(ComboBox comboBox, comboBoxType type)
    {
        comboBox.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue options, Object oldValue, Object newValue) ->
                {
                    switch (type)
                    {
                        case examen:
                            examenSelected = newValue.toString();
                            break;
                        case user:
                            userSelected = newValue.toString();
                            break;
                        case materia:
                            materiaSelected = newValue.toString();
                            break;
                        default:
                            break;
                    }
                });
    }

    public static void actualizarResultExamenComboBox()
    {
        actualizarComboBox(resultExamenCombobox, ExamData, 1);
    }

    public static void actualizarResultUserComboBox()
    {
        actualizarComboBox(resultUserCombobox, usersData, 3);
    }

    private static void actualizarComboBox(ComboBox comboBox, String[][] data, int indexColumn)
    {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.add("Todos");
        for(int i = 0; i < data.length; i++)
            items.add(data[i][indexColumn]);
        comboBox.itemsProperty().setValue(items);
        comboBox.getSelectionModel().selectFirst();
    }

    enum comboBoxType
    {
        examen, user, materia
    }
}
