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

import javax.swing.JOptionPane;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class GestorArchivo {

    // Get the base directory of the project
    private static final Path DIRECTORIO_BASE = Paths.get("").toAbsolutePath();  
    // Build the full path to the 'ventas.txt' file inside the 'data' folder
    private static final Path ARCHIVO_VENTAS = DIRECTORIO_BASE.resolve("data/ventas.txt");

    /**
     * Method to save a sale to the text file.
     * This method appends the sale data (product, channel, quantity) to the 'ventas.txt' file.
     * If the file or directory doesn't exist, it will be created.
     * 
     * @param producto The name of the product.
     * @param canal The name of the sales channel.
     * @param cantidad The quantity sold.
     */
    public void guardarVenta(String producto, String canal, int cantidad) {
        // Ensure that the 'data' directory exists
        Path directorio = DIRECTORIO_BASE.resolve("data");
        if (!Files.exists(directorio)) {
            try {
                // Create the 'data' directory if it doesn't exist
                Files.createDirectories(directorio);  
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, 
                    "<html><b>Error creating the directory:</b><br>" +
                    "<font color='red'>" + e.getMessage() + "</font></html>", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;  // Exit the method if directory creation fails
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_VENTAS.toFile(), true))) {
            // Write sale data to the file
            writer.write("Producto reservado:   " + producto + "  ; "
                    + "Tienda escogida:  " + canal + "  ; "
                    + "Cantidad de producto: "
                    + " " + cantidad);
            writer.newLine();

            // Display a success message
            JOptionPane.showMessageDialog(null, 
                "<html><b>Sale saved successfully:</b><br>" +
                "Product: <font color='blue'>" + producto + "</font><br>" +
                "Channel: <font color='green'>" + canal + "</font><br>" +
                "Quantity: <font color='red'>" + cantidad + "</font></html>",
                "Successful Registration", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            // Display an error message if the sale could not be saved
            JOptionPane.showMessageDialog(null, 
                "<html><b>Error saving the sale:</b><br>" +
                "<font color='red'>" + e.getMessage() + "</font></html>", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Method to load all sales from the text file.
     * This method reads the 'ventas.txt' file and loads each sale as a string into a list.
     * If the file doesn't exist or there is an error, an error message will be displayed.
     * 
     * @return A list of strings, where each string represents a sale.
     */
    public List<String> cargarVentas() {
        List<String> ventas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_VENTAS.toFile()))) {
            String linea;
            // Read each line (representing a sale) from the file and add it to the list
            while ((linea = reader.readLine()) != null) {
                ventas.add(linea);
            }

            // Success message if sales were loaded
            if (!ventas.isEmpty()) {
                JOptionPane.showMessageDialog(null, 
                 "<html><b>Sales loaded successfully:</b><br>" +
                 "Total records: <font color='blue'>" + ventas.size() + "</font></html>",
                 "Successful Load", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Message when no sales are found
                JOptionPane.showMessageDialog(null, 
                    "<html><b>No sales records found.</b></html>", 
                    "Information", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IOException e) {
            // Display an error message if there was an issue loading the sales
            JOptionPane.showMessageDialog(null, 
                "<html><b>Error loading the sales:</b><br>" +
                "<font color='red'>" + e.getMessage() + "</font></html>", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        // Return the list of sales
        return ventas;
    }
}



