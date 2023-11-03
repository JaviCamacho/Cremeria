/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cremeria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Javier Camacho
 */
public class FileLoader {

    private File nombreArchivo;

    public FileLoader(String nombreArchivo) {
        this.nombreArchivo = new File(nombreArchivo);
        if (this.nombreArchivo.exists()) {
            System.out.println("Archivo cargado con exito.");
        } else {
            try {
                System.out.println("No se ha encontrado el archivo");
                this.nombreArchivo.createNewFile();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void escribirRegistros(ArrayList<Producto> productos) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo));

            for (Producto producto : productos) {
                writer.write(producto.getNombre() + "," + producto.getCantidad() + "," + producto.getPrecio());
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Producto> leerRegistros() {
        ArrayList<Producto> productos = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo));
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                String nombre = partes[0];
                int cantidad = Integer.parseInt(partes[1]);
                double precio = Double.parseDouble(partes[2]);

                Producto producto = new Producto(nombre, cantidad, precio);
                productos.add(producto);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productos;
    }
}
