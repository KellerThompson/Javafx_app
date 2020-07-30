package Controlador.model.Resultados;

import Controlador.DataBase.Database;

public class ResultadosControl
{
    private static String tabla = "Resultados";
    private static String primaryKey = "idResultados";
    private static String idUserColumn = "idUser";

    public static void delete(Database db, int idResultados)
    {
        db.borrarRegistro(tabla, primaryKey, idResultados+"");
    }

    public static void deleteUsersByIdUser(Database db, int idUser)
    {
        db.executeQuery("SELECT "+primaryKey+" FROM "+Database.dataBaseName+"."+tabla+" " +
                "where "+idUserColumn+" = "+idUser+";");
        String[][] tabla = db.obtenerDatosTabla();

        for(int i = 0; i < tabla.length; i++)
        {
            System.out.println("Delete resultado: " + tabla[i][0]);
            delete(db, Integer.parseInt(tabla[i][0]));
        }
    }
}
