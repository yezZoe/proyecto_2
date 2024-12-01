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
 */
public class GraficoBarras extends JPanel {
    private int[][] ventas; // Matriz que almacena las ventas de cada producto en cada canal.
    private String[] productos; // Lista de productos.
    private String[] canales; // Lista de canales.

    /**
     * Constructor para inicializar los datos del gráfico.
     *
     * @param ventas    Array bidimensional con las ventas de cada producto en cada canal.
     * @param productos Lista de nombres de los productos.
     * @param canales   Lista de canales de venta (por ejemplo, tienda física, en línea).
     */
    public GraficoBarras(int[][] ventas, String[] productos, String[] canales) {
        this.ventas = ventas;
        this.productos = productos;
        this.canales = canales;
    }

    /**
     * Método para dibujar el gráfico de barras.
     *
     * @param g Objeto gráfico utilizado para dibujar.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Fondo degradado ajustado
        GradientPaint fondo = new GradientPaint(0, 0, new Color(220, 240, 255), getWidth(), getHeight(), new Color(190, 220, 240));
        g2d.setPaint(fondo);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Configuración inicial
        int anchoBarra = 50; // Ancho de cada barra
        int espacio = 20; // Espacio entre grupos de barras
        int margenIzquierdo = 100; // Margen izquierdo del gráfico
        int margenInferior = getHeight() - 80; // Margen inferior
        int alturaMaxima = 300; // Altura máxima de las barras

        // Encontrar la mayor cantidad de ventas para escalar las barras
        int maxVentas = 0;
        for (int[] producto : ventas) {
            for (int canal : producto) {
                maxVentas = Math.max(maxVentas, canal);
            }
        }

        // Configuración del estilo de las líneas (activando antialiasing)
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar el eje vertical (Y)
        g2d.setColor(Color.BLACK);
        g2d.drawLine(margenIzquierdo, margenInferior, margenIzquierdo, margenInferior - alturaMaxima);
        g2d.drawString("Ventas", margenIzquierdo - 50, margenInferior - alturaMaxima - 10);

        // Dibujar el eje horizontal (X)
        g2d.drawLine(margenIzquierdo, margenInferior, getWidth() - 50, margenInferior);
        g2d.drawString("Productos", getWidth() - 100, margenInferior + 30);

        // Colores personalizados con alto contraste para las barras
        Color[] colores = {new Color(70, 130, 180), new Color(255, 99, 71), new Color(34, 139, 34)};
        Color[] bordes = {new Color(0, 102, 153), new Color(204, 51, 51), new Color(0, 102, 51)};

        // Dibujar las barras
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

        // Dibujar etiquetas de productos
        x = margenIzquierdo + espacio + anchoBarra / 2;
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        for (String producto : productos) {
            g2d.setColor(Color.BLACK);
            g2d.drawString(producto, x - 15, margenInferior + 15);
            x += (anchoBarra * canales.length) + espacio;
        }

        // Dibujar la leyenda
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
     * Método principal para probar el gráfico.
     */
    public static void main(String[] args) {
        // Datos de ejemplo
        int[][] ventas = {
            {120, 200}, // Producto A
            {150, 100}, // Producto B
            {90, 250}   // Producto C
        };
        String[] productos = {"Producto A", "Producto B", "Producto C"};
        String[] canales = {"Tienda Física", "En Línea"};

        // Crear ventana para mostrar el gráfico
        JFrame ventana = new JFrame("Gráfico de Ventas");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(800, 600);

        // Crear el gráfico y añadirlo a la ventana
        GraficoBarras grafico = new GraficoBarras(ventas, productos, canales);
        ventana.add(grafico);

        ventana.setVisible(true);
    }
}
