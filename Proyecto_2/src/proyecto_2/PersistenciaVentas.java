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
import java.io.*;

public class PersistenciaVentas {

    /**
     * Guarda las ventas en un archivo de texto.
     * Saves sales in a text file.
     * 
     * @param ventas   Array bidimensional con las ventas.
     * @param filename Nombre del archivo.
     */
    public static void guardarVentas(int[][] ventas, String filename) {
        try (BufferedWriter writer = 
                new BufferedWriter(new FileWriter(filename))) {
            for (int[] producto : ventas) {
                for (int venta : producto) {
                    writer.write(venta + " ");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga las ventas desde un archivo de texto.
     * Loads sales from a text file.
     * 
     * @param filename Nombre del archivo.
     * @return Array bidimensional con las ventas.
     */
    public static int[][] cargarVentas(String filename) {
        int[][] ventas = new int[3][2]; //Cambia las dimensiones según tu diseño
        try (BufferedReader reader =
                new BufferedReader(new FileReader(filename))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(" ");
                for (int col = 0; col < values.length; col++) {
                    ventas[row][col] = Integer.parseInt(values[col]);
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ventas;
    }
}

