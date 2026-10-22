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

    public String aTextoPlano() {
        return codigo + "," + nombre + "," + precio + "," + stock;
    }

    public static Producto desdeTextoPlano(String linea) {
        String[] partes = linea.split(",");
        if (partes.length == 4) {
            return new Producto(partes[0], partes[1], Double.parseDouble(partes[2]), Integer.parseInt(partes[3]));
        }
        return null;
    }
}