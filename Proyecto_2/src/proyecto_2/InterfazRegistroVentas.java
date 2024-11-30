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
        new InterfazRegistroVentas();
    }
}

