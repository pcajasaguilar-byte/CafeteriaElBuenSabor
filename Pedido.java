package cafeteria;

import java.util.ArrayList;

public class Pedido {
    private int idPedido;
    private String cliente;
    private double total;
    private ArrayList<DetallePedido> detalles;

    public Pedido(int idPedido, String cliente) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.detalles = new ArrayList<>();
        this.total = 0.0;
    }

    public void agregarDetalle(Producto producto, int cantidad) {
        DetallePedido detalle = new DetallePedido(producto, cantidad);
        detalles.add(detalle);
        calcularTotal();
    }

    public double calcularTotal() {
        total = 0.0;
        for (DetallePedido d : detalles) {
            total += d.getSubtotal();
        }
        return total;
    }

    public int getIdPedido() { return idPedido; }
    public String getCliente() { return cliente; }
    public double getTotal() { return total; }
    public ArrayList<DetallePedido> getDetalles() { return detalles; }
}