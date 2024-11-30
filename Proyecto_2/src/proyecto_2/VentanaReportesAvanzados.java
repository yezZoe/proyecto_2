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
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaReportesAvanzados extends JFrame {
    private SistemaVentas gestionVentas;

    /**
     * Constructor para inicializar los reportes avanzados.
     * Constructor to initialize advanced reports.
     *
     * @param gestionVentas Objeto que gestiona las ventas.
     */
    public VentanaReportesAvanzados(SistemaVentas gestionVentas) {
        this.gestionVentas = gestionVentas;

        setTitle("Reportes Avanzados");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Configuración del panel
        JPanel panel = new JPanel(new BorderLayout());

        // Tabla de reportes
        String[] columnas = {"Producto", "Tienda Física", "En Línea", "Total"};
        DefaultTableModel modeloTabla = 
                new DefaultTableModel(columnas, 0);

        // Poblar la tabla con datos
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
        JScrollPane scrollPane = new JScrollPane(tabla);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Etiquetas de estadísticas
        JPanel panelEstadisticas = new JPanel(new GridLayout(2, 1));
        JLabel totalVentas = new JLabel
        ("Total Ventas: " + calcularTotalVentas(ventas));
        JLabel promedioSemanal = new JLabel
        ("Promedio Semanal: " + calcularPromedioSemanal(ventas));
        panelEstadisticas.add(totalVentas);
        panelEstadisticas.add(promedioSemanal);

        panel.add(panelEstadisticas, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    private int calcularTotalVentas(int[][] ventas) {
        int total = 0;
        for (int[] producto : ventas) {
            for (int venta : producto) {
                total += venta;
            }
        }
        return total;
    }

    private double calcularPromedioSemanal(int[][] ventas) {
        return calcularTotalVentas(ventas) / 7.0; // Asume 7 días en la semana
    }

    public static void main(String[] args) {
        SistemaVentas gestion = new SistemaVentas(
            new String[]{"Producto A", "Producto B", "Producto C"},
            new String[]{"Tienda Física", "En Línea"}
        );
        new VentanaReportesAvanzados(gestion);
    }
}

