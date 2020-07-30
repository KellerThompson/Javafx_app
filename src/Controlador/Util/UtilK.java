package Controlador.Util;

public class UtilK
{
    public static void imprimeMatriz(String[][] matriz)
    {
        for(int i = 0; i < matriz.length; i++) // filas
        {
            for(int e = 0; e < matriz[0].length; e++) //columnas
            {
                System.out.print(matriz[i][e] + "  ");
            }
            System.out.println();
        }
    }

    public static void imprimeArray(String [] array)
    {
        System.out.println();
        for(int i = 0; i <= array.length - 1; i++)
        {
            System.out.println(array[i]);
        }
    }
}
