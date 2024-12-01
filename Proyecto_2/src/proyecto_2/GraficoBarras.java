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
 * Esta clase representa un gráfico de barras para visualizar las ventas de productos en diferentes canales.
 * Permite mostrar las ventas de varios productos a través de barras de diferentes colores para cada canal.
 * 
 * This class represents a bar chart to visualize product sales across different channels.
 * It allows displaying sales of several products through bars of different colors for each channel.
 * 
 * @author Tu Nombre
 * @version 1.0
 */
public class GraficoBarras extends JPanel {
    private int[][] ventas; // Matriz que almacena las ventas de cada producto en cada canal / Matrix storing sales of each product in each channel
    private String[] productos; // Lista de productos / List of products
    private String[] canales; // Lista de canales / List of sales channels (e.g., physical store, online)

    /**
     * Constructor para inicializar los datos del gráfico.
     * Constructor to initialize the chart data.
     *
     * @param ventas    Array bidimensional con las ventas de cada producto en cada canal / 2D array with sales for each product in each channel.
     * @param productos Lista de nombres de los productos / List of product names.
     * @param canales   Lista de canales de venta (por ejemplo, tienda física, en línea) / List of sales channels (e.g., physical store, online).
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
     * @param g Objeto gráfico utilizado para dibujar / Graphics object used for drawing.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Configuración inicial / Initial settings
        int anchoBarra = 50; // Ancho de cada barra / Width of each bar
        int espacio = 20; // Espacio entre grupos de barras / Space between bar groups
        int margenIzquierdo = 100; // Margen izquierdo del gráfico / Left margin of the chart
        int margenInferior = getHeight() - 50; // Margen inferior para las etiquetas / Bottom margin for labels
        int alturaMaxima = 300; // Altura máxima de las barras del gráfico / Maximum height of the bars in the chart

        // Encontrar la mayor cantidad de ventas para escalar las barras / Find the highest sales value to scale the bars
        int maxVentas = 0;
        for (int[] producto : ventas) {
            for (int canal : producto) {
                maxVentas = Math.max(maxVentas, canal);
            }
        }

        // Dibujar el eje vertical / Draw the vertical axis
        g2d.drawLine(margenIzquierdo, margenInferior, margenIzquierdo, margenInferior - alturaMaxima); // Eje Y / Y axis
        g2d.drawString("Ventas", margenIzquierdo - 40, margenInferior - alturaMaxima); // Etiqueta de eje Y / Y axis label

        // Dibujar el eje horizontal / Draw the horizontal axis
        g2d.drawLine(margenIzquierdo, margenInferior, getWidth() - 50, margenInferior); // Eje X / X axis
        g2d.drawString("Productos", getWidth() - 100, margenInferior + 20); // Etiqueta de eje X / X axis label

        // Dibujar las barras / Draw the bars
        int x = margenIzquierdo + espacio; // Posición inicial de las barras / Initial position for bars
        for (int i = 0; i < productos.length; i++) {
            for (int j = 0; j < canales.length; j++) {
                int altura = (int) ((ventas[i][j] / (double) maxVentas) * alturaMaxima); // Calcular altura de la barra / Calculate the bar height
                g2d.setColor(j == 0 ? Color.BLUE : Color.GREEN); // Color para cada canal / Color for each channel
                g2d.fillRect(x, margenInferior - altura, anchoBarra, altura); // Dibuja la barra / Draw the bar
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, margenInferior - altura, anchoBarra, altura); // Dibuja el borde de la barra / Draw the bar border
                x += anchoBarra; // Mover posición para la siguiente barra / Move position for the next bar
            }
            x += espacio; // Añadir espacio entre productos / Add space between products
        }

        // Dibujar etiquetas de productos / Draw product labels
        x = margenIzquierdo + espacio + anchoBarra / 2; // Posición inicial de las etiquetas / Initial position for labels
        for (String producto : productos) {
            g2d.drawString(producto, x, margenInferior + 15); // Etiqueta debajo de cada grupo de barras / Label under each bar group
            x += (anchoBarra * canales.length) + espacio; // Ajustar la posición para la siguiente etiqueta / Adjust position for the next label
        }

        // Dibujar la leyenda / Draw the legend
        g2d.setColor(Color.BLUE);
        g2d.fillRect(getWidth() - 150, 30, 20, 20); // Rectángulo azul para Tienda Física / Blue rectangle for Physical Store
        g2d.setColor(Color.BLACK);
        g2d.drawString("Tienda Física", getWidth() - 120, 45); // Etiqueta de Tienda Física / Physical Store label

        g2d.setColor(Color.GREEN);
        g2d.fillRect(getWidth() - 150, 60, 20, 20); // Rectángulo verde para En Línea / Green rectangle for Online
        g2d.setColor(Color.BLACK);
        g2d.drawString("En Línea", getWidth() - 120, 75); // Etiqueta de En Línea / Online label
    }

    /**
     * Método principal para probar el gráfico.
     * Main method to test the chart.
     */
    public static void main(String[] args) {
        // Datos de ejemplo / Example data
        int[][] ventas = {
            {100, 200}, // Ventas para Producto A: 100 en Tienda Física, 200 en Línea / Sales for Product A: 100 in Physical Store, 200 Online
            {150, 250}, // Ventas para Producto B: 150 en Tienda Física, 250 en Línea / Sales for Product B: 150 in Physical Store, 250 Online
            {300, 100}  // Ventas para Producto C: 300 en Tienda Física, 100 en Línea / Sales for Product C: 300 in Physical Store, 100 Online
        };
        String[] productos = {"Producto A", "Producto B", "Producto C"}; // Lista de productos / List of products
        String[] canales = {"Tienda Física", "En Línea"}; // Lista de canales / List of sales channels

        // Crear ventana para mostrar el gráfico / Create window to display the chart
        JFrame ventana = new JFrame("Gráfico de Ventas"); // Título de la ventana / Window title
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(800, 600); // Tamaño de la ventana / Window size

        // Crear el gráfico y añadirlo a la ventana / Create the chart and add it to the window
        GraficoBarras grafico = new GraficoBarras(ventas, productos, canales);
        ventana.add(grafico);

        ventana.setVisible(true); // Hacer visible la ventana / Make the window visible
    }
}
