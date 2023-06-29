import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class clientes {
    private JPanel panel3;
    private JTextField nombre;
    private JTextField cuitcuil;
    private JTextField telefono;
    private JTextField domicilio;
    private JTextField mail;
    private JButton ADDButton;
    private JButton MODIFICARButton;
    private JButton ELIMINARButton;
    private JTable tablaclientes;
    private JLabel nors;
    private JLabel dni;
    private JLabel tel;
    private JLabel dom;
    private JLabel mai;
    private JButton facturarButton;
    private JButton stockButton;
    private JButton proveedorButton;
    private JTextField idclientes;
    private JScrollPane paneton3;
    private JTextField apellido;

    Connect jj=new Connect();
    DefaultTableModel gg = new DefaultTableModel();

    public void MostrarTabla() {
        String sql = "select * from clientes;";
        try {
            PreparedStatement ps =jj.con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData resul = rs.getMetaData();
            int cantidadColumnas=resul.getColumnCount();

        while(rs.next()){
            Object[] filas = new Object[cantidadColumnas];
            for (int i = 0; i < cantidadColumnas; i++) {
                filas[i] = rs.getObject(i + 1);
            }
            gg.addRow(filas);
            paneton3.setViewportView(tablaclientes);
            tablaclientes.setVisible(false);
            tablaclientes.setModel(gg);
            gg.fireTableDataChanged();
            tablaclientes.setVisible(true);}

        } catch (SQLException le) {
            throw new RuntimeException(le);
        }
    }
    public void RellenarTabla() {
        String sql = "select * from clientes;";
        try {
            PreparedStatement ps =jj.con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData resul = rs.getMetaData();
            int cantidadColumnas=resul.getColumnCount();
            gg.addColumn("ID");
            gg.addColumn("NOMBRE");
            gg.addColumn("APELLIDO");
            gg.addColumn("DNI");
            gg.addColumn("TELEFONO");
            gg.addColumn("DOMICILIO");
            gg.addColumn("MAIL");

            while(rs.next()){
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                gg.addRow(filas);
                paneton3.setViewportView(tablaclientes);
                tablaclientes.setVisible(false);
                tablaclientes.setModel(gg);
                gg.fireTableDataChanged();
                tablaclientes.setVisible(true);}
        } catch (SQLException le) {
            throw new RuntimeException(le);
        }
    }
    public clientes() {
        jj.conectar();
        RellenarTabla();

        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con= jj.cc();
                PreparedStatement ps;

                String nomdb,apedb,domdb,maildb,cuitcuildb,teldb;
                nomdb = nombre.getText();
                apedb = apellido.getText();
                cuitcuildb = cuitcuil.getText();
                teldb = telefono.getText();
                domdb = domicilio.getText();
                maildb = mail.getText();
                try {
                    ps = con.prepareStatement("insert into clientes(nombre,apellido,dni,telefono,domicilio,mail) values(?,?,?,?,?,?);");
                    ps.setString(1, nomdb);
                    ps.setString(2, apedb);
                    ps.setString(3, cuitcuildb);
                    ps.setString(4, teldb);
                    ps.setString(5, domdb);
                    ps.setString(6, maildb);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Cliente Guardado");
                    int filas=gg.getRowCount();
                    for(int i=0; filas>i; i++) {
                        gg.removeRow(0);
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
                int fila = tablaclientes.getSelectedRow();
                String codigo = tablaclientes.getValueAt(fila, 0).toString();
                try {
                    ps = con.prepareStatement("DELETE FROM clientes WHERE id_clientes=?");
                    ps.setString(1, codigo);
                    ps.execute();
                } catch (SQLException exc) {
                    throw new RuntimeException(exc);
                }
                gg.removeRow(fila);
                JOptionPane.showMessageDialog(null, "Cliente Eliminado");
                limpiar();
            }
        });
        stockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stock ee = new stock();
                ee.setVisible(true);
                JFrame clientesFrame = (JFrame) SwingUtilities.getRoot(panel3);
                clientesFrame.dispose();
            }
        });
        proveedorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proveedores dd=new proveedores();
                dd.setVisible(true);
                JFrame clientesFrame = (JFrame) SwingUtilities.getRoot(panel3);
                clientesFrame.dispose();
            }
        });
        facturarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                facturar ff = new facturar();
                ff.setVisible(true);
                JFrame clientesFrame = (JFrame) SwingUtilities.getRoot(panel3);
                clientesFrame.dispose();
            }
        });
        MODIFICARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con=jj.cc();
                PreparedStatement ps;
                String nomdb,apedb,domdb,maildb,teldb;
                int cuitcuildb,iddb;
                nomdb = nombre.getText();
                apedb = apellido.getText();
                cuitcuildb = Integer.parseInt(cuitcuil.getText());
                teldb = telefono.getText();
                domdb = domicilio.getText();
                maildb = mail.getText();
                iddb = Integer.parseInt((idclientes.getText()));
                try {
                    ps = con.prepareStatement("UPDATE clientes SET nombre=?,apellido=?, dni=?, telefono=?, domicilio=?, mail=? WHERE id_clientes=?;");
                    ps.setString(1, nomdb);
                    ps.setString(2, apedb);
                    ps.setInt(3, cuitcuildb);
                    ps.setString(4, teldb);
                    ps.setString(5, domdb);
                    ps.setString(6, maildb);
                    ps.setInt(7,iddb);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Proveedor Modificado");
                    int filas=gg.getRowCount();
                    for(int i=0; filas>i; i++) {
                        gg.removeRow(0);
                    }
                    MostrarTabla();
                    limpiar();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        tablaclientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int fila = tablaclientes.rowAtPoint(e.getPoint());
                idclientes.setText(tablaclientes.getValueAt(fila,0).toString());
                nombre.setText(tablaclientes.getValueAt(fila,1).toString());
                apellido.setText(tablaclientes.getValueAt(fila,2).toString());
                cuitcuil.setText(tablaclientes.getValueAt(fila,3).toString());
                telefono.setText(tablaclientes.getValueAt(fila,4).toString());
                domicilio.setText(tablaclientes.getValueAt(fila,5).toString());
                mail.setText(tablaclientes.getValueAt(fila,6).toString());
            }
        });
    }
    private void limpiar() {
        nombre.setText("");
        apellido.setText("");
        cuitcuil.setText("");
        telefono.setText("");
        domicilio.setText("");
        mail.setText("");
    }
    public void setVisible(boolean c) {
        JFrame frame = new JFrame("clientes");
        frame.setContentPane(new clientes().panel3);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}