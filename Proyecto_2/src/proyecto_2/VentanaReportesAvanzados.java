/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_2;

/**
 * Ventana para mostrar reportes avanzados de ventas.
 * Window to display advanced sales reports.
 *
 * @author YESLYDANIELAFIGUEROA
 * @author NICOLELEIVAFALLAS
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaReportesAvanzados extends JFrame {
    private SistemaVentas gestionVentas; // Objeto que gestiona las ventas / Object to manage sales

    /**
     * Constructor para inicializar los reportes avanzados.
     * Constructor to initialize advanced reports.
     *
     * @param gestionVentas Objeto que gestiona las ventas / Object that manages sales.
     */
    public VentanaReportesAvanzados(SistemaVentas gestionVentas) {
        this.gestionVentas = gestionVentas; // Inicializa el sistema de ventas / Initializes the sales system

        setTitle("Reportes Avanzados"); // Título de la ventana / Window title
        setSize(600, 400); // Tamaño de la ventana / Window size
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Acción de cierre de ventana / Window close operation

        // Configuración del panel principal
        // Main panel configuration
        JPanel panel = new JPanel(new BorderLayout());

        // Definición de columnas de la tabla
        // Defining table columns
        String[] columnas = {"Producto", "Tienda Física", "En Línea", "Total"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);

        // Poblar la tabla con datos de ventas
        // Populate the table with sales data
        int[][] ventas = gestionVentas.obtenerVentas();
        for (int i = 0; i < gestionVentas.getProductos().length; i++) {
            int total = ventas[i][0] + ventas[i][1]; // Suma de ventas de ambos canales / Sum of sales from both channels
            modeloTabla.addRow(new Object[]{
                gestionVentas.getProductos()[i], // Nombre del producto / Product name
                ventas[i][0], // Ventas en tienda física / In-store sales
                ventas[i][1], // Ventas en línea / Online sales
                total // Total de ventas / Total sales
            });
        }

        // Crear la tabla y añadirla al panel con un JScrollPane
        // Create the table and add it to the panel with a JScrollPane
        JTable tabla = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel para mostrar estadísticas de ventas
        // Panel to display sales statistics
        JPanel panelEstadisticas = new JPanel(new GridLayout(2, 1));
        JLabel totalVentas = new JLabel("Total Ventas: " + calcularTotalVentas(ventas)); // Mostrar total de ventas / Display total sales
        JLabel promedioSemanal = new JLabel("Promedio Semanal: " + calcularPromedioSemanal(ventas)); // Mostrar promedio semanal / Display weekly average
        panelEstadisticas.add(totalVentas); // Añadir etiqueta de total de ventas / Add total sales label
        panelEstadisticas.add(promedioSemanal); // Añadir etiqueta de promedio semanal / Add weekly average label

        // Añadir el panel de estadísticas al panel principal
        // Add the statistics panel to the main panel
        panel.add(panelEstadisticas, BorderLayout.SOUTH);

        add(panel); // Añadir el panel al JFrame / Add the panel to JFrame
        setVisible(true); // Hacer visible la ventana / Make the window visible
    }

    /**
     * Método para calcular el total de ventas.
     * Method to calculate total sales.
     *
     * @param ventas Matriz de ventas / Sales data matrix
     * @return Total de ventas / Total sales
     */
    private int calcularTotalVentas(int[][] ventas) {
        int total = 0; // Inicializar total / Initialize total
        for (int[] producto : ventas) { // Iterar sobre los productos / Iterate over the products
            for (int venta : producto) { // Iterar sobre las ventas de cada producto / Iterate over the sales of each product
                total += venta; // Sumar las ventas al total / Add sales to total
            }
        }
        return total; // Retornar el total / Return the total
    }

    /**
     * Método para calcular el promedio semanal de ventas.
     * Method to calculate weekly average sales.
     *
     * @param ventas Matriz de ventas / Sales data matrix
     * @return Promedio semanal de ventas / Weekly average sales
     */
    private double calcularPromedioSemanal(int[][] ventas) {
        return calcularTotalVentas(ventas) / 7.0; // Asume 7 días en la semana / Assumes 7 days in a week
    }

    /**
     * Método principal para iniciar la ventana de reportes avanzados.
     * Main method to launch the advanced reports window.
     */
    public static void main(String[] args) {
        // Crear una instancia del sistema de ventas / Create an instance of the sales system
        SistemaVentas gestion = new SistemaVentas(
            new String[]{"Producto A", "Producto B", "Producto C"}, // Lista de productos / List of products
            new String[]{"Tienda Física", "En Línea"} // Tipos de canales / Types of channels
        );
        new VentanaReportesAvanzados(gestion); // Crear la ventana de reportes avanzados / Create the advanced reports window
    }
}

