import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class proveedores {
    private JTextField nombre;
    private JTextField cuitcuil;
    private JTextField telefono;
    private JTextField domicilio;
    private JLabel nors1;
    private JPanel panel4;
    private JLabel tel1;
    private JLabel dom1;
    private JLabel mail1;
    private JLabel dni1;
    private JTextField mail;
    private JTable tablaproveedor;
    private JButton ADDButton;
    private JButton MODIFICARButton;
    private JButton ELIMINARButton;
    private JButton facturarButton;
    private JButton stockButton;
    private JButton clientesButton;
    private JScrollPane paneton2;
    private JTextField idproveedor;

    Connect jj=new Connect();
    DefaultTableModel hh = new DefaultTableModel();
    public void RellenarTabla() {
        String sql = "select * from proveedor;";
        try {
            PreparedStatement ps =jj.con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData resul = rs.getMetaData();
            int cantidadColumnas=resul.getColumnCount();
            hh.addColumn("ID");
            hh.addColumn("NOMBRE");
            hh.addColumn("CUIT O CUIL");
            hh.addColumn("TELEFONO");
            hh.addColumn("DOMICILIO");
            hh.addColumn("MAIL");

            while(rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                hh.addRow(filas);
                paneton2.setViewportView(tablaproveedor);
                tablaproveedor.setVisible(false);
                tablaproveedor.setModel(hh);
                hh.fireTableDataChanged();
                tablaproveedor.setVisible(true);}
        } catch (SQLException le) {
            throw new RuntimeException(le);
        }
    }

    public void MostrarTabla() {
        String sql = "select * from proveedor;";
        try {
            PreparedStatement ps =jj.con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData resul = rs.getMetaData();
            int cantidadColumnas=resul.getColumnCount();

            while(rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                hh.addRow(filas);
                paneton2.setViewportView(tablaproveedor);
                tablaproveedor.setVisible(false);
                tablaproveedor.setModel(hh);
                hh.fireTableDataChanged();
                tablaproveedor.setVisible(true);}
        } catch (SQLException le) {
            throw new RuntimeException(le);
        }
    }
    public proveedores() {
       jj.conectar();
       RellenarTabla();

        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con=jj.cc();
                PreparedStatement ps;

                String nomdb,domdb,maildb;
                int cuitcuildb,teldb;
                nomdb = nombre.getText();
                cuitcuildb = Integer.parseInt(cuitcuil.getText());
                teldb = Integer.parseInt(telefono.getText());
                domdb = domicilio.getText();
                maildb = mail.getText();
                try {
                    ps = con.prepareStatement("insert into proveedor(nombre_razonsocial,cuit_o_cuil,telefono,domicilio,mail) values(?,?,?,?,?);");
                    ps.setString(1, nomdb);
                    ps.setInt(2, cuitcuildb);
                    ps.setInt(3, teldb);
                    ps.setString(4, domdb);
                    ps.setString(5, maildb);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Proveedor Guardado");
                    int filas=hh.getRowCount();
                    for(int i=0; filas>i; i++) {
                        hh.removeRow(0);
                    }
                    MostrarTabla();
                    limpiar();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con=jj.cc();
                PreparedStatement ps;
                int fila = tablaproveedor.getSelectedRow();
                String codigo = tablaproveedor.getValueAt(fila, 0).toString();
                try {
                    ps = con.prepareStatement("DELETE FROM proveedor WHERE id_proveedor=?");
                    ps.setString(1, codigo);
                    ps.execute();
                } catch (SQLException exc) {
                    throw new RuntimeException(exc);
                }
                hh.removeRow(fila);
                JOptionPane.showMessageDialog(null, "Proveedor Eliminado");
                limpiar();
            }
        });

        stockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stock ee = new stock();
                ee.setVisible(true);
                JFrame proveedoresFrame = (JFrame) SwingUtilities.getRoot(panel4);
                proveedoresFrame.dispose();
            }
        });
        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientes cc = new clientes();
                cc.setVisible(true);
                JFrame proveedoresFrame = (JFrame) SwingUtilities.getRoot(panel4);
                proveedoresFrame.dispose();
            }
        });
        facturarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                facturar ff = new facturar();
                ff.setVisible(true);
                JFrame proveedoresFrame = (JFrame) SwingUtilities.getRoot(panel4);
                proveedoresFrame.dispose();
            }
        });
        MODIFICARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con=jj.cc();
                PreparedStatement ps;

                String nomdb,domdb,maildb;
                int cuitcuildb, teldb,iddb;
                nomdb = nombre.getText();
                cuitcuildb = Integer.parseInt(cuitcuil.getText());
                teldb = Integer.parseInt(telefono.getText());
                domdb = domicilio.getText();
                maildb = mail.getText();
                iddb = Integer.parseInt((idproveedor.getText()));
                try {
                    ps = con.prepareStatement("UPDATE proveedor SET nombre_razonsocial=?, cuit_o_cuil=?, telefono=?, domicilio=?, mail=? WHERE id_proveedor=?;");
                    ps.setString(1, nomdb);
                    ps.setInt(2, cuitcuildb);
                    ps.setInt(3, teldb);
                    ps.setString(4, domdb);
                    ps.setString(5, maildb);
                    ps.setInt(6,iddb);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Proveedor Modificado");
                    int filas=hh.getRowCount();
                    for(int i=0; filas>i; i++) {
                        hh.removeRow(0);
                    }
                    MostrarTabla();
                    limpiar();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        tablaproveedor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int fila = tablaproveedor.rowAtPoint(e.getPoint());
                idproveedor.setText(tablaproveedor.getValueAt(fila,0).toString());
                nombre.setText(tablaproveedor.getValueAt(fila,1).toString());
                cuitcuil.setText(tablaproveedor.getValueAt(fila,2).toString());
                telefono.setText(tablaproveedor.getValueAt(fila,3).toString());
                domicilio.setText(tablaproveedor.getValueAt(fila,4).toString());
                mail.setText(tablaproveedor.getValueAt(fila,5).toString());
            }
        });
    }
    private void limpiar() {
        idproveedor.setText("");
        nombre.setText("");
        cuitcuil.setText("");
        telefono.setText("");
        domicilio.setText("");
        mail.setText("");
    }

    public void setVisible(boolean c) {
        JFrame frame = new JFrame("proveedores");
        frame.setContentPane(new proveedores().panel4);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}