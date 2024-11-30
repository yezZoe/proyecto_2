/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_2;

/**
 *
 * @author YESLYDANIELAFIGUEROA
 * @author NICOLELEIVAFALLAS
 */
/**
 * Clase para gestionar las ventas en la tienda Class to manage store sales
 */
public class SistemaVentas {

    private int[][] ventas;
    private String[] productos; // Lista de productos
    private String[] canales; // Tipos de canales (tienda física/en línea)

    /**
     * Constructor para inicializar la gestión de ventas. Constructor to
     * initialize sales management.
     *
     * @param productos Lista de productos disponibles.
     * @param canales Tipos de canales de venta.
     */
    public SistemaVentas(String[] productos, String[] canales) {
        this.productos = productos;
        this.canales = canales;
        this.ventas = new int[productos.length][canales.length];
    }
     
    /**
     * Metodo get y setters 
     * @return 
     */
    public int[][] getVentas() {
        return ventas;
    }
    

    public void setVentas(int[][] ventas) {
        this.ventas = ventas;
    }

    public String[] getProductos() {
        return productos;
    }

    public void setProductos(String[] productos) {
        this.productos = productos;
    }

    public String[] getCanales() {
        return canales;
    }

    public void setCanales(String[] canales) {
        this.canales = canales;
    }

    /**
     * Registrar una venta. Register a sale.
     *
     * @param producto Índice del producto vendido.
     * @param canal Índice del canal de venta.
     * @param cantidad Cantidad vendida.
     */
    public void registrarVenta(int producto, int canal, int cantidad) {
        if (producto >= 0 && producto < productos.length && canal >= 0 && canal < canales.length) {
            ventas[producto][canal] += cantidad;
        } else {
            System.out.println("Error: Índice de producto o canal fuera de rango.");
        }
    }

    /**
     * Obtener las ventas por producto y canal. Get sales by product and
     * channel.
     *
     * @return Array bidimensional con las ventas registradas.
     */
    public int[][] obtenerVentas() {
        return ventas;
    }
}
