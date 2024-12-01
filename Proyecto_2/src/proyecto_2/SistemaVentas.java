/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_2;

/**
 * Clase para gestionar las ventas en la tienda.
 * Class to manage store sales.
 * 
 * @author YESLYDANIELAFIGUEROA
 * @author NICOLELEIVAFALLAS
 */
public class SistemaVentas {

    private int[][] ventas; // Array bidimensional para almacenar las ventas / 2D array to store sales
    private String[] productos; // Lista de productos / List of products
    private String[] canales; // Tipos de canales (tienda física/en línea) / Types of channels (physical store/online)

    /**
     * Constructor para inicializar la gestión de ventas.
     * Constructor to initialize sales management.
     *
     * @param productos Lista de productos disponibles / List of available products
     * @param canales Tipos de canales de venta / Types of sales channels
     */
    public SistemaVentas(String[] productos, String[] canales) {
        this.productos = productos;
        this.canales = canales;
        this.ventas = new int[productos.length][canales.length]; // Inicializa el array de ventas / Initializes the sales array
    }
     
    /**
     * Metodo get para obtener las ventas.
     * Get method to obtain the sales.
     * @return Array bidimensional con las ventas registradas / 2D array with recorded sales
     */
    public int[][] getVentas() {
        return ventas;
    }

    /**
     * Metodo set para establecer las ventas.
     * Set method to set the sales.
     * @param ventas Array bidimensional con las ventas registradas / 2D array with recorded sales
     */
    public void setVentas(int[][] ventas) {
        this.ventas = ventas;
    }

    /**
     * Metodo get para obtener los productos.
     * Get method to obtain the products.
     * @return Lista de productos / List of products
     */
    public String[] getProductos() {
        return productos;
    }

    /**
     * Metodo set para establecer los productos.
     * Set method to set the products.
     * @param productos Lista de productos / List of products
     */
    public void setProductos(String[] productos) {
        this.productos = productos;
    }

    /**
     * Metodo get para obtener los canales.
     * Get method to obtain the channels.
     * @return Lista de canales / List of channels
     */
    public String[] getCanales() {
        return canales;
    }

    /**
     * Metodo set para establecer los canales.
     * Set method to set the channels.
     * @param canales Lista de canales / List of channels
     */
    public void setCanales(String[] canales) {
        this.canales = canales;
    }

    /**
     * Registrar una venta.
     * Register a sale.
     *
     * @param producto Índice del producto vendido / Product index sold
     * @param canal Índice del canal de venta / Sales channel index
     * @param cantidad Cantidad vendida / Quantity sold
     */
    public void registrarVenta(int producto, int canal, int cantidad) {
        if (producto >= 0 && producto < productos.length && canal >= 0 && canal < canales.length) {
            ventas[producto][canal] += cantidad; // Registra la venta / Registers the sale
        } else {
            System.out.println("Error: Índice de producto o canal fuera de rango."); // Error si los índices están fuera de rango / Error if the indices are out of range
        }
    }

    /**
     * Obtener las ventas por producto y canal.
     * Get sales by product and channel.
     *
     * @return Array bidimensional con las ventas registradas / 2D array with recorded sales
     */
    public int[][] obtenerVentas() {
        return ventas;
    }
}
