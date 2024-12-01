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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que representa la interfaz gráfica para registrar ventas.
 * This class represents the graphical interface for registering sales.
 */
public class InterfazRegistroVentas extends JFrame {
    private SistemaVentas gestionVentas; // Objeto para manejar el sistema de ventas / Object to manage the sales system.

    /**
     * Constructor para crear la interfaz gráfica.
     * Constructor to create the graphical interface.
     */
    public InterfazRegistroVentas() {
        // Inicialización del sistema de ventas con productos y canales predefinidos
        // Initialize the sales system with predefined products and channels
        gestionVentas = new SistemaVentas(
            new String[]{"Producto A", "Producto B", "Producto C"},
            new String[]{"Tienda Física", "En Línea"}
        );

        // Configuración de la ventana principal
        // Setting up the main window
        setTitle("Registro de Ventas"); // Título de la ventana / Window title
        setSize(400, 300); // Tamaño de la ventana / Window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configuración de cierre de ventana / Window close operation
        setLayout(new GridLayout(4, 2)); // Configuración del layout de la ventana / Window layout setup

        // Crear la barra de menú
        // Create the menu bar
        JMenuBar barraMenu = new JMenuBar();  // Create a menu bar
        JMenu menuOpciones = new JMenu("Menú");  // Create a menu called "Menu"
        
        // Opción "Ver Reportes" en el menú
        // Option "View Reports" in the menu
        JMenuItem itemVerReportes = new JMenuItem("Ver Reportes");
        itemVerReportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí se crea una nueva instancia de la interfaz de reportes
                // Here we create a new instance of the reports interface
                new InterfazReportes(gestionVentas);
            }
        });

        // Opción "Ver Reportes Avanzados" en el menú
        // Option "View Advanced Reports" in the menu
        JMenuItem itemVerReportesAvanzados = new JMenuItem("Ver Reportes Avanzados");
        itemVerReportesAvanzados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí se crea una nueva instancia de la interfaz de reportes avanzados
                // Here we create a new instance of the advanced reports interface
                new VentanaReportesAvanzados(gestionVentas);
            }
        });

        // Opción "Ver Gráfico de Ventas" en el menú
        // Option "View Sales Chart" in the menu
        JMenuItem itemVerGraficoVentas = new JMenuItem("Ver Gráfico de Ventas");
        itemVerGraficoVentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear ventana para el gráfico de barras
                // Create a window for the bar chart
                int[][] ventas = gestionVentas.obtenerVentas(); // Obtener datos de ventas / Get sales data
                String[] productos = gestionVentas.getProductos(); // Obtener lista de productos / Get list of products
                String[] canales = gestionVentas.getCanales(); // Obtener lista de canales / Get list of sales channels

                // Crear una nueva ventana para mostrar el gráfico de barras
                // Create a new window to display the bar chart
                JFrame ventanaGrafico = new JFrame("Gráfico de Ventas"); // Título de la ventana del gráfico / Chart window title
                ventanaGrafico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Configuración de cierre de la ventana del gráfico / Close operation for chart window
                ventanaGrafico.setSize(800, 600); // Tamaño de la ventana / Window size
                
                // Instanciamos el gráfico con los datos
                // Instantiate the chart with the data
                GraficoBarras grafico = new GraficoBarras(ventas, productos, canales);
                ventanaGrafico.add(grafico); // Añadir el gráfico a la ventana / Add the chart to the window
                
                // Mostrar la ventana con el gráfico
                // Display the window with the chart
                ventanaGrafico.setVisible(true); // Hacer visible la ventana del gráfico / Make the chart window visible
            }
        });
        
        // Opción "Salir" en el menú
        // Option "Exit" in the menu
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cierra la aplicación
                // Close the application
                System.exit(0);  // Cierra la aplicación / Close the application
            }
        });
       
        // Añadir las opciones al menú
        // Add the options to the menu
        menuOpciones.add(itemVerReportes);  // Añadimos la opción "Ver Reportes" / Add the "View Reports" option
        menuOpciones.add(itemVerGraficoVentas);  // Añadimos la opción "Ver Gráfico de Ventas" / Add the "View Sales Chart" option
        menuOpciones.add(itemVerReportesAvanzados);  // Añadimos la opción "Ver Reportes Avanzados" / Add the "View Advanced Reports" option
        menuOpciones.add(itemSalir);  // Añadimos la opción "Salir" / Add the "Exit" option
        barraMenu.add(menuOpciones);  // Añadir el menú a la barra de menú / Add the menu to the menu bar
        
        // **Asociar la barra de menú con la ventana**
        // **Associate the menu bar with the window**
        setJMenuBar(barraMenu);  // Asignar la barra de menú a la ventana / Assign the menu bar to the window
        
        // Crear las etiquetas y campos para ingresar los datos
        // Create the labels and fields to input the data
        JLabel etiquetaProducto = new JLabel("Producto:"); // Etiqueta para el campo de producto / Label for the product field
        JComboBox<String> comboProducto = 
                new JComboBox<>(gestionVentas.getProductos()); // Combo box para seleccionar el producto / Combo box to select the product
        JLabel etiquetaCanal = new JLabel("Canal:"); // Etiqueta para el campo de canal / Label for the channel field
        JComboBox<String> comboCanal = 
                new JComboBox<>(gestionVentas.getCanales()); // Combo box para seleccionar el canal / Combo box to select the channel
        JLabel etiquetaCantidad = new JLabel("Cantidad:"); // Etiqueta para el campo de cantidad / Label for the quantity field
        JTextField campoCantidad = new JTextField(); // Campo de texto para ingresar la cantidad / Text field to input the quantity

        // Crear el botón para registrar ventas
        // Create the button to register sales
        JButton botonRegistrar = new JButton("Registrar Venta"); // Botón para registrar la venta / Button to register the sale
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int producto = comboProducto.getSelectedIndex();  // Obtener el índice del producto seleccionado / Get the selected product index
                    int canal = comboCanal.getSelectedIndex();  // Obtener el índice del canal seleccionado / Get the selected channel index
                    int cantidad = Integer.parseInt(campoCantidad.getText());  // Convertir la cantidad ingresada a número / Convert the input quantity to number

                    // Registrar la venta
                    // Register the sale
                    gestionVentas.registrarVenta(producto, canal, cantidad); // Registrar la venta en el sistema / Register the sale in the system
                    JOptionPane.showMessageDialog(null, "Venta registrada correctamente."); // Mostrar mensaje de éxito / Show success message
                } catch (NumberFormatException ex) {
                    // Si ocurre un error al ingresar la cantidad, mostrar mensaje de error
                    // If an error occurs when entering the quantity, show error message
                    JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida."); // Mostrar mensaje de error / Show error message
                }
            }
        });

        // Añadir los componentes a la ventana
        // Add the components to the window
        add(etiquetaProducto); // Añadir etiqueta de producto / Add product label
        add(comboProducto); // Añadir combo box de productos / Add product combo box
        add(etiquetaCanal); // Añadir etiqueta de canal / Add channel label
        add(comboCanal); // Añadir combo box de canales / Add channel combo box
        add(etiquetaCantidad); // Añadir etiqueta de cantidad / Add quantity label
        add(campoCantidad); // Añadir campo de cantidad / Add quantity field
        add(botonRegistrar); // Añadir botón de registrar venta / Add register sale button

        // Hacer visible la ventana
        // Make the window visible
        setVisible(true); // Mostrar la ventana / Show the window
    }

    /**
     * Método principal para iniciar la interfaz.
     * Main method to launch the interface.
     */
    public static void main(String[] args) {
        // Iniciar la interfaz en el hilo de eventos de Swing
        // Launch the interface in the Swing event thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazRegistroVentas();  // Crear la interfaz / Create the interface
            }
        });
    }
}
