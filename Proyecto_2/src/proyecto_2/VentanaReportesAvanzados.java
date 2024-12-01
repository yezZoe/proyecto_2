/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Ventana para mostrar reportes avanzados de ventas. Window to display advanced
 * sales reports.
 *
 * @author YESLYDANIELAFIGUEROA
 * @author NICOLELEIVAFALLAS
 */
public class VentanaReportesAvanzados extends JFrame {

    private SistemaVentas gestionVentas; // Objeto que gestiona las ventas

    /**
     * Constructor para inicializar los reportes avanzados. Constructor to
     * initialize the advanced reports.
     *
     * @param gestionVentas Objeto que gestiona las ventas. / Object managing
     * sales.
     */
    public VentanaReportesAvanzados(SistemaVentas gestionVentas) {
        this.gestionVentas = gestionVentas;

        setTitle("Reportes Avanzados");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

       
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(230, 220, 255));

        
        String[] columnas = {"Producto", "Tienda Física", "En Línea", "Total"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);

       
        int[][] ventas = gestionVentas.obtenerVentas();
        for (int i = 0; i < gestionVentas.getProductos().length; i++) {
            int total = ventas[i][0] + ventas[i][1];
            modeloTabla.addRow(new Object[]{
                gestionVentas.getProductos()[i],
                ventas[i][0],
                ventas[i][1],
                total
            });
        }

        JTable tabla = new JTable(modeloTabla);
        tabla.setBackground(new Color(255, 255, 255));
        tabla.setFont(new Font("Arial", Font.PLAIN, 14)); 
        tabla.setRowHeight(30); 
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tabla.getTableHeader().setBackground(new Color(200, 180, 255));
        tabla.getTableHeader().setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(120, 0, 150), 2));
        panel.add(scrollPane, BorderLayout.CENTER);

        //  Panel to display sales statistics.
        JPanel panelEstadisticas = new JPanel(new GridLayout(2, 1));
        panelEstadisticas.setBackground(new Color(230, 220, 255));
        panelEstadisticas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel totalVentas = new JLabel("Total Ventas: " + calcularTotalVentas(ventas));
        totalVentas.setFont(new Font("Arial", Font.BOLD, 16));
        totalVentas.setForeground(new Color(80, 0, 120));

        JLabel promedioSemanal = new JLabel("Promedio Semanal: " + calcularPromedioSemanal(ventas));
        promedioSemanal.setFont(new Font("Arial", Font.BOLD, 16)); 
        promedioSemanal.setForeground(new Color(80, 0, 120));

        panelEstadisticas.add(totalVentas); // Add total sales label
        panelEstadisticas.add(promedioSemanal); // Add weekly average label.

        //Add statistics panel to the main panel.
        panel.add(panelEstadisticas, BorderLayout.SOUTH);

        add(panel); // Add the panel to JFrame.
        setVisible(true); //Make the window visible.
    }

    /**
     * Método recursivo para calcular el total de ventas. Recursive method to
     * calculate the total sales.
     *
     * @param ventas Matriz de ventas. / Sales matrix.
     * @param fila Fila actual que estamos procesando. / Current row being
     * processed.
     * @param columna Columna actual que estamos procesando. / Current column
     * being processed.
     * @return Total de ventas. / Total sales.
     */
    private int calcularTotalVentasRecursivo(int[][] ventas, int fila, int columna) {
        if (fila >= ventas.length) { //Base case: if we reached the end of rows
            return 0;
        }

        if (columna >= ventas[fila].length) { //Base case: if we reached the end of columns..
            return calcularTotalVentasRecursivo(ventas, fila + 1, 0); // Recursive call with next row.
        }

        // Sumar la venta de la celda actual y llamar recursivamente a la siguiente columna
        return ventas[fila][columna] + calcularTotalVentasRecursivo(ventas, fila, columna + 1);
    }

    /**
     * Método para calcular el total de ventas usando recursión. Method to
     * calculate total sales using recursion.
     *
     * @param ventas Matriz de ventas. / Sales matrix.
     * @return Total de ventas. / Total sales.
     */
    private int calcularTotalVentas(int[][] ventas) {
        return calcularTotalVentasRecursivo(ventas, 0, 0);
    }

    /**
     * Método para calcular el promedio semanal de ventas.
     *
     * @param ventas Matriz de ventas
     * @return Promedio semanal de ventas
     */
    private double calcularPromedioSemanal(int[][] ventas) {
        return calcularTotalVentas(ventas) / 7.0; 
    }

    /**
     * Método principal para iniciar la ventana de reportes avanzados. Main
     * method to start the advanced reports window.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {

        SistemaVentas gestion = new SistemaVentas(
                new String[]{"Producto A", "Producto B", "Producto C"},
                new String[]{"Tienda Física", "En Línea"}
        );
        VentanaReportesAvanzados ventanaReportesAvanzados = new VentanaReportesAvanzados(gestion);
    }
}
