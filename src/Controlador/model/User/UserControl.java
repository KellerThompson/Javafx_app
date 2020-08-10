package Controlador.model.User;

import Controlador.DataBase.Database;
import Controlador.Util.UtilK;
import Controlador.model.Persona.Persona;
import Controlador.model.Persona.PersonaControl;
import Controlador.model.Resultados.ResultadosControl;

import javax.xml.crypto.Data;
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

    public static User registrar(Database db, Persona persona, String username, String password)
    {
        String date = Date.valueOf(LocalDate.now()).toString();
        db.executeInsert("insert into bfkbonwrvl7atwiehbto.User " +
                "(idPersona, username, password, fechaRegistro) " +
                "values ("+persona.getId()+", '"+username+"', '"+password+"', '"+ date +"');");
        int idUsuario = db.getLastIDInserted(tabla, primaryKey);
        return new User(idUsuario, persona, username,password, date);
    }

    private static User getByID(Database db, int idUser)
    {
        db.executeQuery("SELECT * FROM "+Database.dataBaseName+"."+tabla+" where "+primaryKey+" = "+idUser+";");
        String[][] userString = db.obtenerDatosTabla();
        return new User(Integer.parseInt(userString[0][0]), PersonaControl.getByID(db, Integer.parseInt(userString[0][1])),
                userString[0][2], userString[0][3], userString[0][4]);
    }

    public static User getByID(int iduser)
    {
        Database db = new Database();
        db.conectar("UserControl.getByID");
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

    public static String[][] consultarUsuarios(Database db)
    {
        db.executeQuery(
                "select Persona.idPersona, Persona.name, Persona.lastname, User.username, User.password, " +
                "User.fechaRegistro from Persona " +
                "inner join User on Persona.idPersona = User.idPersona;");
        String[][] tabla = db.obtenerDatosTabla();

        return tabla;
    }

    public static User updateUsername(int idPersona, String newUsername)
    {
        Database db = new Database();
        db.conectar("UserControl.updateusername");
        db.executeQuery("select idUser from bfkbonwrvl7atwiehbto.User where User.idPersona = "+idPersona+";");
        String[][] idUser = db.obtenerDatosTabla();
        db.executeInsert(
                "update "+Database.dataBaseName+"."+tabla+" set "+usernameColumn+" = '"+newUsername+
                        "' where "+primaryKey+" = "+idUser[0][0]+";");
        User user = getByID(db, Integer.parseInt(idUser[0][0]));
        db.cerrarConexion();
        return user;
    }

    public static User updateUsername(Database db, int idPersona, String newUsername)
    {
        db.executeQuery("select idUser from bfkbonwrvl7atwiehbto.User where User.idPersona = "+idPersona+";");
        String[][] idUser = db.obtenerDatosTabla();
        db.executeInsert(
                "update "+Database.dataBaseName+"."+tabla+" set "+usernameColumn+" = '"+newUsername+
                        "' where "+primaryKey+" = "+idUser[0][0]+";");
        User user = getByID(db, Integer.parseInt(idUser[0][0]));
        return user;
    }

    public static User updatePassword(int idPersona, String newPassword)
    {
        Database db = new Database();
        db.conectar("UserControl.updatePassword");
        db.executeQuery("select idUser from bfkbonwrvl7atwiehbto.User where User.idPersona = "+idPersona+";");
        String[][] idUser = db.obtenerDatosTabla();
        db.executeInsert(
                "update "+Database.dataBaseName+"."+tabla+" set "+passwordColumn+" = '"+newPassword+
                        "' where "+primaryKey+" = "+idUser[0][0]+";");
        User user = getByID(db, Integer.parseInt(idUser[0][0]));
        db.cerrarConexion();
        return user;
    }

    public static User updatePassword(Database db, int idPersona, String newPassword)
    {
        db.executeQuery("select idUser from bfkbonwrvl7atwiehbto.User where User.idPersona = "+idPersona+";");
        String[][] idUser = db.obtenerDatosTabla();
        db.executeInsert(
                "update "+Database.dataBaseName+"."+tabla+" set "+passwordColumn+" = '"+newPassword+
                        "' where "+primaryKey+" = "+idUser[0][0]+";");
        User user = getByID(db, Integer.parseInt(idUser[0][0]));
        return user;
    }
}
