package cafeteria;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CafeteriaDelegateView extends JFrame {
    private CafeteriaModel modelo;
    private JFrame ventanaPrincipal;
    private JTable tablaProductos;
    private DefaultTableModel tableModel;
    private JTextField txtCodigo, txtNombre, txtPrecio, txtStock;

    public CafeteriaDelegateView() {
    	setFont(new Font("Dialog", Font.BOLD, 12));
        this.modelo = new CafeteriaModel();
        this.ventanaPrincipal = this;
        inicializarComponentes();
        gestionarEventosBotones();
        actualizarTablas();
    }

    public void inicializarComponentes() {
        setTitle("Sistema de Gestión de Pedidos - Cafetería v2.0");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        String[] columnas = {"Código", "Nombre", "Precio", "Stock"};
        tableModel = new DefaultTableModel(columnas, 0);
        tablaProductos = new JTable(tableModel);
        getContentPane().add(new JScrollPane(tablaProductos), BorderLayout.CENTER);

        JPanel panelForm = new JPanel(new GridLayout(5, 2, 5, 5));
        txtCodigo = new JTextField();
        txtNombre = new JTextField();
        txtPrecio = new JTextField();
        txtStock = new JTextField();
        JButton btnRegistrar = new JButton("Registrar / Reponer Producto");

        panelForm.add(new JLabel(" Código:")); panelForm.add(txtCodigo);
        panelForm.add(new JLabel(" Nombre:")); panelForm.add(txtNombre);
        panelForm.add(new JLabel(" Precio:")); panelForm.add(txtPrecio);
        panelForm.add(new JLabel(" Stock:")); panelForm.add(txtStock);
        panelForm.add(new JLabel("")); panelForm.add(btnRegistrar);

        getContentPane().add(panelForm, BorderLayout.SOUTH);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String cod = txtCodigo.getText().trim();
                    String nom = txtNombre.getText().trim();
                    double pre = Double.parseDouble(txtPrecio.getText());
                    int sto = Integer.parseInt(txtStock.getText());

                    if (cod.isEmpty() || nom.isEmpty()) {
                        mostrarMensaje("Complete todos los campos.");
                        return;
                    }

                    boolean existe = false;
                    for (Producto p : modelo.getInventario()) {
                        if (p.getCodigo().equalsIgnoreCase(cod)) {
                            modelo.actualizarStock(cod, sto);
                            existe = true;
                            break;
                        }
                    }
                    if (!existe) {
                        modelo.agregarProducto(new Producto(cod, nom, pre, sto));
                    }

                    actualizarTablas();
                    mostrarMensaje("¡Operación realizada con éxito!");
                    txtCodigo.setText(""); txtNombre.setText(""); txtPrecio.setText(""); txtStock.setText("");
                } catch (Exception ex) {
                    mostrarMensaje("Error en los datos ingresados: " + ex.getMessage());
                }
            }
        });
    }

    public void gestionarEventosBotones() {
    }

    public void actualizarTablas() {
        tableModel.setRowCount(0);
        for (Producto p : modelo.getInventario()) {
            tableModel.addRow(new Object[]{p.getCodigo(), p.getNombre(), p.getPrecio(), p.getStock()});
        }
    }

    public void mostrarMensaje(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CafeteriaDelegateView().setVisible(true);
        });
    }
}