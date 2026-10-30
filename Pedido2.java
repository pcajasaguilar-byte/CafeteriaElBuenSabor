package cafeteria;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String idPedido;
    private String cliente;
    private List<DetallePedido> detalles;
    private double impuestos;
    private double total;

    public Pedido(String idPedido, String cliente) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.detalles = new ArrayList<>();
        this.impuestos = 0.0;
        this.total = 0.0;
    }

    public void agregarDetalle(DetallePedido detalle) {
        detalles.add(detalle);
        calcularTotales();
    }

    public void calcularTotales() {
        double subtotalGeneral = 0;
        for (DetallePedido d : detalles) {
            subtotalGeneral += d.getSubtotal();
        }
        this.impuestos = subtotalGeneral * 0.15;
        this.total = subtotalGeneral + this.impuestos;
    }

    public String getIdPedido() { return idPedido; }
    public String getCliente() { return cliente; }
    public List<DetallePedido> getDetalles() { return detalles; }
    public double getImpuestos() { return impuestos; }
    public double getTotal() { return total; }
}