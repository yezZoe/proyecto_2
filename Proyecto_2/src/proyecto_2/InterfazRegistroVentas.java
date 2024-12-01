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

public class InterfazRegistroVentas extends JFrame {
    private SistemaVentas gestionVentas; // Objeto para manejar el sistema de ventas
    private GestorArchivo gestorArchivo; // Objeto para gestionar la persistencia en archivo

    /**
     * Constructor para crear la interfaz gráfica.
     */
    public InterfazRegistroVentas() {
        // Inicialización del sistema de ventas con productos y canales predefinidos
        gestionVentas = new SistemaVentas(
            new String[]{"Producto A", "Producto B", "Producto C"},
            new String[]{"Tienda Física", "En Línea"}
        );

        // Inicialización del gestor de archivos
        gestorArchivo = new GestorArchivo();

        // Configuración de la ventana principal
        setTitle("N&Y Essence");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Establecer un diseño más profesional
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(230, 220, 255)); // Fondo lila suave
        panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10)); // Márgenes

        // Crear barra de menú estilizada
        JMenuBar barraMenu = new JMenuBar();
        barraMenu.setBackground(new Color(200, 180, 255)); // Fondo lila más oscuro
        barraMenu.setBorderPainted(false);
        barraMenu.setOpaque(true);

        JMenu menuOpciones = new JMenu("Menú");
        menuOpciones.setFont(new Font("Arial", Font.BOLD, 14));
        menuOpciones.setForeground(new Color(80, 0, 120)); // Texto púrpura oscuro

        JMenuItem itemVerReportes = new JMenuItem("Ver Reportes");
        JMenuItem itemVerReportesAvanzados = new JMenuItem("Ver Reportes Avanzados");
        JMenuItem itemVerGraficoVentas = new JMenuItem("Ver Gráfico de Ventas");
        JMenuItem itemCargarVentas = new JMenuItem("Cargar Ventas");
        JMenuItem itemSalir = new JMenuItem("Salir");

        // Configuración de estilo para elementos del menú
        for (JMenuItem item : new JMenuItem[]{itemVerReportes, itemVerReportesAvanzados, itemVerGraficoVentas, itemCargarVentas, itemSalir}) {
            item.setFont(new Font("Arial", Font.PLAIN, 13));
            item.setBackground(new Color(245, 240, 255));
            item.setForeground(new Color(60, 0, 90));
        }

        // Añadir acciones
        itemVerReportes.addActionListener(e -> new InterfazReportes(gestionVentas));
        itemVerReportesAvanzados.addActionListener(e -> new VentanaReportesAvanzados(gestionVentas));
        itemVerGraficoVentas.addActionListener(e -> {
            int[][] ventas = gestionVentas.obtenerVentas();
            String[] productos = gestionVentas.getProductos();
            String[] canales = gestionVentas.getCanales();
            JFrame ventanaGrafico = new JFrame("Gráfico de Ventas");
            ventanaGrafico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ventanaGrafico.setSize(800, 600);
            GraficoBarras grafico = new GraficoBarras(ventas, productos, canales);
            ventanaGrafico.add(grafico);
            ventanaGrafico.setVisible(true);
        });
        itemCargarVentas.addActionListener(e -> {
            List<String> ventas = gestorArchivo.cargarVentas();
            StringBuilder mensaje = new StringBuilder("Ventas registradas:\n");
            for (String venta : ventas) {
                mensaje.append(venta).append("\n");
            }
            JOptionPane.showMessageDialog(null, mensaje.toString());
        });
        itemSalir.addActionListener(e -> System.exit(0));

        menuOpciones.add(itemVerReportes);
        menuOpciones.add(itemVerGraficoVentas);
        menuOpciones.add(itemCargarVentas);
        menuOpciones.add(itemVerReportesAvanzados);
        menuOpciones.add(itemSalir);
        barraMenu.add(menuOpciones);

        // Asociar la barra de menú con la ventana
        setJMenuBar(barraMenu);

        // Panel de entrada de datos
        JPanel panelEntrada = new JPanel(new GridLayout(4, 2, 10, 10));
        panelEntrada.setBackground(new Color(230, 220, 255));

        JLabel etiquetaProducto = new JLabel("Producto:");
        JComboBox<String> comboProducto = new JComboBox<>(gestionVentas.getProductos());
        JLabel etiquetaCanal = new JLabel("Canal:");
        JComboBox<String> comboCanal = new JComboBox<>(gestionVentas.getCanales());
        JLabel etiquetaCantidad = new JLabel("Cantidad:");
        JTextField campoCantidad = new JTextField();

        JButton botonRegistrar = new JButton("Registrar Venta");
        botonRegistrar.setBackground(new Color(200, 180, 255));
        botonRegistrar.setForeground(Color.WHITE);
        botonRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        botonRegistrar.setBorder(BorderFactory.createLineBorder(new Color(120, 0, 150)));

        botonRegistrar.addActionListener(e -> {
            try {
                int producto = comboProducto.getSelectedIndex();
                int canal = comboCanal.getSelectedIndex();
                int cantidad = Integer.parseInt(campoCantidad.getText());
                gestionVentas.registrarVenta(producto, canal, cantidad);
                String nombreProducto = gestionVentas.getProductos()[producto];
                String nombreCanal = gestionVentas.getCanales()[canal];
                gestorArchivo.guardarVenta(nombreProducto, nombreCanal, cantidad);
                JOptionPane.showMessageDialog(null, "Venta registrada correctamente.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida.");
            }
        });

        // Añadir componentes estilizados al panel
        for (JComponent comp : new JComponent[]{etiquetaProducto, etiquetaCanal, etiquetaCantidad}) {
            comp.setFont(new Font("Arial", Font.PLAIN, 14));
            comp.setForeground(new Color(80, 0, 120));
        }

        panelEntrada.add(etiquetaProducto);
        panelEntrada.add(comboProducto);
        panelEntrada.add(etiquetaCanal);
        panelEntrada.add(comboCanal);
        panelEntrada.add(etiquetaCantidad);
        panelEntrada.add(campoCantidad);
        panelEntrada.add(botonRegistrar);

        // Añadir paneles al marco principal
        panelPrincipal.add(panelEntrada, BorderLayout.CENTER);
        add(panelPrincipal);

        // Hacer visible la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InterfazRegistroVentas::new);
    }
}
