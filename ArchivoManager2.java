package cafeteria;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArchivoManager {
    private final String ARCHIVO_PRODUCTOS = "productos.txt";
    private final String ARCHIVO_PEDIDOS = "pedidos.txt";

    public void guardarProductos(List<Producto> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_PRODUCTOS))) {
            for (Producto p : lista) {
                pw.println(p.aTextoPlano());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Producto> cargarProductos() {
        List<Producto> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO_PRODUCTOS);
        if (!archivo.exists()) return lista;
        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                if (!linea.trim().isEmpty()) {
                    UtilidadesColecciones.agregarVarios(lista, Producto.desdeTextoPlano(linea));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void guardarPedidos(List<Pedido> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_PEDIDOS))) {
            for (Pedido ped : lista) {
                pw.println(ped.getIdPedido() + "," + ped.getCliente() + "," + ped.getTotal());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Pedido> cargarPedidos() {
        List<Pedido> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO_PEDIDOS);
        if (!archivo.exists()) return lista;
        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                if (!linea.trim().isEmpty()) {
                    String[] p = linea.split(",");
                    UtilidadesColecciones.agregarVarios(lista, new Pedido(p[0], p[1]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}