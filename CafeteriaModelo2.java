package cafeteria;

import java.util.List;

public class CafeteriaModel {
    private List<Producto> inventario;
    private List<Pedido> listaPedidos;
    private ArchivoManager archivoManager;

    public CafeteriaModel() {
        this.archivoManager = new ArchivoManager();
        this.inventario = archivoManager.cargarProductos();
        this.listaPedidos = archivoManager.cargarPedidos();

        if (this.inventario.isEmpty()) {
            this.inventario = UtilidadesColecciones.crearColeccionGenerica(
                new Producto("J01", "Jugo de Naranja", 1.25, 40),
                new Producto("C01", "Café Americano", 1.00, 50)
            );
            archivoManager.guardarProductos(this.inventario);
        }
    }

    public void agregarProducto(Producto p) {
        inventario.add(p);
        archivoManager.guardarProductos(inventario);
    }

    public void actualizarStock(String codigo, int cantidad) {
        for (Producto p : inventario) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                p.reponerStock(cantidad);
                break;
            }
        }
        archivoManager.guardarProductos(inventario);
    }

    public boolean verificarStock(String codigo, int cantidad) {
        List<Producto> encontrados = UtilidadesColecciones.filtrarElementos(
            inventario, p -> p.getCodigo().equalsIgnoreCase(codigo) && p.getStock() >= cantidad
        );
        return !encontrados.isEmpty();
    }

    public boolean registrarPedido(Pedido p) {
        for (DetallePedido detalle : p.getDetalles()) {
            Producto prod = detalle.getProducto();
            if (prod.getStock() < detalle.getCantidad()) {
                return false;
            }
        }
        for (DetallePedido detalle : p.getDetalles()) {
            detalle.getProducto().descontarStock(detalle.getCantidad());
        }
        listaPedidos.add(p);
        archivoManager.guardarProductos(inventario);
        archivoManager.guardarPedidos(listaPedidos);
        return true;
    }

    public List<Producto> getInventario() { return inventario; }
    public List<Pedido> getListaPedidos() { return listaPedidos; }
}
