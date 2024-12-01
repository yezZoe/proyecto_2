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
    private SistemaVentas gestionVentas; // Objeto que gestiona las ventas

    /**
     * Constructor para inicializar los reportes avanzados.
     *
     * @param gestionVentas Objeto que gestiona las ventas.
     */
    public VentanaReportesAvanzados(SistemaVentas gestionVentas) {
        this.gestionVentas = gestionVentas; // Inicializa el sistema de ventas

        setTitle("Reportes Avanzados"); // Título de la ventana
        setSize(600, 400); // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Acción de cierre de ventana

        // Configuración del panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(230, 220, 255)); // Fondo color lila suave

        // Definición de columnas de la tabla
        String[] columnas = {"Producto", "Tienda Física", "En Línea", "Total"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);

        // Poblar la tabla con datos de ventas
        int[][] ventas = gestionVentas.obtenerVentas();
        for (int i = 0; i < gestionVentas.getProductos().length; i++) {
            int total = ventas[i][0] + ventas[i][1]; // Suma de ventas de ambos canales
            modeloTabla.addRow(new Object[] {
                gestionVentas.getProductos()[i], // Nombre del producto
                ventas[i][0], // Ventas en tienda física
                ventas[i][1], // Ventas en línea
                total // Total de ventas
            });
        }

        // Crear la tabla y añadirla al panel con un JScrollPane
        JTable tabla = new JTable(modeloTabla);
        tabla.setBackground(new Color(255, 255, 255)); // Fondo blanco para la tabla
        tabla.setFont(new Font("Arial", Font.PLAIN, 14)); // Fuente mejorada
        tabla.setRowHeight(30); // Aumentar altura de las filas
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); // Fuente para los encabezados
        tabla.getTableHeader().setBackground(new Color(200, 180, 255)); // Fondo para el encabezado
        tabla.getTableHeader().setForeground(Color.WHITE); // Color del texto en el encabezado
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(120, 0, 150), 2)); // Borde decorativo
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel para mostrar estadísticas de ventas
        JPanel panelEstadisticas = new JPanel(new GridLayout(2, 1));
        panelEstadisticas.setBackground(new Color(230, 220, 255)); // Fondo lila suave
        panelEstadisticas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Márgenes internos

        JLabel totalVentas = new JLabel("Total Ventas: " + calcularTotalVentas(ventas));
        totalVentas.setFont(new Font("Arial", Font.BOLD, 16)); // Estilo de fuente para total de ventas
        totalVentas.setForeground(new Color(80, 0, 120)); // Color del texto en púrpura oscuro

        JLabel promedioSemanal = new JLabel("Promedio Semanal: " + calcularPromedioSemanal(ventas));
        promedioSemanal.setFont(new Font("Arial", Font.BOLD, 16)); // Estilo de fuente para promedio semanal
        promedioSemanal.setForeground(new Color(80, 0, 120)); // Color del texto en púrpura oscuro

        panelEstadisticas.add(totalVentas); // Añadir etiqueta de total de ventas
        panelEstadisticas.add(promedioSemanal); // Añadir etiqueta de promedio semanal

        // Añadir el panel de estadísticas al panel principal
        panel.add(panelEstadisticas, BorderLayout.SOUTH);

        add(panel); // Añadir el panel al JFrame
        setVisible(true); // Hacer visible la ventana
    }

    /**
     * Método recursivo para calcular el total de ventas.
     *
     * @param ventas Matriz de ventas
     * @param fila   Fila actual que estamos procesando
     * @param columna Columna actual que estamos procesando
     * @return Total de ventas
     */
    private int calcularTotalVentasRecursivo(int[][] ventas, int fila, int columna) {
        if (fila >= ventas.length) { // Caso base: si llegamos al final de las filas
            return 0;
        }

        if (columna >= ventas[fila].length) { // Caso base: si llegamos al final de las columnas de la fila
            return calcularTotalVentasRecursivo(ventas, fila + 1, 0); // Llamada recursiva con la siguiente fila
        }

        // Sumar la venta de la celda actual y llamar recursivamente a la siguiente columna
        return ventas[fila][columna] + calcularTotalVentasRecursivo(ventas, fila, columna + 1);
    }

    /**
     * Método para calcular el total de ventas usando recursión.
     *
     * @param ventas Matriz de ventas
     * @return Total de ventas
     */
    private int calcularTotalVentas(int[][] ventas) {
        return calcularTotalVentasRecursivo(ventas, 0, 0); // Iniciar desde la primera fila y columna
    }

    /**
     * Método para calcular el promedio semanal de ventas.
     *
     * @param ventas Matriz de ventas
     * @return Promedio semanal de ventas
     */
    private double calcularPromedioSemanal(int[][] ventas) {
        return calcularTotalVentas(ventas) / 7.0; // Asume 7 días en la semana
    }

    /**
     * Método principal para iniciar la ventana de reportes avanzados.
     */
    public static void main(String[] args) {
        // Crear una instancia del sistema de ventas
        SistemaVentas gestion = new SistemaVentas(
            new String[]{"Producto A", "Producto B", "Producto C"}, // Lista de productos
            new String[]{"Tienda Física", "En Línea"} // Tipos de canales
        );
        new VentanaReportesAvanzados(gestion); // Crear la ventana de reportes avanzados
    }
}
