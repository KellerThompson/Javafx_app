package Controlador.model.User;

import Controlador.DataBase.Database;
import Controlador.model.Persona.Persona;
import Controlador.model.Persona.PersonaControl;
import Controlador.model.Resultados.ResultadosControl;

import java.sql.Date;
import java.time.LocalDate;

public class UserControl
{
    private static String tabla = "User";
    private static String primaryKey = "idUser";
    private static String idPersonaColumn = "idPersona";
    private static String usernameColumn = "username";
    private static String passwordColumn = "password";
    private static String examenColumn = "examen";
    private static String fechaColumn = "fechaRegistro";

    public static User registrar(Persona persona, String username, String password)
    {
        String date = Date.valueOf(LocalDate.now()).toString();
        Database db = new Database();
        db.conectar();
        db.executeInsert("insert into bfkbonwrvl7atwiehbto.User " +
                "(idPersona, username, password, examen, fechaRegistro) " +
                "values ("+persona.getId()+", '"+username+"', '"+password+"', " + false + ", '"+ date +"');");
        int idUsuario = db.getLastIDInserted(tabla, primaryKey);
        db.cerrarConexion();
        return new User(idUsuario, persona, username,password, false, date);
    }

    private static User getByID(Database db, int idUser)
    {
        db.executeQuery("SELECT * FROM "+Database.dataBaseName+"."+tabla+" where "+primaryKey+" = "+idUser+";");
        String[][] userString = db.obtenerDatosTabla();
        return new User(Integer.parseInt(userString[0][0]), PersonaControl.getByID(Integer.parseInt(userString[0][1])),
                userString[0][2], userString[0][3], Boolean.parseBoolean(userString[0][4]), userString[0][5]);
    }

    public static User getByID(int iduser)
    {
        Database db = new Database();
        db.conectar();
        User user = getByID(db, iduser);
        db.cerrarConexion();
        return user;
    }

    public static void delete(Database db, int idUser)
    {
        ResultadosControl.deleteUsersByIdUser(db, idUser);
        db.borrarRegistro(tabla, primaryKey, idUser+"");
    }

    public static void deleteUsersByIdPersona(Database db, int idPersona)
    {
        db.executeQuery("SELECT "+primaryKey+" FROM "+Database.dataBaseName+"."+tabla+" " +
                "where "+idPersonaColumn+" = "+idPersona+";");
        String[][] tabla = db.obtenerDatosTabla();

        for(int i = 0; i < tabla.length; i++)
        {
            System.out.println("Delete user: " + tabla[i][0]);
            delete(db, Integer.parseInt(tabla[i][0]));
        }
    }

    public static String[][] consultarUsuarios()
    {
        Database db = new Database();
        db.conectar();
        db.executeQuery("select Persona.idPersona, Persona.name, Persona.lastname, User.username, User.password, " +
                "User.examen, User.fechaRegistro from Persona " +
                "inner join User on Persona.idPersona = User.idPersona;");
        String[][] tabla = db.obtenerDatosTabla();
        db.cerrarConexion();
        return tabla;
    }
}
