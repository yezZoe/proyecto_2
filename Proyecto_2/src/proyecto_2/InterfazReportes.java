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

public class InterfazReportes extends JFrame {
    private SistemaVentas gestionVentas;

    /**
     * Constructor para generar reportes.
     * Constructor to generate reports.
     */
    public InterfazReportes(SistemaVentas gestionVentas) {
        this.gestionVentas = gestionVentas;

        setTitle("Reportes de Ventas");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel para estadísticas
        JPanel panelEstadisticas = new JPanel(new GridLayout(3, 1));
        int[][] ventas = gestionVentas.obtenerVentas();
        
        JLabel totalVentas = new JLabel("Total Ventas: " + 
                calcularTotalVentas(ventas));
        JLabel promedioVentas = new JLabel("Promedio Semanal: " 
                + calcularPromedioSemanal(ventas));
        JLabel tendencias = new JLabel("Tendencias: " + 
                detectarTendencias(ventas));

        panelEstadisticas.add(totalVentas);
        panelEstadisticas.add(promedioVentas);
        panelEstadisticas.add(tendencias);

        add(panelEstadisticas, BorderLayout.CENTER);

        // Botón para regresar
        JButton botonRegresar = new JButton("Regresar");
        botonRegresar.addActionListener(e -> dispose());
        add(botonRegresar, BorderLayout.SOUTH);

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
        int total = calcularTotalVentas(ventas);
        return total / 7.0; // Asume 7 días en la semana
    }

    private String detectarTendencias(int[][] ventas) {
        /**
         * Aquí puedes implementar un análisis más detallado usando 
         * la clase AnalisisRecursivo
         */
        return "Tendencias aún no implementadas";
    }

    public static void main(String[] args) {
        SistemaVentas gestion = new SistemaVentas(
            new String[]{"Producto A", "Producto B", "Producto C"},
            new String[]{"Tienda Física", "En Línea"}
        );
        new InterfazReportes(gestion);
    }
}

