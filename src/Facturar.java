import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Facturar extends Facturar1 {
    private JPanel panel5;
    private JTextField nombre;
    private JTextField cuitCuil;
    private JTextField telefono;
    private JTextField domicilio;
    private JTextField mail;
    private JTextField desc;
    private JTextField precio;
    private JTextField cantidad;
    private JButton clientesButton;
    private JButton stockButton;
    private JButton proveedorButton;
    private JTable tablaFacturar;
    private JButton calcularButton;
    private JTextField descuento;
    private JButton addButton;
    private JButton eliminarButton;
    private JLabel subTotalLabel;
    private JLabel totalLabel;
    private JTextField idCliente;
    private JButton facturarButton;
    private JScrollPane scrollPane;
    private JComboBox<String> medioPagoComboBox;
    private JLabel medioPagoLabel;
    private JLabel horaLabel;
    private JLabel fechaLabel;
    private JTextField idProducto;
    private JTextField apellido;
    private int userId;
    private Facturar1 facturar1Instance;
    private DefaultTableModel tableModel;

    public Facturar() {
        facturar1Instance = new Facturar1();
        tableModel = new DefaultTableModel();
        initListeners();
        rellenarTabla();
        fechayhora();
    }

    private void initListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });

        stockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirStock();
            }
        });

        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirClientes();
            }
        });

        proveedorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirProveedores();
            }
        });

        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularDescuento();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });

        facturarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                facturar();
            }
        });
    }

    private void rellenarTabla() {
        tableModel.addColumn("ID CLIENTE");
        tableModel.addColumn("PRODUCTO");
        tableModel.addColumn("PRECIO");
        tableModel.addColumn("CANTIDAD");
        tableModel.addColumn("FECHA");
        tableModel.addColumn("HORA");
        tableModel.addColumn("MEDIO PAGO");

        scrollPane.setViewportView(tablaFacturar);
        tablaFacturar.setModel(tableModel);
    }

    private void fechayhora() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String fechaActual = dateFormat.format(new Date());
        fechaLabel.setText(fechaActual);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        Runnable runnable = () -> {
            while (true) {
                try {
                    Thread.sleep(500);
                    horaLabel.setText(formatter.format(LocalDateTime.now()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void agregarProducto() {
        String idClienteValue = idCliente.getText();
        String descValue = desc.getText();
        String precioValue = precio.getText();
        String cantidadValue = cantidad.getText();
        String fechaValue = fechaLabel.getText();
        String horaValue = horaLabel.getText();

        if (idClienteValue.isEmpty() || descValue.isEmpty() || precioValue.isEmpty() ||
                cantidadValue.isEmpty() || fechaValue.isEmpty() || horaValue.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar completos", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            Object[] linea = new Object[7];
            linea[0] = idClienteValue;
            linea[1] = descValue;
            linea[2] = precioValue;
            linea[3] = cantidadValue;
            linea[4] = fechaValue;
            linea[5] = horaValue;
            linea[6] = medioPagoComboBox.getSelectedItem();

            tableModel.addRow(linea);
            tablaFacturar.setModel(tableModel);

            float n2 = Float.parseFloat(precioValue);
            float n3 = Float.parseFloat(cantidadValue);
            facturar1Instance.ingresarN2(n3);
            facturar1Instance.ingresarN3(n2);

            String subtotal = String.valueOf(facturar1Instance.calcularSub());
            subTotalLabel.setText(subtotal);
            totalLabel.setText("0");
        }
    }

    private void abrirStock() {
        Stock stock = new Stock();
        stock.setVisible(true);
        JFrame facturarFrame = (JFrame) SwingUtilities.getRoot(panel5);
        facturarFrame.dispose();
    }

    private void abrirClientes() {
        Clientes clientes = new Clientes();
        clientes.setVisible(true);
        JFrame facturarFrame = (JFrame) SwingUtilities.getRoot(panel5);
        facturarFrame.dispose();
    }

    private void abrirProveedores() {
        Proveedores proveedores = new Proveedores();
        proveedores.setVisible(true);
        JFrame facturarFrame = (JFrame) SwingUtilities.getRoot(panel5);
        facturarFrame.dispose();
    }

    private void calcularDescuento() {
        String descuentoValue = descuento.getText();
        if (descuentoValue.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese un valor de descuento", "Atención", JOptionPane.WARNING_MESSAGE);
        } else {
            float descuentoFloat = Float.parseFloat(descuentoValue);
            facturar1Instance.ingresarN1(descuentoFloat);
            String totalConDescuento = String.valueOf(facturar1Instance.calcularConDescuento());
            totalLabel.setText(totalConDescuento);
        }
    }

    private void eliminarProducto() {
        int fila = tablaFacturar.getSelectedRow();
        if (fila >= 0) {
            float precio = Float.parseFloat(tablaFacturar.getValueAt(fila, 2).toString());
            float cantidad = Float.parseFloat(tablaFacturar.getValueAt(fila, 3).toString());
            tableModel.removeRow(fila);

            float valor = precio * cantidad;
            facturar1Instance.ingresarN3(valor);

            String descuentoValue = descuento.getText();
            float descuentoFloat = Float.parseFloat(descuentoValue);

            if (descuentoFloat > 0) {
                String totalDescuento = String.valueOf(facturar1Instance.restarTotalDescuento());
                totalLabel.setText(totalDescuento);
            } else {
                String total = String.valueOf(facturar1Instance.restarTotal());
                totalLabel.setText(total);
            }

            String subTotal = String.valueOf(facturar1Instance.restarSub());
            subTotalLabel.setText(subTotal);
            String total = String.valueOf(facturar1Instance.resetTotal());
            totalLabel.setText(total);

            descuento.setText("0");
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void facturar() {
        if (tablaFacturar.getRowCount() > 0) {
            while (tableModel.getRowCount() > 0) {
                tableModel.removeRow(0);
            }
            limpiarCampos();
            facturar1Instance.resetTotal();
            facturar1Instance.resetSubtotal();
            JOptionPane.showMessageDialog(null, "Factura Guardada", "Completado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No hay productos en la factura", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para limpiar los campos
    private void limpiarCampos() {
        subTotalLabel.setText("");
        totalLabel.setText("");
        descuento.setText("");
    }

    // Getters y Setters

    public JPanel getPanel5() {
        return panel5;
    }

    public void setPanel5(JPanel panel5) {
        this.panel5 = panel5;
    }

    public JTextField getNombre() {
        return nombre;
    }

    public void setNombre(JTextField nombre) {
        this.nombre = nombre;
    }

    public JTextField getCuitCuil() {
        return cuitCuil;
    }

    public void setCuitCuil(JTextField cuitCuil) {
        this.cuitCuil = cuitCuil;
    }

    public JTextField getTelefono() {
        return telefono;
    }

    public void setTelefono(JTextField telefono) {
        this.telefono = telefono;
    }

    public JTextField getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(JTextField domicilio) {
        this.domicilio = domicilio;
    }

    public JTextField getMail() {
        return mail;
    }

    public void setMail(JTextField mail) {
        this.mail = mail;
    }

    public JTextField getDesc() {
        return desc;
    }

    public void setDesc(JTextField desc) {
        this.desc = desc;
    }

    public JTextField getPrecio() {
        return precio;
    }

    public void setPrecio(JTextField precio) {
        this.precio = precio;
    }

    public JTextField getCantidad() {
        return cantidad;
    }

    public void setCantidad(JTextField cantidad) {
        this.cantidad = cantidad;
    }

    public JButton getClientesButton() {
        return clientesButton;
    }

    public void setClientesButton(JButton clientesButton) {
        this.clientesButton = clientesButton;
    }

    public JButton getStockButton() {
        return stockButton;
    }

    public void setStockButton(JButton stockButton) {
        this.stockButton = stockButton;
    }

    public JButton getProveedorButton() {
        return proveedorButton;
    }

    public void setProveedorButton(JButton proveedorButton) {
        this.proveedorButton = proveedorButton;
    }

    public JTable getTablaFacturar() {
        return tablaFacturar;
    }

    public void setTablaFacturar(JTable tablaFacturar) {
        this.tablaFacturar = tablaFacturar;
    }

    public JButton getCalcularButton() {
        return calcularButton;
    }

    public void setCalcularButton(JButton calcularButton) {
        this.calcularButton = calcularButton;
    }

    public JTextField getDescuento() {
        return descuento;
    }

    public void setDescuento(JTextField descuento) {
        this.descuento = descuento;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public JButton getEliminarButton() {
        return eliminarButton;
    }

    public void setEliminarButton(JButton eliminarButton) {
        this.eliminarButton = eliminarButton;
    }

    public JLabel getSubTotalLabel() {
        return subTotalLabel;
    }

    public void setSubTotalLabel(JLabel subTotalLabel) {
        this.subTotalLabel = subTotalLabel;
    }

    public JLabel getTotalLabel() {
        return totalLabel;
    }

    public void setTotalLabel(JLabel totalLabel) {
        this.totalLabel = totalLabel;
    }

    public JTextField getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(JTextField idCliente) {
        this.idCliente = idCliente;
    }

    public JButton getFacturarButton() {
        return facturarButton;
    }

    public void setFacturarButton(JButton facturarButton) {
        this.facturarButton = facturarButton;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JComboBox<String> getMedioPagoComboBox() {
        return medioPagoComboBox;
    }

    public void setMedioPagoComboBox(JComboBox<String> medioPagoComboBox) {
        this.medioPagoComboBox = medioPagoComboBox;
    }

    public JLabel getMedioPagoLabel() {
        return medioPagoLabel;
    }

    public void setMedioPagoLabel(JLabel medioPagoLabel) {
        this.medioPagoLabel = medioPagoLabel;
    }

    public JLabel getHoraLabel() {
        return horaLabel;
    }

    public void setHoraLabel(JLabel horaLabel) {
        this.horaLabel = horaLabel;
    }

    public JLabel getFechaLabel() {
        return fechaLabel;
    }

    public void setFechaLabel(JLabel fechaLabel) {
        this.fechaLabel = fechaLabel;
    }

    public JTextField getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(JTextField idProducto) {
        this.idProducto = idProducto;
    }

    public JTextField getApellido() {
        return apellido;
    }

    public void setApellido(JTextField apellido) {
        this.apellido = apellido;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    // Método para hacer visible la ventana
    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("Facturar");
        frame.setContentPane(this.getPanel5());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public String toString() {
        return "Facturar{" +
                "panel5=" + panel5 +
                ", nombre=" + nombre +
                ", cuitCuil=" + cuitCuil +
                ", telefono=" + telefono +
                ", domicilio=" + domicilio +
                ", mail=" + mail +
                ", desc=" + desc +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                ", clientesButton=" + clientesButton +
                ", stockButton=" + stockButton +
                ", proveedorButton=" + proveedorButton +
                ", tablaFacturar=" + tablaFacturar +
                ", calcularButton=" + calcularButton +
                ", descuento=" + descuento +
                ", addButton=" + addButton +
                ", eliminarButton=" + eliminarButton +
                ", subTotalLabel=" + subTotalLabel +
                ", totalLabel=" + totalLabel +
                ", idCliente=" + idCliente +
                ", facturarButton=" + facturarButton +
                ", scrollPane=" + scrollPane +
                ", medioPagoComboBox=" + medioPagoComboBox +
                ", medioPagoLabel=" + medioPagoLabel +
                ", horaLabel=" + horaLabel +
                ", fechaLabel=" + fechaLabel +
                ", idProducto=" + idProducto +
                ", apellido=" + apellido +
                ", userId=" + userId +
                '}';
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Facturar().setVisible(true);
            }
        });
    }
}
