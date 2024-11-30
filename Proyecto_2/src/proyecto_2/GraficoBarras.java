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

public class GraficoBarras extends JPanel {
    private int[][] ventas;
    private String[] productos;
    private String[] canales;

    /**
     * Constructor para inicializar los datos del gráfico.
     * Constructor to initialize the chart data.
     *
     * @param ventas    Array bidimensional con las ventas.
     * @param productos Lista de productos.
     * @param canales   Lista de canales.
     */
    public GraficoBarras(int[][] ventas, String[] productos, String[] canales) {
        this.ventas = ventas;
        this.productos = productos;
        this.canales = canales;
    }

    /**
     * Método para dibujar el gráfico.
     * Method to draw the chart.
     *
     * @param g Objeto gráfico.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Configuración inicial
        int anchoBarra = 50; // Ancho de cada barra
        int espacio = 20; // Espacio entre grupos de barras
        int margenIzquierdo = 100; // Margen izquierdo
        int margenInferior = getHeight() - 50; // Margen inferior
        int alturaMaxima = 300; // Altura máxima del gráfico

        // Encontrar la mayor cantidad de ventas para escalar las barras
        int maxVentas = 0;
        for (int[] producto : ventas) {
            for (int canal : producto) {
                maxVentas = Math.max(maxVentas, canal);
            }
        }

        // Dibujar el eje vertical
        g2d.drawLine(margenIzquierdo, margenInferior, margenIzquierdo, margenInferior - alturaMaxima);
        g2d.drawString("Ventas", margenIzquierdo - 40, margenInferior - alturaMaxima);

        // Dibujar el eje horizontal
        g2d.drawLine(margenIzquierdo, margenInferior, getWidth() - 50, margenInferior);
        g2d.drawString("Productos", getWidth() - 100, margenInferior + 20);

        // Dibujar las barras
        int x = margenIzquierdo + espacio;
        for (int i = 0; i < productos.length; i++) {
            for (int j = 0; j < canales.length; j++) {
                int altura = (int) ((ventas[i][j] / (double) maxVentas) * alturaMaxima);
                g2d.setColor(j == 0 ? Color.BLUE : Color.GREEN);
                g2d.fillRect(x, margenInferior - altura, anchoBarra, altura);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, margenInferior - altura, anchoBarra, altura);
                x += anchoBarra;
            }
            x += espacio; // Añadir espacio entre productos
        }

        // Dibujar etiquetas de productos
        x = margenIzquierdo + espacio + anchoBarra / 2;
        for (String producto : productos) {
            g2d.drawString(producto, x, margenInferior + 15);
            x += (anchoBarra * canales.length) + espacio;
        }

        // Dibujar la leyenda
        g2d.setColor(Color.BLUE);
        g2d.fillRect(getWidth() - 150, 30, 20, 20);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Tienda Física", getWidth() - 120, 45);

        g2d.setColor(Color.GREEN);
        g2d.fillRect(getWidth() - 150, 60, 20, 20);
        g2d.setColor(Color.BLACK);
        g2d.drawString("En Línea", getWidth() - 120, 75);
    }

    /**
     * Método principal para probar el gráfico.
     */
    public static void main(String[] args) {
        // Datos de ejemplo
        int[][] ventas = {
            {100, 200},
            {150, 250},
            {300, 100}
        };
        String[] productos = {"Producto A", "Producto B", "Producto C"};
        String[] canales = {"Tienda Física", "En Línea"};

        // Crear ventana
        JFrame ventana = new JFrame("Gráfico de Ventas");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(800, 600);

        // Añadir el gráfico
        GraficoBarras grafico = new GraficoBarras(ventas, productos, canales);
        ventana.add(grafico);

        ventana.setVisible(true);
    }
}


