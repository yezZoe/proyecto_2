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
     * @param ventas   Array bidimensional con las ventas. / 2D array with the sales data.
     * @param filename Nombre del archivo. / Name of the file.
     */
    public static void guardarVentas(int[][] ventas, String filename) {
        try (BufferedWriter writer = 
                new BufferedWriter(new FileWriter(filename))) { // Abrir el archivo para escritura / Open the file for writing
            for (int[] producto : ventas) { // Iterar sobre cada producto / Iterate over each product
                for (int venta : producto) { // Iterar sobre las ventas de cada producto / Iterate over the sales of each product
                    writer.write(venta + " "); // Escribir la venta en el archivo / Write the sale to the file
                }
                writer.newLine(); // Añadir una nueva línea después de cada producto / Add a new line after each product
            }
        } catch (IOException e) { // Manejo de excepciones / Exception handling
            e.printStackTrace(); // Imprimir el error en la consola / Print the error to the console
        }
    }

    /**
     * Carga las ventas desde un archivo de texto.
     * Loads sales from a text file.
     * 
     * @param filename Nombre del archivo. / Name of the file.
     * @return Array bidimensional con las ventas. / 2D array with the sales data.
     */
    public static int[][] cargarVentas(String filename) {
        int[][] ventas = new int[3][2]; //Cambia las dimensiones según tu diseño / Change the dimensions according to your design
        try (BufferedReader reader =
                new BufferedReader(new FileReader(filename))) { // Abrir el archivo para lectura / Open the file for reading
            String line;
            int row = 0; // Fila actual en el array / Current row in the array
            while ((line = reader.readLine()) != null) { // Leer cada línea del archivo / Read each line from the file
                String[] values = line.split(" "); // Dividir la línea en valores / Split the line into values
                for (int col = 0; col < values.length; col++) { // Iterar sobre los valores / Iterate over the values
                    ventas[row][col] = Integer.parseInt(values[col]); // Convertir a entero y guardar en el array / Convert to int and store in the array
                }
                row++; // Incrementar la fila / Increment the row
            }
        } catch (IOException e) { // Manejo de excepciones / Exception handling
            e.printStackTrace(); // Imprimir el error en la consola / Print the error to the console
        }
        return ventas; // Retornar las ventas cargadas / Return the loaded sales
    }
}

