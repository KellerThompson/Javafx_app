package Vista.Base;

import Vista.AsignacionPane.AsignacionRegisterController;
import Vista.AsignacionPane.AsignacionUpdateController;
import Vista.ResultadoPane.Chart.ChartController;
import Vista.ResultadoPane.ResultController;
import Vista.Table.TableController;
import Vista.ExamenPane.RegisterExamenController;
import Vista.ExamenPane.UpdateExamenController;
import Vista.Base.SelectionPane.SelectionPane;
import Vista.Base.PanelSuperior.PanelSuperior;
import Vista.UserPane.RegisterController;
import Vista.UserPane.UpdateController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import static Vista.UserPane.UpdateController.updateType.*;
import static Vista.ExamenPane.UpdateExamenController.updateType.*;

public class BaseController
{
    //----- Panel Superior -----------------------------------------
    @FXML public Pane panelSuperior;
    @FXML public Label labelX;
    @FXML public Rectangle shadowLabelX;
    @FXML public Label labelM;
    @FXML public Rectangle shadowLabelM;


    //---- Panel de seleccion ---------------------------------------
    @FXML private JFXButton userButton;
    @FXML private JFXButton examenButton;
    @FXML private JFXButton asignacionButton;
    @FXML private JFXButton resultButton;


    //----- Panel Usuarios -----------------------------------------
    @FXML private Pane userPane;

    @FXML private JFXTextField nombreTextfield;
    @FXML private JFXTextField apellidoTextfield;
    @FXML private JFXTextField usernameTextfield;
    @FXML private JFXTextField passwordTextfield;
    @FXML private Button registerUserButton;

    @FXML private TableView tablaUsuarios;

    @FXML private Label nombrelabel;
    @FXML private Label apellidolabel;
    @FXML private Label usernamelabel;
    @FXML private Label passwordlabel;
    @FXML private TextField updateNameTextfield;
    @FXML private Button updateNameButton;
    @FXML private TextField updateApellidposTextfield;
    @FXML private Button updateApellidposButton;
    @FXML private TextField updateUserameTextfield;
    @FXML private Button updateUserameButton;
    @FXML private TextField updatePasswordTextfield;
    @FXML private Button updatePaswordButton;

    @FXML private Button deleteButton;

    //----- Panel Examen -----------------------------------------
    @FXML private Pane examenPane;

    @FXML private JFXTextField examenLinkTextfield;
    @FXML private JFXTextField examenTituloTextfield;
    @FXML private Button examenRegisterButton;

    @FXML private TableView tablaExamenes;

    @FXML private Label linklabel;
    @FXML private TextField updateLinkTextfield;
    @FXML private Button updatelinkButton;
    @FXML private Label titulolabel;
    @FXML private TextField updatetituloTextfield;
    @FXML private Button updatetituloButton;

    @FXML private Button deleteLinkButton;

    //---- Panel Asignacion --------------------------------------
    @FXML private Pane asignacionPane;

    @FXML private ComboBox asignacionUsuarioCombobox;
    @FXML private ComboBox asignacionExamenCombobox;
    @FXML private Button asignacionRegisterButton;

    @FXML private TableView tablaAsignacion;

    @FXML private Label examenAsignadoLabel;
    @FXML private ComboBox updateAsignacionExamenCombobox;
    @FXML private Button updateAsignacionButton;

    @FXML private Button deleteAsignacionButton;

    //----- Panel Resultados ----------------------------------------
    @FXML private Pane resultsPane;

    @FXML private ComboBox resultExamenCombobox;
    @FXML private ComboBox resultMateriaCombobox;

    @FXML private Button updateGrafica;

    @FXML private BarChart barChart;
    @FXML private CategoryAxis categoryAxis;
    @FXML private NumberAxis numberAxis;

    public void initialize()
    {
        //------------------------------------- Panel Superior -----------------------------------------------------
        PanelSuperior.initialize(panelSuperior);
        PanelSuperior.initialize(labelX, shadowLabelX, 1);
        PanelSuperior.initialize(labelM, shadowLabelM, 2);

        //------------------------------------- Seleccion Pane -----------------------------------------------------
        SelectionPane
                .initialize(userButton, examenButton, asignacionButton, resultButton,
                        userPane, examenPane, asignacionPane, resultsPane);

        //------------------------------------- Init Tablas -------------------------------------------------------
        TableController.initTables(tablaUsuarios, tablaExamenes, tablaAsignacion);

        // ------------------------------------ User Pane ---------------------------------------------------------
        RegisterController
                .initialize(nombreTextfield, apellidoTextfield, usernameTextfield,
                        passwordTextfield, registerUserButton);

        UpdateController.initialize(nombrelabel, apellidolabel, usernamelabel, passwordlabel, deleteButton);
        UpdateController.initUpdate(updateNameButton, updateNameTextfield, name);
        UpdateController.initUpdate(updateApellidposButton, updateApellidposTextfield, lastname);
        UpdateController.initUpdate(updateUserameButton, updateUserameTextfield, username);
        UpdateController.initUpdate(updatePaswordButton, updatePasswordTextfield, password);

        //------------------------------------ Examen Pane --------------------------------------------------------
        RegisterExamenController.initialize(examenLinkTextfield, examenTituloTextfield, examenRegisterButton);

        UpdateExamenController.initialize(linklabel, titulolabel, deleteLinkButton);
        UpdateExamenController.initUpdate(updatelinkButton, updateLinkTextfield, link);
        UpdateExamenController.initUpdate(updatetituloButton, updatetituloTextfield, titulo);

        //-----------------------------------  Asignacion Pane ----------------------------------------------------
        AsignacionRegisterController
                .initialize(asignacionUsuarioCombobox, asignacionExamenCombobox, asignacionRegisterButton);

        AsignacionUpdateController.initialize(deleteAsignacionButton, examenAsignadoLabel);
        AsignacionRegisterController.initialize(updateAsignacionExamenCombobox);
        AsignacionUpdateController.initUpdate(updateAsignacionButton);

        //-----------------------------------  Resultado Pane ------------------------------------------------------

        ResultController.initialize(resultExamenCombobox, resultMateriaCombobox, updateGrafica);
        ChartController.initialize(barChart, categoryAxis, numberAxis);
    }
}


