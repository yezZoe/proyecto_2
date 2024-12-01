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
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

/**
 * Interfaz para registrar ventas y gestionar reportes. 
 * Interface to register
 * sales and manage reports.
 */

public class InterfazRegistroVentas extends JFrame {

    private SistemaVentas gestionVentas; // Object to manage the sales system
    private GestorArchivo gestorArchivo; // Object to manage file persistence

    /**
     * Constructor to create the graphical interface.
     */
    public InterfazRegistroVentas() {
        // Initialize sales system with predefined products and channels
        gestionVentas = new SistemaVentas(
                new String[]{"Producto A", "Producto B", "Producto C"},
                new String[]{"Tienda Física", "En Línea"}
        );

        // Initialize file manager
        gestorArchivo = new GestorArchivo();

        // Configure the main window
        setTitle("N&Y Essence");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel configuration
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(230, 220, 255));
        panelPrincipal.setBorder(new EmptyBorder
        (10, 10, 10, 10));

        // Create menu bar
        JMenuBar barraMenu = new JMenuBar();
        barraMenu.setBackground(new Color(200, 180, 255));
        barraMenu.setBorderPainted(false);
        barraMenu.setOpaque(true);

        // Menu and items
        JMenu menuOpciones = new JMenu("Menú");
        JMenuItem itemVerReportes = new JMenuItem("Ver Reportes");
        JMenuItem itemVerReportesAvanzados = new JMenuItem
        ("Ver Reportes Avanzados");
        JMenuItem itemVerGraficoVentas = new JMenuItem
        ("Ver Gráfico de Ventas");
        JMenuItem itemCargarVentas = new JMenuItem("Todas las Ventas");
        JMenuItem itemSalir = new JMenuItem("Salir");

        // Add actions to menu items
        itemVerReportes.addActionListener(e -> 
                new InterfazReportes(gestionVentas));
        itemVerReportesAvanzados.addActionListener
        (e -> new VentanaReportesAvanzados(gestionVentas));
        itemVerGraficoVentas.addActionListener(e -> {
            int[][] ventas = gestionVentas.obtenerVentas();
            String[] productos = gestionVentas.getProductos();
            String[] canales = gestionVentas.getCanales();
            JFrame ventanaGrafico = new JFrame("Gráfico de Ventas");
            ventanaGrafico.setDefaultCloseOperation(
                    JFrame.DISPOSE_ON_CLOSE);
            ventanaGrafico.setSize(800, 600);
            GraficoBarras grafico =
                    new GraficoBarras(ventas, productos, canales);
            ventanaGrafico.add(grafico);
            ventanaGrafico.setVisible(true);
        });
        itemCargarVentas.addActionListener(e -> {
            List<String> ventas = gestorArchivo.cargarVentas();
            StringBuilder mensaje =
                    new StringBuilder("Ventas registradas:\n");
            for (String venta : ventas) {
                mensaje.append(venta).append("\n");
            }
            JOptionPane.showMessageDialog
        (null, mensaje.toString());
        });
        itemSalir.addActionListener(e -> System.exit(0));

        // Add menu items to menu and menu to menu bar
        menuOpciones.add(itemVerReportes);
        menuOpciones.add(itemVerGraficoVentas);
        menuOpciones.add(itemCargarVentas);
        menuOpciones.add(itemVerReportesAvanzados);
        menuOpciones.add(itemSalir);
        barraMenu.add(menuOpciones);

        // Associate the menu bar with the window
        setJMenuBar(barraMenu);

        // Data input panel
        JPanel panelEntrada = new JPanel
        (new GridLayout(4, 2, 10, 10));
        panelEntrada.setBackground(new Color(230, 220, 255));

        // Create input components
        JLabel etiquetaProducto = new JLabel("Producto:");
        JComboBox<String> comboProducto = new 
        JComboBox<>(gestionVentas.getProductos());
        JLabel etiquetaCanal = new JLabel("Canal:");
        JComboBox<String> comboCanal = new 
        JComboBox<>(gestionVentas.getCanales());
        JLabel etiquetaCantidad = new JLabel("Cantidad:");
        JTextField campoCantidad = new JTextField();

        // Button to register sale
        JButton botonRegistrar = new JButton("Registrar Venta");
        botonRegistrar.setBackground(new Color(200, 180, 255));
        botonRegistrar.setForeground(Color.WHITE);
        botonRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        botonRegistrar.setBorder(
                BorderFactory.createLineBorder
        (new Color(120, 0, 150)));

        botonRegistrar.addActionListener(e -> {
            try {
                int producto = comboProducto.getSelectedIndex();
                int canal = comboCanal.getSelectedIndex();
                int cantidad = Integer.parseInt(campoCantidad.getText());
                gestionVentas.registrarVenta(producto, canal, cantidad);
                String nombreProducto = gestionVentas.getProductos()[producto];
                String nombreCanal = gestionVentas.getCanales()[canal];
                gestorArchivo.guardarVenta
        (nombreProducto, nombreCanal, cantidad);
                JOptionPane.showMessageDialog
        (null, "Venta registrada correctamente.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        null, "Ingrese una cantidad válida.");
            }
        });

        // Add components to data input panel
        panelEntrada.add(etiquetaProducto);
        panelEntrada.add(comboProducto);
        panelEntrada.add(etiquetaCanal);
        panelEntrada.add(comboCanal);
        panelEntrada.add(etiquetaCantidad);
        panelEntrada.add(campoCantidad);
        panelEntrada.add(botonRegistrar);

        // Add data input panel to main panel
        panelPrincipal.add(panelEntrada, BorderLayout.CENTER);
        add(panelPrincipal);

        // Make window visible
        setVisible(true);
    }

    /**
     * Main method to launch the application.
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(InterfazRegistroVentas::new);
    }
}
