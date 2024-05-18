import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Proveedores {
    private JTextField nombreField;
    private JTextField cuitCuilField;
    private JTextField telefonoField;
    private JTextField domicilioField;
    private JPanel panel4;
    private JTextField mailField;
    private JTable tablaProveedor;
    private JButton addButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JButton facturarButton;
    private JButton stockButton;
    private JButton clientesButton;
    private JScrollPane scrollPane;
    private JTextField idProveedorField;

    private DefaultTableModel tableModel;

    public Proveedores() {
        initComponents();
    }

    private void initComponents() {
        // Inicialización del modelo de la tabla
        tableModel = new DefaultTableModel();
        String[] columnas = {"ID", "NOMBRE", "CUIT O CUIL", "TELEFONO", "DOMICILIO", "MAIL"};
        tableModel.setColumnIdentifiers(columnas);
        tablaProveedor.setModel(tableModel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProveedor();
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarProveedor();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProveedor();
            }
        });

        tablaProveedor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mostrarProveedorSeleccionado();
            }
        });

        stockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción para abrir ventana de stock
                Stock ee = new Stock();
                ee.setVisible(true);
                JFrame proveedoresFrame = (JFrame) SwingUtilities.getRoot(panel4);
                proveedoresFrame.dispose();
            }
        });
        facturarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción para abrir ventana de facturación
                Facturar ff = new Facturar();
                ff.setVisible(true);
                JFrame proveedoresFrame = (JFrame) SwingUtilities.getRoot(panel4);
                proveedoresFrame.dispose();
            }
        });
        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción para abrir ventana de clientes
                Clientes cc = new Clientes();
                cc.setVisible(true);
                JFrame proveedoresFrame = (JFrame) SwingUtilities.getRoot(panel4);
                proveedoresFrame.dispose();
            }
        });
    }

    private void agregarProveedor() {
        String nombre = nombreField.getText();
        int cuitCuil = Integer.parseInt(cuitCuilField.getText());
        int telefono = Integer.parseInt(telefonoField.getText());
        String domicilio = domicilioField.getText();
        String mail = mailField.getText();

        Object[] nuevaFila = {tableModel.getRowCount() + 1, nombre, cuitCuil, telefono, domicilio, mail};
        tableModel.addRow(nuevaFila);

        JOptionPane.showMessageDialog(null, "Proveedor Guardado", "Completado", JOptionPane.INFORMATION_MESSAGE);
        limpiarCampos();
    }

    private void modificarProveedor() {
        int filaSeleccionada = tablaProveedor.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String nombre = nombreField.getText();
            int cuitCuil = Integer.parseInt(cuitCuilField.getText());
            int telefono = Integer.parseInt(telefonoField.getText());
            String domicilio = domicilioField.getText();
            String mail = mailField.getText();

            tableModel.setValueAt(nombre, filaSeleccionada, 1);
            tableModel.setValueAt(cuitCuil, filaSeleccionada, 2);
            tableModel.setValueAt(telefono, filaSeleccionada, 3);
            tableModel.setValueAt(domicilio, filaSeleccionada, 4);
            tableModel.setValueAt(mail, filaSeleccionada, 5);

            JOptionPane.showMessageDialog(null, "Proveedor Modificado", "Completado", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un proveedor para modificar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarProveedor() {
        int filaSeleccionada = tablaProveedor.getSelectedRow();
        if (filaSeleccionada >= 0) {
            tableModel.removeRow(filaSeleccionada);
            JOptionPane.showMessageDialog(null, "Proveedor Eliminado", "Completado", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un proveedor para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarProveedorSeleccionado() {
        int filaSeleccionada = tablaProveedor.getSelectedRow();
        idProveedorField.setText(tablaProveedor.getValueAt(filaSeleccionada, 0).toString());
        nombreField.setText(tablaProveedor.getValueAt(filaSeleccionada, 1).toString());
        cuitCuilField.setText(tablaProveedor.getValueAt(filaSeleccionada, 2).toString());
        telefonoField.setText(tablaProveedor.getValueAt(filaSeleccionada, 3).toString());
        domicilioField.setText(tablaProveedor.getValueAt(filaSeleccionada, 4).toString());
        mailField.setText(tablaProveedor.getValueAt(filaSeleccionada, 5).toString());
    }

    private void limpiarCampos() {
        idProveedorField.setText("");
        nombreField.setText("");
        cuitCuilField.setText("");
        telefonoField.setText("");
        domicilioField.setText("");
        mailField.setText("");
    }

    public JPanel getPanel4() {
        return panel4;
    }

    public void setPanel4(JPanel panel4) {
        this.panel4 = panel4;
    }

    public JTable getTablaProveedor() {
        return tablaProveedor;
    }

    public void setTablaProveedor(JTable tablaProveedor) {
        this.tablaProveedor = tablaProveedor;
    }

    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("Proveedores");
        frame.setContentPane(this.getPanel4());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public String toString() {
        return "Proveedores{" +
                "nombre='" + nombreField.getText() + '\'' +
                ", cuitCuil=" + cuitCuilField.getText() +
                ", telefono=" + telefonoField.getText() +
                ", domicilio='" + domicilioField.getText() + '\'' +
                ", mail='" + mailField.getText() + '\'' +
                '}';
    }
}
