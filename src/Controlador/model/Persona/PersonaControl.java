package Controlador.model.Persona;

import Controlador.DataBase.Database;
import Controlador.model.User.UserControl;

public class PersonaControl
{
    private static String tabla = "Persona";
    private static String primaryKey = "idPersona";
    private static String nameColumn = "name";
    private static String lastnameColumn = "lastname";

    public static Persona registrar(String name, String lastname)
    {
        Database db = new Database();
        db.conectar("PersonaControl.registrar");
        db.executeInsert(
                "insert into " + Database.dataBaseName + "." + tabla + " " +
                        "("+nameColumn+", "+lastnameColumn+") " +
                        "values ('"+name+"','"+lastname+"');");
        int idPersona = db.getLastIDInserted(tabla, primaryKey);
        db.cerrarConexion();
        return new Persona(idPersona, name, lastname);
    }

    public static Persona registrar(Database db, String name, String lastname)
    {
        db.executeInsert(
                "insert into " + Database.dataBaseName + "." + tabla + " " +
                        "("+nameColumn+", "+lastnameColumn+") " +
                        "values ('"+name+"','"+lastname+"');");
        int idPersona = db.getLastIDInserted(tabla, primaryKey);
        return new Persona(idPersona, name, lastname);
    }

    public static Persona getByID(Database db, int idPersona)
    {
        db.executeQuery("SELECT * FROM "+Database.dataBaseName+"."+tabla+" where "+primaryKey+" = "+idPersona+";");
        String[][] personaString = db.obtenerDatosTabla();
        return new Persona(idPersona, personaString[0][1], personaString[0][2]);
    }

    public static Persona getByID(int idPersona)
    {
        Database db = new Database();
        db.conectar("PersonaControl.gerByID");
        Persona persona = getByID(db, idPersona);
        db.cerrarConexion();
        return persona;
    }

    public static Persona updateName(int idPersona, String newname)
    {
        Database db = new Database();
        db.conectar("PersonaContro.updateName");
        db.executeInsert(
                "update "+Database.dataBaseName+"."+tabla+" set "+nameColumn+" = '"+newname+
                        "' where "+primaryKey+" = "+idPersona+";");
        Persona persona = getByID(db, idPersona);
        db.cerrarConexion();
        return persona;
    }

    public static Persona updateName(Database db, int idPersona, String newname)
    {
        db.executeInsert(
                "update "+Database.dataBaseName+"."+tabla+" set "+nameColumn+" = '"+newname+
                        "' where "+primaryKey+" = "+idPersona+";");
        Persona persona = getByID(db, idPersona);
        return persona;
    }

    public static Persona updateLastname(int idPersona, String lastname)
    {
        Database db = new Database();
        db.conectar("PersonaContro.updateLastName");
        db.executeInsert(
                "update "+Database.dataBaseName+"."+tabla+" set "+lastnameColumn+" = '"+lastname+
                        "' where "+primaryKey+" = "+idPersona+";");
        Persona persona = getByID(db, idPersona);
        db.cerrarConexion();
        return persona;
    }

    public static Persona updateLastname(Database db, int idPersona, String lastname)
    {
        db.executeInsert(
                "update "+Database.dataBaseName+"."+tabla+" set "+lastnameColumn+" = '"+lastname+
                        "' where "+primaryKey+" = "+idPersona+";");
        Persona persona = getByID(db, idPersona);
        return persona;
    }

    public static void delete(int idPersona)
    {
        Database db = new Database();
        db.conectar("PersonaContro.delete");
        UserControl.deleteUsersByIdPersona(db, idPersona);
        db.borrarRegistro(tabla, primaryKey, idPersona+"");
        db.cerrarConexion();
    }

    public static void delete(Database db, int idPersona)
    {
        UserControl.deleteUsersByIdPersona(db, idPersona);
        db.borrarRegistro(tabla, primaryKey, idPersona+"");
    }

    public static String[][] consultarTabla()
    {
        Database db = new Database();
        db.conectar("PersonaContro.consultarTabla");
        db.executeQuery("SELECT * FROM "+Database.dataBaseName+"."+tabla+";");
        String[][] tabla = db.obtenerDatosTabla();
        db.cerrarConexion();
        return tabla;
    }

    public static String[] columnas()
    {
        String[] columnas = {primaryKey,nameColumn,lastnameColumn};
        return columnas;
    }

    public static String[][] getAllId()
    {
        Database db = new Database();
        db.conectar("PersonaContro.getAllId");
        db.executeQuery("SELECT Persona.idPersona FROM "+Database.dataBaseName+"."+tabla+";");
        String[][] tabla = db.obtenerDatosTabla();
        db.cerrarConexion();
        return tabla;
    }
}
