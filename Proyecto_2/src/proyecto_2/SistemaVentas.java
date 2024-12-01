/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_2;
/**
 * Class to manage store sales.
 * Clase para gestionar las ventas en la tienda.
 *
 * @author YESLYDANIELAFIGUEROA
 * @author NICOLELEIVAFALLAS
 */
public class SistemaVentas {

    private int[][] ventas; // 2D array to store sales
    private String[] productos; // List of products
    private String[] canales; // List of sales channels

    /**
     * Constructor to initialize sales management.
     * Constructor para inicializar la gestión de ventas.
     *
     * @param productos List of available products
     * @param canales List of sales channels
     */
    public SistemaVentas(String[] productos, String[] canales) {
        this.productos = productos;
        this.canales = canales;
        this.ventas = new int[productos.length][canales.length];
    }

    /**
     * Get method to obtain the sales.
     * Metodo get para obtener las ventas.
     *
     * @return 2D array with recorded sales
     */
    public int[][] getVentas() {
        return ventas;
    }

    /**
     * Set method to set the sales.
     * Metodo set para establecer las ventas.
     *
     * @param ventas 2D array with recorded sales
     */
    public void setVentas(int[][] ventas) {
        this.ventas = ventas;
    }

    /**
     * Get method to obtain the products.
     * Metodo get para obtener los productos.
     *
     * @return List of products
     */
    public String[] getProductos() {
        return productos;
    }

    /**
     * Set method to set the products.
     * Metodo set para establecer los productos.
     *
     * @param productos List of products
     */
    public void setProductos(String[] productos) {
        this.productos = productos;
    }

    /**
     * Get method to obtain the channels.
     * Metodo get para obtener los canales.
     *
     * @return List of channels
     */
    public String[] getCanales() {
        return canales;
    }

    /**
     * Set method to set the channels.
     * Metodo set para establecer los canales.
     *
     * @param canales List of channels
     */
    public void setCanales(String[] canales) {
        this.canales = canales;
    }

    /**
     * Register a sale.
     * Registrar una venta.
     *
     * @param producto Product index sold
     * @param canal Sales channel index
     * @param cantidad Quantity sold
     */
    public void registrarVenta(int producto, int canal, int cantidad) {
        if (producto >= 0 && producto < 
                productos.length && canal >= 0 && canal < canales.length) {
            ventas[producto][canal] += cantidad; // Registers the sale
        } else {
            System.out.println(
                    "Error: Índice de producto o canal fuera de rango.");
        }
    }

    /**
     * Get sales by product and channel.
     * Obtener las ventas por producto y canal.
     *
     * @return 2D array with recorded sales
     */
    public int[][] obtenerVentas() {
        return ventas;
    }
}
