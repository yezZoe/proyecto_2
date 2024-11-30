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
    private SistemaVentas gestionVentas;

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
        setTitle("Registro de Ventas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

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
                int[][] ventas = gestionVentas.obtenerVentas(); // Obtener datos de ventas
                String[] productos = gestionVentas.getProductos();
                String[] canales = gestionVentas.getCanales();

                // Crear una nueva ventana para mostrar el gráfico de barras
                // Create a new window to display the bar chart
                JFrame ventanaGrafico = new JFrame("Gráfico de Ventas");
                ventanaGrafico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ventanaGrafico.setSize(800, 600);
                
                // Instanciamos el gráfico con los datos
                // Instantiate the chart with the data
                GraficoBarras grafico = new GraficoBarras(ventas, productos, canales);
                ventanaGrafico.add(grafico);
                
                // Mostrar la ventana con el gráfico
                // Display the window with the chart
                ventanaGrafico.setVisible(true);
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
                System.exit(0);  // Cierra la aplicación
            }
        });
       
        // Añadir las opciones al menú
        // Add the options to the menu
        menuOpciones.add(itemVerReportes);  // Añadimos la opción "Ver Reportes"
        menuOpciones.add(itemVerGraficoVentas);  // Añadimos la opción "Ver Gráfico de Ventas"
        menuOpciones.add(itemVerReportesAvanzados);  // Añadimos la opción "Ver Reportes Avanzados"
        menuOpciones.add(itemSalir);  // Añadimos la opción "Salir"
        barraMenu.add(menuOpciones);  // Añadir el menú a la barra de menú
        
        // **Asociar la barra de menú con la ventana**
        // **Associate the menu bar with the window**
        setJMenuBar(barraMenu);  // Asignar la barra de menú a la ventana
        
        // Crear las etiquetas y campos para ingresar los datos
        // Create the labels and fields to input the data
        JLabel etiquetaProducto = new JLabel("Producto:");
        JComboBox<String> comboProducto = 
                new JComboBox<>(gestionVentas.getProductos());
        JLabel etiquetaCanal = new JLabel("Canal:");
        JComboBox<String> comboCanal = 
                new JComboBox<>(gestionVentas.getCanales());
        JLabel etiquetaCantidad = new JLabel("Cantidad:");
        JTextField campoCantidad = new JTextField();

        // Crear el botón para registrar ventas
        // Create the button to register sales
        JButton botonRegistrar = new JButton("Registrar Venta");
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int producto = comboProducto.getSelectedIndex();  // Obtener el índice del producto seleccionado
                    int canal = comboCanal.getSelectedIndex();  // Obtener el índice del canal seleccionado
                    int cantidad = Integer.parseInt(campoCantidad.getText());  // Convertir la cantidad ingresada a número

                    // Registrar la venta
                    // Register the sale
                    gestionVentas.registrarVenta(producto, canal, cantidad);
                    JOptionPane.showMessageDialog(null, "Venta registrada correctamente.");
                } catch (NumberFormatException ex) {
                    // Si ocurre un error al ingresar la cantidad, mostrar mensaje de error
                    // If an error occurs when entering the quantity, show error message
                    JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida.");
                }
            }
        });

        // Añadir los componentes a la ventana
        // Add the components to the window
        add(etiquetaProducto);
        add(comboProducto);
        add(etiquetaCanal);
        add(comboCanal);
        add(etiquetaCantidad);
        add(campoCantidad);
        add(botonRegistrar);

        // Hacer visible la ventana
        // Make the window visible
        setVisible(true);
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
                new InterfazRegistroVentas();  // Crear la interfaz
            }
        });
    }
}
