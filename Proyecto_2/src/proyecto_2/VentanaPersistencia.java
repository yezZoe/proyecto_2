/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_2;

/**
 * Ventana para manejar la persistencia de datos de ventas.
 * Window to manage sales data persistence.
 * 
 * @author YESLYDANIELAFIGUEROA
 * @author NICOLELEIVAFALLAS
 */
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class VentanaPersistencia extends JFrame {
    private SistemaVentas gestionVentas; // Objeto para gestionar el sistema de ventas / Object to manage the sales system

    /**
     * Constructor para crear la ventana de persistencia de datos.
     * Constructor to create the data persistence window.
     *
     * @param gestionVentas Sistema de gestión de ventas / Sales management system
     */
    public VentanaPersistencia(SistemaVentas gestionVentas) {
        this.gestionVentas = gestionVentas; // Inicializa el sistema de ventas / Initialize the sales system

        setTitle("Persistencia de Datos"); // Título de la ventana / Window title
        setSize(400, 200); // Tamaño de la ventana / Window size
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Acción de cerrar ventana / Window close action

        setLayout(new FlowLayout()); // Configura el layout de la ventana / Set window layout

        // Botón para guardar datos
        // Button to save data
        JButton botonGuardar = new JButton("Guardar Datos");
        botonGuardar.addActionListener(e -> guardarDatos()); // Acción para guardar los datos / Action to save data

        // Botón para cargar datos
        // Button to load data
        JButton botonCargar = new JButton("Cargar Datos");
        botonCargar.addActionListener(e -> cargarDatos()); // Acción para cargar los datos / Action to load data

        add(botonGuardar); // Añadir botón de guardar / Add save button
        add(botonCargar); // Añadir botón de cargar / Add load button

        setVisible(true); // Hacer visible la ventana / Make the window visible
    }

    /**
     * Método recursivo para guardar los datos de ventas en un archivo.
     * Recursive method to save sales data to a file.
     *
     * @param ventas Matriz de ventas / Sales matrix
     * @param fila Índice de la fila / Row index
     * @param col Índice de la columna / Column index
     */
private void guardarDatosRecursivo(int[][] ventas, int fila, int col) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("ventas.txt", true))) { // Abrir archivo en modo append / Open file in append mode
        // Caso base: Si llegamos al final de las filas, terminamos
        if (fila == ventas.length) {
            JOptionPane.showMessageDialog(this, "Datos guardados correctamente."); // Mensaje de éxito / Success message
            return;
        }

        // Si hemos terminado de procesar una fila, avanzamos a la siguiente
        if (col == ventas[fila].length) {
            writer.newLine(); // Escribir una nueva línea después de cada fila / Write a new line after each row
            guardarDatosRecursivo(ventas, fila + 1, 0); // Llamada recursiva para la siguiente fila / Recursive call for the next row
            return;
        }

        // Escribir la venta en el archivo / Write the sale in the file
        writer.write(ventas[fila][col] + " ");
        
        // Llamada recursiva para la siguiente columna / Recursive call for the next column
        guardarDatosRecursivo(ventas, fila, col + 1);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al guardar los datos."); // Mensaje de error / Error message
    }
}

  /**
     * Método original para llamar a la versión recursiva de guardar datos.
     * Original method to call the recursive version of saving data.
     */
    private void guardarDatos() {
    int[][] ventas = gestionVentas.obtenerVentas(); // Obtener los datos de ventas / Get sales data
    guardarDatosRecursivo(ventas, 0, 0); // Llamada inicial a la función recursiva / Initial call to the recursive function
}

  /**
     * Método recursivo para cargar los datos de ventas desde un archivo.
     * Recursive method to load sales data from a file.
     *
     * @param reader BufferedReader para leer el archivo / BufferedReader to read the file
     * @param ventas Matriz de ventas / Sales matrix
     * @param fila Índice de la fila / Row index
     * @throws IOException Si ocurre un error al leer el archivo / If an error occurs while reading the file
     */
   private void cargarDatosRecursivo(BufferedReader reader, int[][] ventas, int fila) throws IOException {
    String line = reader.readLine(); // Leer una línea del archivo / Read a line from the file
    if (line == null) {
        JOptionPane.showMessageDialog(this, "Datos cargados correctamente."); // Mensaje de éxito / Success message
        return;
    }

    String[] valores = line.split(" "); // Dividir la línea en valores / Split the line into values
    for (int col = 0; col < valores.length; col++) {
        ventas[fila][col] = Integer.parseInt(valores[col]); // Asignar el valor de la venta / Assign the sale value
    }

    // Llamada recursiva para la siguiente fila / Recursive call for the next row
    cargarDatosRecursivo(reader, ventas, fila + 1);
}
 /**
     * Método original para llamar a la versión recursiva de cargar datos.
     * Original method to call the recursive version of loading data.
     */
   private void cargarDatos() {
    try (BufferedReader reader = new BufferedReader(new FileReader("ventas.txt"))) { // Abrir archivo para lectura / Open file for reading
        int[][] ventas = gestionVentas.obtenerVentas(); // Obtener los datos de ventas / Get sales data
        cargarDatosRecursivo(reader, ventas, 0); // Llamada inicial a la función recursiva / Initial call to the recursive function
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar los datos."); // Mensaje de error / Error message
    }
}


    /**
     * Método principal para iniciar la ventana de persistencia.
     * Main method to launch the persistence window.
     */
    public static void main(String[] args) {
        // Crear una instancia del sistema de ventas / Create an instance of the sales system
        SistemaVentas gestion = new SistemaVentas(
            new String[]{"Producto A", "Producto B", "Producto C"}, // Lista de productos / List of products
            new String[]{"Tienda Física", "En Línea"} // Tipos de canales / Types of channels
        );
        new VentanaPersistencia(gestion); // Crear la ventana de persistencia / Create the persistence window
    }
}

