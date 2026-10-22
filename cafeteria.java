package cafeteria;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class cafeteria extends JFrame {
    private JPanel contentPane;
    private JTextField txtCodigo, txtNombre, txtPrecio, txtStock;
    private JTable tableProductos;
    private DefaultTableModel modeloTabla;
    
    private ArrayList<Producto> listaProductos;
    private ArchivoManager archivoManager;

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    cafeteria frame = new cafeteria();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public cafeteria() {
        archivoManager = new ArchivoManager();
        listaProductos = archivoManager.cargarProductos();

        setTitle("Cafetería El Buen Sabor - Gestión de Productos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 720, 430);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(20, 20, 80, 25);
        contentPane.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(90, 20, 150, 25);
        contentPane.add(txtCodigo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 60, 80, 25);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(90, 60, 150, 25);
        contentPane.add(txtNombre);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(20, 100, 80, 25);
        contentPane.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(90, 100, 150, 25);
        contentPane.add(txtPrecio);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(20, 140, 80, 25);
        contentPane.add(lblStock);

        txtStock = new JTextField();
        txtStock.setBounds(90, 140, 150, 25);
        contentPane.add(txtStock);

        JButton btnRegistrar = new JButton("Registrar / Guardar");
        btnRegistrar.setBounds(20, 190, 220, 30);
        contentPane.add(btnRegistrar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(260, 20, 420, 340);
        contentPane.add(scrollPane);

        tableProductos = new JTable();
        modeloTabla = new DefaultTableModel(new Object[]{"Código", "Nombre", "Precio", "Stock"}, 0);
        tableProductos.setModel(modeloTabla);
        scrollPane.setViewportView(tableProductos);

        actualizarTabla();

        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String codigo = txtCodigo.getText();
                    String nombre = txtNombre.getText();
                    double precio = Double.parseDouble(txtPrecio.getText());
                    int stock = Integer.parseInt(txtStock.getText());

                    Producto nuevo = new Producto(codigo, nombre, precio, stock);
                    listaProductos.add(nuevo);

                    archivoManager.guardarProductos(listaProductos);

                    actualizarTabla();
                    limpiarCampos();
                    JOptionPane.showMessageDialog(null, "¡Producto guardado exitosamente!");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese valores numéricos válidos en Precio y Stock.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (Producto p : listaProductos) {
            modeloTabla.addRow(new Object[]{p.getCodigo(), p.getNombre(), p.getPrecio(), p.getStock()});
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
    }
}