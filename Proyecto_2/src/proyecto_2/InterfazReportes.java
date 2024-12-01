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
import javax.swing.*;
import java.awt.*;

// Clase que representa la interfaz para mostrar reportes de ventas.
// This class represents the interface to display sales reports.
public class InterfazReportes extends JFrame {
    private SistemaVentas gestionVentas; // Objeto para manejar el sistema de ventas / Object to manage the sales system.

    /**
     * Constructor para generar reportes.
     * Constructor to generate reports.
     * @param gestionVentas
     */
    public InterfazReportes(SistemaVentas gestionVentas) {
        this.gestionVentas = gestionVentas; // Inicializa el sistema de ventas / Initialize the sales system.

        setTitle("Reportes de Ventas"); // Título de la ventana / Window title
        setSize(500, 400); // Tamaño de la ventana / Window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configuración de cierre de ventana / Window close operation
        setLayout(new BorderLayout()); // Configuración del layout de la ventana / Layout setup

        // Panel para estadísticas
        // Panel for statistics
        JPanel panelEstadisticas = new JPanel(new GridLayout(3, 1)); // Panel con un layout de 3 filas / Panel with a 3-row layout
        int[][] ventas = gestionVentas.obtenerVentas(); // Obtener datos de ventas / Get sales data
        
        // Mostrar total de ventas
        // Display total sales
        JLabel totalVentas = new JLabel("Total Ventas: " + 
                calcularTotalVentas(ventas)); // Calcula y muestra el total de ventas / Calculate and display the total sales
        
        // Mostrar promedio semanal de ventas
        // Display weekly average sales
        JLabel promedioVentas = new JLabel("Promedio Semanal: " 
                + calcularPromedioSemanal(ventas)); // Calcula y muestra el promedio semanal / Calculate and display the weekly average
        
        // Mostrar tendencias de ventas
        // Display sales trends
        JLabel tendencias = new JLabel("Tendencias: " + 
                detectarTendencias(ventas)); // Detecta y muestra las tendencias de ventas / Detect and display sales trends

        // Añadir las etiquetas al panel de estadísticas
        // Add the labels to the statistics panel
        panelEstadisticas.add(totalVentas); // Añadir total de ventas / Add total sales label
        panelEstadisticas.add(promedioVentas); // Añadir promedio de ventas / Add sales average label
        panelEstadisticas.add(tendencias); // Añadir tendencias / Add trends label

        // Añadir el panel al centro de la ventana
        // Add the panel to the center of the window
        add(panelEstadisticas, BorderLayout.CENTER); // Añadir el panel de estadísticas al centro / Add the statistics panel to the center

        // Botón para regresar
        // Button to go back
        JButton botonRegresar = new JButton("Regresar"); // Crear el botón "Regresar" / Create the "Go back" button
        botonRegresar.addActionListener(e -> dispose()); // Cierra la ventana actual cuando se presiona el botón / Close the current window when button is pressed
        add(botonRegresar, BorderLayout.SOUTH); // Añadir el botón al sur de la ventana / Add the button to the bottom of the window

        setVisible(true); // Hacer visible la ventana / Make the window visible
    }

    /**
     * Método para calcular el total de ventas.
     * Method to calculate the total sales.
     * @param ventas Matriz con los datos de ventas / Sales data matrix
     * @return Total de ventas / Total sales
     */
    private int calcularTotalVentas(int[][] ventas) {
        int total = 0; // Inicializar total / Initialize total
        for (int[] producto : ventas) { // Iterar sobre los productos / Iterate over products
            for (int venta : producto) { // Iterar sobre las ventas de cada producto / Iterate over the sales of each product
                total += venta; // Sumar la venta al total / Add the sale to the total
            }
        }
        return total; // Retornar el total de ventas / Return the total sales
    }

    /**
     * Método para calcular el promedio semanal de ventas.
     * Method to calculate the weekly average sales.
     * @param ventas Matriz con los datos de ventas / Sales data matrix
     * @return Promedio semanal de ventas / Weekly average sales
     */
    private double calcularPromedioSemanal(int[][] ventas) {
        int total = calcularTotalVentas(ventas); // Calcular el total de ventas / Calculate the total sales
        return total / 7.0; // Asume 7 días en la semana / Assumes 7 days in the week
    }

    /**
     * Método para detectar tendencias en las ventas.
     * Method to detect sales trends.
     * @param ventas Matriz con los datos de ventas / Sales data matrix
     * @return Tendencias detectadas / Detected trends
     */
    private String detectarTendencias(int[][] ventas) {
        /**
         * Aquí puedes implementar un análisis más detallado usando 
         * la clase AnalisisRecursivo
         * Here you can implement a more detailed analysis using the 
         * AnalisisRecursivo class.
         */
        return "Tendencias aún no implementadas"; // Retorna un mensaje indicando que las tendencias no están implementadas / Returns a message indicating that trends are not implemented
    }

    /**
     * Método principal para iniciar la interfaz de reportes.
     * Main method to launch the reports interface.
     */
    public static void main(String[] args) {
        // Crear una instancia del sistema de ventas / Create an instance of the sales system
        SistemaVentas gestion = new SistemaVentas(
            new String[]{"Producto A", "Producto B", "Producto C"},
            new String[]{"Tienda Física", "En Línea"}
        );
        new InterfazReportes(gestion); // Crear la interfaz de reportes / Create the reports interface
    }
}