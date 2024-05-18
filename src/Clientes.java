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

public class Clientes {
    private JPanel panel3;
    private JTextField nombreField;
    private JTextField cuitCuilField;
    private JTextField telefonoField;
    private JTextField domicilioField;
    private JTextField mailField;
    private JButton addButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JTable tablaClientes;
    private JScrollPane scrollPane;
    private JTextField idField;
    private JTextField apellidoField;
    private JButton facturarButton;
    private JButton stockButton;
    private JButton proveedorButton;

    private DefaultTableModel tableModel;

    public Clientes() {
        initComponents();
    }

    private void initComponents() {
        // Inicialización del modelo de la tabla
        tableModel = new DefaultTableModel();
        String[] columnas = {"ID", "NOMBRE", "APELLIDO", "CUIT/CUIL", "TELÉFONO", "DOMICILIO", "MAIL"};
        tableModel.setColumnIdentifiers(columnas);
        tablaClientes.setModel(tableModel);

        stockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción para abrir ventana de stock
                Stock ee = new Stock();
                ee.setVisible(true);
                JFrame clientesFrame = (JFrame) SwingUtilities.getRoot(panel3);
                clientesFrame.dispose();
            }
        });
        proveedorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción para abrir ventana de proveedores
                Proveedores dd = new Proveedores();
                dd.setVisible(true);
                JFrame clientesFrame = (JFrame) SwingUtilities.getRoot(panel3);
                clientesFrame.dispose();
            }
        });

        facturarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción para abrir ventana de facturación
                Facturar ff = new Facturar();
                ff.setVisible(true);
                JFrame clientesFrame = (JFrame) SwingUtilities.getRoot(panel3);
                clientesFrame.dispose();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCliente();
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarCliente();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCliente();
            }
        });

        tablaClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mostrarClienteSeleccionado();
            }
        });
    }




    private void agregarCliente() {
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String cuitCuil = cuitCuilField.getText();
        String telefono = telefonoField.getText();
        String domicilio = domicilioField.getText();
        String mail = mailField.getText();

        Object[] nuevaFila = {tableModel.getRowCount() + 1, nombre, apellido, cuitCuil, telefono, domicilio, mail};
        tableModel.addRow(nuevaFila);

        JOptionPane.showMessageDialog(null, "Cliente Guardado", "Completado", JOptionPane.INFORMATION_MESSAGE);
        limpiarCampos();
    }

    private void modificarCliente() {
        int filaSeleccionada = tablaClientes.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();
            String cuitCuil = cuitCuilField.getText();
            String telefono = telefonoField.getText();
            String domicilio = domicilioField.getText();
            String mail = mailField.getText();

            tableModel.setValueAt(nombre, filaSeleccionada, 1);
            tableModel.setValueAt(apellido, filaSeleccionada, 2);
            tableModel.setValueAt(cuitCuil, filaSeleccionada, 3);
            tableModel.setValueAt(telefono, filaSeleccionada, 4);
            tableModel.setValueAt(domicilio, filaSeleccionada, 5);
            tableModel.setValueAt(mail, filaSeleccionada, 6);

            JOptionPane.showMessageDialog(null, "Cliente Modificado", "Completado", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un cliente para modificar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarCliente() {
        int filaSeleccionada = tablaClientes.getSelectedRow();
        if (filaSeleccionada >= 0) {
            tableModel.removeRow(filaSeleccionada);
            JOptionPane.showMessageDialog(null, "Cliente Eliminado", "Completado", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un cliente para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarClienteSeleccionado() {
        int filaSeleccionada = tablaClientes.getSelectedRow();
        idField.setText(tablaClientes.getValueAt(filaSeleccionada, 0).toString());
        nombreField.setText(tablaClientes.getValueAt(filaSeleccionada, 1).toString());
        apellidoField.setText(tablaClientes.getValueAt(filaSeleccionada, 2).toString());
        cuitCuilField.setText(tablaClientes.getValueAt(filaSeleccionada, 3).toString());
        telefonoField.setText(tablaClientes.getValueAt(filaSeleccionada, 4).toString());
        domicilioField.setText(tablaClientes.getValueAt(filaSeleccionada, 5).toString());
        mailField.setText(tablaClientes.getValueAt(filaSeleccionada, 6).toString());
    }

    private void limpiarCampos() {
        idField.setText("");
        nombreField.setText("");
        apellidoField.setText("");
        cuitCuilField.setText("");
        telefonoField.setText("");
        domicilioField.setText("");
        mailField.setText("");
    }

    public JPanel getPanel3() {
        return panel3;
    }

    public void setPanel3(JPanel panel3) {
        this.panel3 = panel3;
    }

    public JTable getTablaClientes() {
        return tablaClientes;
    }

    public void setTablaClientes(JTable tablaClientes) {
        this.tablaClientes = tablaClientes;
    }

    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("Clientes");
        frame.setContentPane(this.getPanel3());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public String toString() {
        return "Clientes{" +
                "nombre='" + nombreField.getText() + '\'' +
                ", apellido='" + apellidoField.getText() + '\'' +
                ", cuitCuil='" + cuitCuilField.getText() + '\'' +
                ", telefono='" + telefonoField.getText() + '\'' +
                ", domicilio='" + domicilioField.getText() + '\'' +
                ", mail='" + mailField.getText() + '\'' +
                '}';
    }
}
