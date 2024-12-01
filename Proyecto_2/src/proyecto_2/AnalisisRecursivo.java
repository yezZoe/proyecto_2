/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_2;

/**
 *
 * @author YESLYDANIELAFIGUEROA
 * @author NICOLELEIVAFALLAS
 */

/**
 * Clase para realizar análisis recursivos sobre las ventas
 * Class to perform recursive analysis on sales
 * 
 */

public class AnalisisRecursivo {
    /**
     * Calcula el promedio de un array recursivamente.
     * Calculates the average of an array recursively.
     * 
     * @param ventas Array de ventas.
     * @param n  Número de elementos a considerar.
     * @return Promedio de las ventas.
     */
    public static double calcularPromedio(int[] ventas, int n) {
        if (n == 0) return 0; // Caso base
        return (ventas[n - 1] + calcularPromedio(ventas, n - 1)) / (double) n;
    }

    /**
     * Detecta tendencias de aumento o disminución en ventas.
     * Detects trends of increase or decrease in sales.
     * 
     * @param ventas Array de ventas.
     * @param index  Índice actual.
     * @param tendencia "aumento" o "disminucion".
     * @return Verdadero si se detecta la tendencia, falso de lo contrario.
     */
    public static boolean detectarTendencia(int[] ventas, 
            int index, String tendencia) {
        if (index == ventas.length - 1) return true; //base case
        if (tendencia.equals("aumento")
                && ventas[index] <= ventas[index + 1]) {
            return detectarTendencia(ventas, index + 1, tendencia);
        }
        if (tendencia.equals("disminucion") 
                && ventas[index] >= ventas[index + 1]) {
            return detectarTendencia(ventas, index + 1, tendencia);
        }
        return false;
    }
}

