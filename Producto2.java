package cafeteria;

public class Producto {
    private String codigo;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String codigo, String nombre, double precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public void reponerStock(int cantidad) {
        this.stock += cantidad;
    }

    public void descontarStock(int cantidad) {
        this.stock -= cantidad;
    }

    public String aTextoPlano() {
        return codigo + "," + nombre + "," + precio + "," + stock;
    }

    public static Producto desdeTextoPlano(String linea) {
        String[] p = linea.split(",");
        return new Producto(p[0], p[1], Double.parseDouble(p[2]), Integer.parseInt(p[3]));
    }
}