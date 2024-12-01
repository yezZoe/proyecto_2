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
 * Esta clase representa un gráfico de barras para visualizar las ventas de productos
 * en diferentes canales. Permite mostrar las ventas de varios productos a través
 * de barras de diferentes colores para cada canal.
 * 
 * This class represents a bar chart to visualize product sales across different
 * channels. It allows displaying sales of several products through bars of
 * different colors for each channel.
 */
public class GraficoBarras extends JPanel {
    private int[][] ventas; // Matriz que almacena las ventas de cada producto en cada canal
                            // Matrix storing sales of each product in each channel
    private String[] productos; // Lista de productos
                                // List of products
    private String[] canales; // Lista de canales
                              // List of sales channels

    /**
     * Constructor para inicializar los datos del gráfico.
     * Constructor to initialize the chart data.
     *
     * @param ventas    Array bidimensional con las ventas de cada producto en cada canal.
     *                  2D array with sales for each product in each channel.
     * @param productos Lista de nombres de los productos.
     *                  List of product names.
     * @param canales   Lista de canales de venta (por ejemplo, tienda física, en línea).
     *                  List of sales channels (e.g., physical store, online).
     */
    public GraficoBarras(int[][] ventas, String[] productos, String[] canales) {
        this.ventas = ventas;
        this.productos = productos;
        this.canales = canales;
    }

    /**
     * Método para dibujar el gráfico de barras.
     * Method to draw the bar chart.
     *
     * @param g Objeto gráfico utilizado para dibujar.
     *          Graphics object used for drawing.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Configuración inicial
        // Initial setup
        int anchoBarra = 40; // Ancho de cada barra más delgado / Width of each bar, thinner
        int espacio = 30; // Espacio entre barras / Space between bars
        int margenIzquierdo = 100; // Margen izquierdo del gráfico / Left margin of the chart
        int margenInferior = getHeight() - 50; // Margen inferior / Bottom margin
        int alturaMaxima = 300; // Altura máxima de las barras / Maximum height of the bars

        // Encontrar la mayor cantidad de ventas para escalar las barras
        // Find the highest sales value to scale the bars
        int maxVentas = 0;
        for (int[] producto : ventas) {
            for (int canal : producto) {
                maxVentas = Math.max(maxVentas, canal);
            }
        }

        // Configuración del estilo de las líneas (activando antialiasing)
        // Line style configuration (enabling antialiasing)
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar el eje vertical (Y)
        // Draw the vertical axis (Y)
        g2d.setColor(Color.BLACK);
        g2d.drawLine(margenIzquierdo, margenInferior, margenIzquierdo, margenInferior - alturaMaxima);
        g2d.drawString("Ventas", margenIzquierdo - 40, margenInferior - alturaMaxima);

        // Dibujar el eje horizontal (X)
        // Draw the horizontal axis (X)
        g2d.drawLine(margenIzquierdo, margenInferior, getWidth() - 50, margenInferior);
        g2d.drawString("Productos", getWidth() - 100, margenInferior + 20);

        // Dibujar las barras
        // Draw the bars
        int x = margenIzquierdo + espacio;
        Color[] colores = {new Color(135, 206, 250), new Color(255, 182, 193)}; // Colores suaves y modernos / Soft and modern colors

        for (int i = 0; i < productos.length; i++) {
            for (int j = 0; j < canales.length; j++) {
                int altura = (int) ((ventas[i][j] / (double) maxVentas) * alturaMaxima); // Calcular altura de las barras / Calculate the bar height
                g2d.setColor(colores[j]);
                g2d.fillRoundRect(x, margenInferior - altura, anchoBarra, altura, 15, 15); // Barras con bordes redondeados / Rounded bars with smooth edges
                g2d.setColor(Color.BLACK);
                g2d.drawRoundRect(x, margenInferior - altura, anchoBarra, altura, 15, 15); // Dibujar borde redondeado / Draw rounded border
                x += anchoBarra;
            }
            x += espacio; // Espacio entre productos / Space between products
        }

        // Dibujar etiquetas de productos
        // Draw product labels
        x = margenIzquierdo + espacio + anchoBarra / 2;
        for (String producto : productos) {
            g2d.setColor(Color.BLACK);
            g2d.drawString(producto, x, margenInferior + 15); // Etiquetas de productos / Product labels
            x += (anchoBarra * canales.length) + espacio; // Ajustar la posición / Adjust position
        }

        // Dibujar la leyenda
        // Draw the legend
        g2d.setColor(colores[0]);
        g2d.fillRect(getWidth() - 150, 30, 20, 20); // Rectángulo azul para Tienda Física / Blue rectangle for Physical Store
        g2d.setColor(Color.BLACK);
        g2d.drawString("Tienda Física", getWidth() - 120, 45);

        g2d.setColor(colores[1]);
        g2d.fillRect(getWidth() - 150, 60, 20, 20); // Rectángulo rosa para En Línea / Pink rectangle for Online
        g2d.setColor(Color.BLACK);
        g2d.drawString("En Línea", getWidth() - 120, 75);
    }

    /**
     * Método principal para probar el gráfico.
     * Main method to test the chart.
     */
    public static void main(String[] args) {
        // Datos de ejemplo
        // Example data
        int[][] ventas = {
            {100, 200}, // Producto A / Product A
            {150, 250}, // Producto B / Product B
            {300, 100}  // Producto C / Product C
        };
        String[] productos = {"Producto A", "Producto B", "Producto C"}; // Lista de productos / List of products
        String[] canales = {"Tienda Física", "En Línea"}; // Lista de canales / List of channels

        // Crear ventana para mostrar el gráfico
        // Create window to display the chart
        JFrame ventana = new JFrame("Gráfico de Ventas"); // Título de la ventana / Window title
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(800, 600); // Tamaño de la ventana / Window size

        // Crear el gráfico y añadirlo a la ventana
        // Create the chart and add it to the window
        GraficoBarras grafico = new GraficoBarras(ventas, productos, canales);
        ventana.add(grafico);

        ventana.setVisible(true); // Hacer visible la ventana / Make the window visible
    }
}
