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

public class InterfazRegistroVentas extends JFrame {
    private SistemaVentas gestionVentas;

    /**
     * Constructor para crear la interfaz gráfica.
     * Constructor to create the graphical interface.
     */
    public InterfazRegistroVentas() {
        gestionVentas = new SistemaVentas(
            new String[]{"Producto A", "Producto B", "Producto C"},
            new String[]{"Tienda Física", "En Línea"}
        );

        setTitle("Registro de Ventas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));
      // **Crear la barra de menú**
        // **Crear la barra de menú**
        JMenuBar barraMenu = new JMenuBar();  // Crear barra de menú
        JMenu menuOpciones = new JMenu("Menú");  // Crear un menú llamado "Menú"
        
         // Opción "Ver Reportes" en el menú
         JMenuItem itemVerReportes = new JMenuItem("Ver Reportes");
         itemVerReportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí creamos una nueva instancia de la interfaz de reportes
                new InterfazReportes(gestionVentas);
            }
        });

        // Opción "Ver Reportes Avanzados" en el menú
        JMenuItem itemVerReportesAvanzados = new JMenuItem("Ver Reportes Avanzados");
        itemVerReportesAvanzados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí creamos una nueva instancia de la interfaz de reportes avanzados
                new VentanaReportesAvanzados(gestionVentas);
            }
        });

          // Opción "Ver Gráfico de Ventas" en el menú
        JMenuItem itemVerGraficoVentas = new JMenuItem("Ver Gráfico de Ventas");
        itemVerGraficoVentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear ventana para el gráfico de barras
                int[][] ventas = gestionVentas.obtenerVentas(); // Obtener datos de ventas
                String[] productos = gestionVentas.getProductos();
                String[] canales = gestionVentas.getCanales();

                // Crear una nueva ventana para mostrar el gráfico de barras
                JFrame ventanaGrafico = new JFrame("Gráfico de Ventas");
                ventanaGrafico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ventanaGrafico.setSize(800, 600);
                
                // Instanciamos el gráfico con los datos
                GraficoBarras grafico = new GraficoBarras(ventas, productos, canales);
                ventanaGrafico.add(grafico);
                
                // Mostrar la ventana con el gráfico
                ventanaGrafico.setVisible(true);
            }
        });
        
        // Opción "Salir" en el menú
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cierra la aplicación
                System.exit(0);  // Cierra la aplicación
            }
        });
       
        // Añadir las opciones al menú
        menuOpciones.add(itemVerReportes);  // Añadimos la opción "Ver Reportes"
        menuOpciones.add(itemVerGraficoVentas);  // Añadimos la opción "Ver Gráfico de Ventas"
        menuOpciones.add(itemVerReportesAvanzados);  // Añadimos la opción "Ver Reportes Avanzados"
        menuOpciones.add(itemSalir);  // Añadimos la opción "Salir"
        barraMenu.add(menuOpciones);  // Añadir el menú a la barra de menú
         // **Asociar la barra de menú con la ventana**
        setJMenuBar(barraMenu);  // Asignar la barra de menú a la ventana
        
        // Etiquetas y campos
        JLabel etiquetaProducto = new JLabel("Producto:");
        JComboBox<String> comboProducto = 
                new JComboBox<>(gestionVentas.getProductos());
        JLabel etiquetaCanal = new JLabel("Canal:");
        JComboBox<String> comboCanal = 
                new JComboBox<>(gestionVentas.getCanales());
        JLabel etiquetaCantidad = new JLabel("Cantidad:");
        JTextField campoCantidad = new JTextField();

        // Botón para registrar ventas
        JButton botonRegistrar = new JButton("Registrar Venta");
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int producto = comboProducto.getSelectedIndex();
                    int canal = comboCanal.getSelectedIndex();
                    int cantidad = Integer.parseInt(campoCantidad.getText());

                    gestionVentas.registrarVenta(producto, canal, cantidad);
                    JOptionPane.showMessageDialog
        (null, "Venta registrada correctamente.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog
        (null, "Ingrese una cantidad válida.");
                }
            }
        });

        // Añadir componentes a la ventana
        add(etiquetaProducto);
        add(comboProducto);
        add(etiquetaCanal);
        add(comboCanal);
        add(etiquetaCantidad);
        add(campoCantidad);
        add(botonRegistrar);

        setVisible(true);
    }

    public static void main(String[] args) {
     // Iniciar la interfaz en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazRegistroVentas();  // Crear la interfaz
            }
        });
    }
}
