import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Stock {
    private JTextField nombreField;
    private JTextField precioField;
    private JTextField cantidadField;
    private JButton facturarButton;
    private JButton clientesButton;
    private JButton proveedorButton;
    private JButton addButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JTable tablaStock;
    private JPanel panel2;
    private JScrollPane scrollPane;
    private JTextField idField;

    private DefaultTableModel tableModel;

    public Stock() {
        initComponents();
    }

    private void initComponents() {
        // Inicialización del modelo de la tabla
        tableModel = new DefaultTableModel();
        String[] columnas = {"ID PRODUCTO", "NOMBRE", "PRECIO", "CANTIDAD"};
        tableModel.setColumnIdentifiers(columnas);
        tablaStock.setModel(tableModel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarProducto();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });

        tablaStock.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mostrarProductoSeleccionado();
            }
        });

        facturarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción para abrir ventana de facturación
                Facturar ff = new Facturar();
                ff.setVisible(true);
                JFrame proveedoresFrame = (JFrame) SwingUtilities.getRoot(panel2);
                proveedoresFrame.dispose();
            }
        });
        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción para abrir ventana de clientes
                Clientes cc = new Clientes();
                cc.setVisible(true);
                JFrame proveedoresFrame = (JFrame) SwingUtilities.getRoot(panel2);
                proveedoresFrame.dispose();
            }
        });
        proveedorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción para abrir ventana de proveedores
                Proveedores dd = new Proveedores();
                dd.setVisible(true);
                JFrame clientesFrame = (JFrame) SwingUtilities.getRoot(panel2);
                clientesFrame.dispose();
            }
        });
    }

    private void agregarProducto() {
        String nombre = nombreField.getText();
        int precio = Integer.parseInt(precioField.getText());
        int cantidad = Integer.parseInt(cantidadField.getText());

        Object[] nuevaFila = {tableModel.getRowCount() + 1, nombre, precio, cantidad};
        tableModel.addRow(nuevaFila);

        JOptionPane.showMessageDialog(null, "Producto Guardado", "Completado", JOptionPane.INFORMATION_MESSAGE);
        limpiarCampos();
    }

    private void modificarProducto() {
        int filaSeleccionada = tablaStock.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String nombre = nombreField.getText();
            int precio = Integer.parseInt(precioField.getText());
            int cantidad = Integer.parseInt(cantidadField.getText());

            tableModel.setValueAt(nombre, filaSeleccionada, 1);
            tableModel.setValueAt(precio, filaSeleccionada, 2);
            tableModel.setValueAt(cantidad, filaSeleccionada, 3);

            JOptionPane.showMessageDialog(null, "Producto Modificado", "Completado", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un producto para modificar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarProducto() {
        int filaSeleccionada = tablaStock.getSelectedRow();
        if (filaSeleccionada >= 0) {
            tableModel.removeRow(filaSeleccionada);
            JOptionPane.showMessageDialog(null, "Producto Eliminado", "Completado", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un producto para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarProductoSeleccionado() {
        int filaSeleccionada = tablaStock.getSelectedRow();
        idField.setText(tablaStock.getValueAt(filaSeleccionada, 0).toString());
        nombreField.setText(tablaStock.getValueAt(filaSeleccionada, 1).toString());
        precioField.setText(tablaStock.getValueAt(filaSeleccionada, 2).toString());
        cantidadField.setText(tablaStock.getValueAt(filaSeleccionada, 3).toString());
    }

    private void limpiarCampos() {
        idField.setText("");
        nombreField.setText("");
        precioField.setText("");
        cantidadField.setText("");
    }

    public JPanel getPanel2() {
        return panel2;
    }

    public void setPanel2(JPanel panel2) {
        this.panel2 = panel2;
    }

    public JTable getTablaStock() {
        return tablaStock;
    }

    public void setTablaStock(JTable tablaStock) {
        this.tablaStock = tablaStock;
    }

    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("Stock");
        frame.setContentPane(this.getPanel2());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "nombre='" + nombreField.getText() + '\'' +
                ", precio=" + precioField.getText() +
                ", cantidad=" + cantidadField.getText() +
                '}';
    }
}
