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
import java.io.*;

public class VentanaPersistencia extends JFrame {
    private SistemaVentas gestionVentas;

    public VentanaPersistencia(SistemaVentas gestionVentas) {
        this.gestionVentas = gestionVentas;

        setTitle("Persistencia de Datos");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new FlowLayout());

        // Botón para guardar datos
        JButton botonGuardar = new JButton("Guardar Datos");
        botonGuardar.addActionListener(e -> guardarDatos());

        // Botón para cargar datos
        JButton botonCargar = new JButton("Cargar Datos");
        botonCargar.addActionListener(e -> cargarDatos());

        add(botonGuardar);
        add(botonCargar);

        setVisible(true);
    }

    private void guardarDatos() {
        try (BufferedWriter writer = new BufferedWriter
        (new FileWriter("ventas.txt"))) {
            int[][] ventas = gestionVentas.obtenerVentas();
            for (int[] producto : ventas) {
                for (int venta : producto) {
                    writer.write(venta + " ");
                }
                writer.newLine();
            }
            JOptionPane.showMessageDialog
        (this, "Datos guardados correctamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog
        (this, "Error al guardar los datos.");
        }
    }

    private void cargarDatos() {
        try (BufferedReader reader = new BufferedReader
        (new FileReader("ventas.txt"))) {
            int[][] ventas = gestionVentas.obtenerVentas();
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] valores = line.split(" ");
                for (int col = 0; col < valores.length; col++) {
                    ventas[row][col] = Integer.parseInt(valores[col]);
                }
                row++;
            }
            JOptionPane.showMessageDialog
        (this, "Datos cargados correctamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog
        (this, "Error al cargar los datos.");
        }
    }

    public static void main(String[] args) {
        SistemaVentas gestion = new SistemaVentas(
            new String[]{"Producto A", "Producto B", "Producto C"},
            new String[]{"Tienda Física", "En Línea"}
        );
        new VentanaPersistencia(gestion);
    }
}

