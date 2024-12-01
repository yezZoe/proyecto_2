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

/**
 * Clase que representa la interfaz para mostrar reportes de ventas.
 *  This class represents the interface to display sales reports.
 */
public class InterfazReportes extends JFrame {
    private SistemaVentas gestionVentas; // Object to manage the sales system.

    /**
     * Constructor para generar reportes.
     * Constructor to generate reports.
     * @param gestionVentas
     */
    public InterfazReportes(SistemaVentas gestionVentas) {
        this.gestionVentas = gestionVentas; // Initialize the sales system.

        setTitle("Reportes de Ventas"); 
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLayout(new BorderLayout()); //Layout setup

        
        // Panel for statistics
        JPanel panelEstadisticas = new JPanel(new GridLayout(3, 1)); 
        int[][] ventas = gestionVentas.obtenerVentas(); 
        
        // Mostrar total de ventas
        // Display total sales
        JLabel totalVentas = new JLabel("Total Ventas: " + 
                calcularTotalVentas(ventas)); //Calculate and display the total sales
        
        // Mostrar promedio semanal de ventas
        // Display weekly average sales
        JLabel promedioVentas = new JLabel("Promedio Semanal: " 
                + calcularPromedioSemanal(ventas)); //Calculate and display the weekly average
        
        // Mostrar tendencias de ventas
        // Display sales trends
        JLabel tendencias = new JLabel("Tendencias: " + 
                detectarTendencias(ventas)); //Detect and display sales trends

      
        // Add the labels to the statistics panel
        panelEstadisticas.add(totalVentas); // Add total sales label
        panelEstadisticas.add(promedioVentas); //Add sales average label
        panelEstadisticas.add(tendencias); //Add trends label

      
        // Add the panel to the center of the window
        add(panelEstadisticas, BorderLayout.CENTER); //Add the statistics panel to the center

        // Botón para regresar
        // Button to go back
        JButton botonRegresar = new JButton("Regresar"); 
        botonRegresar.addActionListener(e -> dispose()); 
        add(botonRegresar, BorderLayout.SOUTH); 

        setVisible(true); //Make the window visible
    }

    /**
     * Método para calcular el total de ventas.
     * Method to calculate the total sales.
     * @param ventas Matriz con los datos de ventas / Sales data matrix
     * @return Total de ventas / Total sales
     */
    private int calcularTotalVentas(int[][] ventas) {
        int total = 0; //Initialize total
        for (int[] producto : ventas) { //Iterate over products
            for (int venta : producto) { //Iterate over the sales of each product
                total += venta; //Add the sale to the total
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
        int total = calcularTotalVentas(ventas); // Calculate the total sales
        return total / 7.0; // Assumes 7 days in the week
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
        return "Tendencias aún no implementadas"; //Returns a message indicating that trends are not implemented
    }

    /**
     * Método principal para iniciar la interfaz de reportes.
     * Main method to launch the reports interface.
     */
    public static void main(String[] args) {
        //Create an instance of the sales system
        SistemaVentas gestion = new SistemaVentas(
            new String[]{"Producto A", "Producto B", "Producto C"},
            new String[]{"Tienda Física", "En Línea"}
        );
        new InterfazReportes(gestion); //Create the reports interface
    }
}