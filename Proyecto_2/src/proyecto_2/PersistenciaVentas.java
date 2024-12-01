/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_2;
/**
 * Class for sales persistence using exceptions.
 * Clase para la persistencia de ventas usando excepciones.
 *
 * @author YESLYDANIELAFIGUEROA
 * @author NICOLELEIVAFALLAS
 */
import java.io.*;  

public class PersistenciaVentas {

    /**
     * Saves sales data to a text file.
     * Guarda las ventas en un archivo de texto.
     *
     * @param ventas Array with sales data.
     * @param filename File name to save the sales data.
     */
    public static void guardarVentas(int[][] ventas, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {  
            // Write each sale entry to the file
            for (int[] producto : ventas) {   
                for (int venta : producto) { 
                    writer.write(venta + " "); // Write sale value followed by a space
                }
                writer.newLine();  // New line after each product's sales data
            }
        } catch (IOException e) {  // Handle exceptions
            e.printStackTrace();  // Print error to console 
        }
    }

    /**
     * Loads sales data from a text file.
     * Carga las ventas desde un archivo de texto.
     *
     * @param filename Name of the file to load the sales data from.
     * @return Array with sales data.
     */
    public static int[][] cargarVentas(String filename) {
        int[][] ventas = new int[3][2]; 
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) { 
            String line;
            int row = 0;  
            while ((line = reader.readLine()) != null) {  
                String[] values = line.split(" "); 
              for (int col = 0; col < values.length; col++) {
                ventas[row][col] = Integer.parseInt(values[col]);
                }
                row++;  // Increment row for each line in the file
            }
        } catch (IOException e) {  // Handle exceptions (Manejo de excepciones)
            e.printStackTrace();  // Print error (Imprimir error)
        }
        return ventas;  // Return loaded sales data (Retornar ventas cargadas)
    }
}

