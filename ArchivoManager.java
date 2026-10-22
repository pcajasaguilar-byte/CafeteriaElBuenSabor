package cafeteria;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ArchivoManager {
    private final String NOMBRE_ARCHIVO = "productos.txt";

    public void guardarProductos(ArrayList<Producto> listaProductos) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(NOMBRE_ARCHIVO))) {
            for (Producto p : listaProductos) {
                pw.println(p.aTextoPlano());
            }
        } catch (Exception e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public ArrayList<Producto> cargarProductos() {
        ArrayList<Producto> lista = new ArrayList<>();
        File archivo = new File(NOMBRE_ARCHIVO);
        
        if (!archivo.exists()) {
            return lista;
        }

        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                Producto p = Producto.desdeTextoPlano(linea);
                if (p != null) {
                    lista.add(p);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        
        return lista;
    }
}