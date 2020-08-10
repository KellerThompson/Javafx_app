package Controlador.DataBase;

import javax.swing.*;
import java.sql.*;

/**
 * @author Keller Thompson
 */
public class Database
{
    private Connection conexion;  //Objeto que establece la conexion
    private Statement sentenciaSQL; //Objeto que almacena la sentencia que ejecuta la consulta
    private ResultSet resultSet; //Objeto que guarda los resultados de la consulta

    public static final String dataBaseName = "bfkbonwrvl7atwiehbto";
    private static final String url_db = "jdbc:mysql://usk3refbtwbe3fdm:ivccxIR52fMqzS8F6gsm@bfkbonwrvl7atwiehbto-mysql.services.clever-cloud.com:3306/bfkbonwrvl7atwiehbto?useSSL=false";
    private static final String user = "usk3refbtwbe3fdm";
    private static final String password = "ivccxIR52fMqzS8F6gsm";

    /**
     * Inicia la conexion con la base de datos.
     * @author Keller Thompson
     */
    public void conectar(String className)
    {
        try
        {
            System.out.println("Conectando...");
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection(url_db, user, password); // SET GLOBAL time_zone = '-6:00';
            sentenciaSQL = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("Conexion exitosa: " + className);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    /**
     * Termina la conexion con la base de datos.
     * @author Keller Thompson
     */
    public void cerrarConexion()
    {
        try
        {
            System.out.println("Cerrar Conexion");
            if(resultSet != null) resultSet.close();
            if(sentenciaSQL != null) sentenciaSQL.close();
            if(conexion != null) conexion.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex);
        }
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Metodo que inserta nuevos registros en la base de datos.
     * @param tabla : Nombre de la tabla.
     * @param columnas : Nombre de las columnas. Cada columna debe estar separada por ", "
     * @param valores : Valores a ingresar. Cada valor debe estar separada por "', '"
     *                  y en el orden correspondiente a las columnas.
     * Ejemplo Database.insertarEnTabla( "User", "username, password", psUsername + "', '" + psPassword);
     * @author Keller Thompson
     */
    public void insertarEnTabla(String tabla, String columnas, String valores)
    {
        try
        {
            sentenciaSQL.executeUpdate("INSERT INTO " + dataBaseName + "." + tabla + " (" + columnas+ ") VALUES ('" + valores + "')");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void executeInsert(String sentencia)
    {
        try
        {
            sentenciaSQL.executeUpdate(sentencia);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    /**
     * Metodo que actualiza un registro especifico.
     * @param psTablas : Nombre de la tabla
     * @param psColumnas : Nombre de la columna
     * @param psValores : Nuevo valor para asignar
     * @param primaryKey : Nombre de la columna del PrimaryKey
     * @param psID : primarykey del registro a actualizar
     * @author Keller Thompson
     */
    public void actualizarCampo (String psTablas, String psColumnas, String psValores, String primaryKey, String psID)
    {
        try
        {
            sentenciaSQL.executeUpdate("UPDATE " + dataBaseName + "." + psTablas + " SET " + psColumnas + " = '" + psValores + "' WHERE (" + primaryKey + "= '" + psID + "')");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }

    /**
     * Metodo que borra un registro especificado por su id
     * @param tabla: Nombre de la tabla donde se encuentra el registro
     * @param primaryKey : Nombre asignado a la primarykey
     * @param idValue : valor del primarykey asignado al registro que desea borrar
     * @author Keller Thompson
     */
    public void borrarRegistro (String tabla, String primaryKey, String idValue)
    {
        try
        {
            sentenciaSQL.execute("DELETE FROM " + dataBaseName + "." + tabla + " WHERE (" + primaryKey + " = " + idValue + ");");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    /**
     * Metodo con el cual buscar un campo especifico en una tabla y columna especifica
     * @param table : Nombre de la tabla.
     * @param columna : Columna en la que se quiere buscar.
     * @param subcad : subcadena a buscar.
     * @author Keller Thompson
     * @return Verdadero si lo encuentra, o un Falso en caso contrario.
     */
    public boolean existeEnColumna(String table, String columna, String subcad)
    {
        try
        {
            resultSet = sentenciaSQL.executeQuery("SELECT * FROM " + dataBaseName + "." + table + " WHERE (" + columna + " = " + "'" + subcad + "')");
            resultSet.beforeFirst();
            if(resultSet.next())
                return true;
            else
                return false;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    public int rowCount(String query)
    {
        try
        {
            resultSet = sentenciaSQL.executeQuery(query);
            resultSet.last();
            return resultSet.getRow();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return 0;
        }
    }



    /**
     * Metodo que obtiene un registro especifico.
     * @param celda : nombre de la columna del dato que se desea
     * @param table : nombre de la tabla
     * @param primaryKey : nombre de la columna que se usa para la clausula "where"
     * @param idValue : valor de la primaryKey que se usa en la clausula "where"
     * @return El resultado de la consulta en un String
     * @author Keller Thompson
     */
    public String getStringAt(String celda, String table, String primaryKey, String idValue)
    {
        try
        {
            resultSet = sentenciaSQL.executeQuery("SELECT " + celda + " FROM " + dataBaseName + "." + table + " WHERE (" + primaryKey + " = " + "'" + idValue + "')");
            resultSet.first();
            return resultSet.getString(1);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    public int getIntegerAt(String celda, String table, String primaryKey, String idValue)
    {
        try
        {
            resultSet = sentenciaSQL.executeQuery("SELECT " + celda + " FROM " + dataBaseName + "." + table + " WHERE (" + primaryKey + " = " + "'" + idValue + "')");
            resultSet.first();
            return resultSet.getInt(1);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return -1;
        }
    }

    /**
     * NOTA: La sentencia debe tener la sintaxis correcta para la consulta a realizar
     * Metodo que realiza una consulta especificada.
     * @param psSentencia : La consulta a realizar
     * @author Keller Thompson
     */
    public void executeQuery(String psSentencia)
    {
        try
        {
            resultSet = sentenciaSQL.executeQuery(psSentencia);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    /**
     * NOTA: Es necesario haber iniciado una consulta antes con el metodo sentenciaQuery()
     * Metodo que cre una matriz String con los datos del ResultSet actual.
     * @return Una matriz String
     * @author Keller Thompson
     */
    public String[][] obtenerDatosTabla()
    {
        String [][] tabla = new String[1][1];

        try
        {
            int nColumnas = resultSet.getMetaData().getColumnCount();
            resultSet.last();
            int nFilas = resultSet.getRow();

            tabla = new String[nFilas][nColumnas];

            resultSet.first();

            for(int i = 0; i <= nFilas-1; i++)
            {
                for(int e = 0; e <= nColumnas-1; e++)
                {
                    tabla[i][e] = resultSet.getString(e+1);
                }
                resultSet.next();
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex);
        }
        return tabla;
    }

    /**
     * NOTA: Es necesario haber iniciado una consulta antes.
     * Metodo que crea un array String con el nombre de las columnas del ResultSet actual.
     * @return Un array String
     * @throws SQLException
     * @author Keller Thompson
     */
    public String[] obtenerColumnasDeTabla()
    {
        try
        {
            int numColumnas = this.resultSet.getMetaData().getColumnCount();
            String[] columnas = new String[numColumnas];

            for(int i = 0; i < numColumnas; i++)
            {
                columnas[i] = this.resultSet.getMetaData().getColumnName(i+1);
            }
            return columnas;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo que obtiene el ID del último registro en la tabla
     * @param psTable : Nombre de la Tabla
     * @param psId : Nombre del primaryKey
     * @autor Keller Thompson
     */
    public int getLastIDInserted(String psTable, String psId)
    {
        try
        {
            this.resultSet = this.sentenciaSQL.executeQuery("SELECT " + psId + " FROM " + this.dataBaseName + "." + psTable + " ORDER BY " + psId + " DESC");
            this.resultSet.first();
            return this.resultSet.getInt(1);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex);
            return -1;
        }
    }
}