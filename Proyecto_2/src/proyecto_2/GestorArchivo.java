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
    private static final String ARCHIVO_VENTAS = "\\Users\\YESLYDANIELAFIGUEROA\\Documents\\NetBeansProjects\\Proyecto_2\\src\\proyecto_2\\ventas.txt";

    /**
     * Método para guardar una venta en el archivo de texto.
     * @param producto El nombre del producto.
     * @param canal El nombre del canal de venta.
     * @param cantidad La cantidad vendida.
     */
    public void guardarVenta(String producto, String canal, int cantidad) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_VENTAS, true))) {
            // Escribimos los datos de la venta en el archivo
            writer.write("Producto reservado:   " + producto + "  ; "
                    + "Tienda escogida:  " + canal + "  ; "
                    + "Cantidad de producto: "
                    + " " + cantidad);
            writer.newLine(); // Añadimos un salto de línea

            // Mensaje de éxito con diseño estético
            JOptionPane.showMessageDialog(null, 
                "<html><b>Venta guardada correctamente:</b><br>" +
                "Producto: <font color='blue'>" + producto + "</font><br>" +
                "Canal: <font color='green'>" + canal + "</font><br>" +
                "Cantidad: <font color='red'>" + cantidad + "</font></html>",
                "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            // Mensaje de error con diseño estilizado
            JOptionPane.showMessageDialog(null, 
                "<html><b>Error al guardar la venta:</b><br>" +
                "<font color='red'>" + e.getMessage() + "</font></html>", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método para cargar todas las ventas desde el archivo de texto.
     * @return Una lista de cadenas, donde cada cadena representa una venta.
     */
    public List<String> cargarVentas() {
        List<String> ventas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_VENTAS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                ventas.add(linea);
            }

            // Mensaje de éxito al cargar ventas
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
            // Mensaje de error con diseño estilizado
            JOptionPane.showMessageDialog(null, 
                "<html><b>Error al cargar las ventas:</b><br>" +
                "<font color='red'>" + e.getMessage() + "</font></html>", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        return ventas;
    }
}



