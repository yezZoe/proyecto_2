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
 * This class represents a bar chart to visualize product sales across different channels.
 */
public class GraficoBarras extends JPanel {

    private int[][] ventas; // Array that stores the sales for each product.
    private String[] productos; // List of product names.
    private String[] canales; // List of sales channels.

    /**
     * Constructor to initialize the chart data.
     *
     * @param ventas 2D array with sales data for each product and channel.
     * @param productos List of product names.
     * @param canales List of sales channels (e.g., physical store, online).
     */
    public GraficoBarras(int[][] ventas, String[] productos, String[] canales) {
        this.ventas = ventas;
        this.productos = productos;
        this.canales = canales;
    }

    /**
     * Method to draw the bar chart.
     *
     * @param g Graphics object used for drawing.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set background gradient
        GradientPaint fondo = new GradientPaint(0, 0, new Color(220, 240, 255),
                 getWidth(), getHeight(), new Color(190, 220, 240));
        g2d.setPaint(fondo);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Initial settings
        int anchoBarra = 50; // Width of each bar
        int espacio = 20; // Space between bar groups
        int margenIzquierdo = 100; // Left margin of the chart
        int margenInferior = getHeight() - 80; // Bottom margin
        int alturaMaxima = 300; // Maximum height of the bars

        // Find the maximum sales value to scale the bars
        int maxVentas = 0;
        for (int[] producto : ventas) {
            for (int canal : producto) {
                maxVentas = Math.max(maxVentas, canal);
            }
        }

        // Enable anti-aliasing for smooth lines
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the vertical axis (Y)
        g2d.setColor(Color.BLACK);
        g2d.drawLine(margenIzquierdo, margenInferior, margenIzquierdo, margenInferior - alturaMaxima);
        g2d.drawString("Sales", margenIzquierdo - 50, margenInferior - alturaMaxima - 10);

        // Draw the horizontal axis (X)
        g2d.drawLine(margenIzquierdo, margenInferior, getWidth() - 50, margenInferior);
        g2d.drawString("Products", getWidth() - 100, margenInferior + 30);

        // Custom colors for the bars
        Color[] colores = {new Color(70, 130, 180), new Color(255, 99, 71), new Color(34, 139, 34)};
        Color[] bordes = {new Color(0, 102, 153), new Color(204, 51, 51), new Color(0, 102, 51)};

        // Draw the bars
        int x = margenIzquierdo + espacio;
        for (int i = 0; i < productos.length; i++) {
            for (int j = 0; j < canales.length; j++) {
                int altura = (int) ((ventas[i][j] / (double) maxVentas) * alturaMaxima);
                g2d.setColor(colores[j % colores.length]);
                g2d.fillRoundRect(x, margenInferior - altura, anchoBarra, altura, 10, 10);
                g2d.setColor(bordes[j % bordes.length]);
                g2d.drawRoundRect(x, margenInferior - altura, anchoBarra, altura, 10, 10);
                x += anchoBarra + 5;
            }
            x += espacio;
        }

        // Draw product labels
        x = margenIzquierdo + espacio + anchoBarra / 2;
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        for (String producto : productos) {
            g2d.setColor(Color.BLACK);
            g2d.drawString(producto, x - 15, margenInferior + 15);
            x += (anchoBarra * canales.length) + espacio;
        }

        // Draw legend
        int leyendaX = getWidth() - 160;
        int leyendaY = 30;
        g2d.setFont(new Font("Arial", Font.PLAIN, 14));
        for (int i = 0; i < canales.length; i++) {
            g2d.setColor(colores[i % colores.length]);
            g2d.fillRect(leyendaX, leyendaY, 20, 20);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(leyendaX, leyendaY, 20, 20);
            g2d.drawString(canales[i], leyendaX + 30, leyendaY + 15);
            leyendaY += 30;
        }
    }

    /**
     * Main method to test the chart.
     * @param args
     */
    public static void main(String[] args) {
        // Example data
        int[][] ventas = {
            {120, 200}, // Product A
            {150, 100}, // Product B
            {90, 250} // Product C
        };
        String[] productos = {"Product A", "Product B", "Product C"};
        String[] canales = {"Physical Store", "Online"};

        // Create window to display the chart
        JFrame ventana = new JFrame("Sales Chart");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(800, 600);

        // Create the chart and add it to the window
        GraficoBarras grafico = new GraficoBarras(ventas, productos, canales);
        ventana.add(grafico);

        ventana.setVisible(true);
    }
}
