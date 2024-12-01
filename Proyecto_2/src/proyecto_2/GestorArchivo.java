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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class GestorArchivo {
  private static final String ARCHIVO_VENTAS = 
 "\\Users\\YESLYDANIELAFIGUEROA\\Documents\\NetBeansProjects\\Proyecto_2\\src\\proyecto_2\\ventas.txt";

    /**
     * Método para guardar una venta en el archivo de texto.
     * Method to save a sale to the text file.
     * 
     * @param producto El nombre del producto. // The name of the product.
     * @param canal El nombre del canal de venta. // The name of the sales channel.
     * @param cantidad La cantidad vendida. // The quantity sold.
     */
    public void guardarVenta(String producto, String canal, int cantidad) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_VENTAS, true))) {
            // Write sale data to the file
            writer.write("Producto reservado:   " + producto + "  ; "
                    + "Tienda escogida:  " + canal + "  ; "
                    + "Cantidad de producto: "
                    + " " + cantidad);
            writer.newLine();

            JOptionPane.showMessageDialog(null, 
                "<html><b>Venta guardada correctamente:</b><br>" +
                "Producto: <font color='blue'>" + producto + "</font><br>" +
                "Canal: <font color='green'>" + canal + "</font><br>" +
                "Cantidad: <font color='red'>" + cantidad + "</font></html>",
                "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
           
            JOptionPane.showMessageDialog(null, 
                "<html><b>Error al guardar la venta:</b><br>" +
                "<font color='red'>" + e.getMessage() + "</font></html>", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método para cargar todas las ventas desde el archivo de texto.
     * Method to load all sales from the text file.
     * 
     * @return Una lista de cadenas, donde cada cadena representa una venta. 
     * // A list of strings, where each string represents a sale.
     */
    public List<String> cargarVentas() {
        List<String> ventas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_VENTAS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                ventas.add(linea);// Add each line (sale) to the list
            }

           
            // Success message when loading sales
            if (!ventas.isEmpty()) {
                JOptionPane.showMessageDialog(null, 
                 "<html><b>Ventas cargadas correctamente:</b><br>" +
                 "Total de registros: <font color='blue'>" + ventas.size() + "</font></html>",
                 "Carga Exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, 
                    "<html><b>No se encontraron ventas registradas.</b></html>", 
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IOException e) {
          
            // Error message with styled design
            JOptionPane.showMessageDialog(null, 
                "<html><b>Error al cargar las ventas:</b><br>" +
                "<font color='red'>" + e.getMessage() + "</font></html>", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        return ventas; //Return the list of sales
    }
}



