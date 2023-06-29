import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class stock {
    private JTextField nombrestock;
    private JTextField preciostock;
    private JTextField cantidadstock;
    private JTextField textField5;
    private JButton facturarButton;
    private JButton clientesButton;
    private JButton proveedorButton;
    private JButton ADDButton;
    private JButton MODIFICARButton;
    private JButton ELIMINARButton;
    private JTable tablastock;
    private JPanel panel2;
    private JScrollPane paneton;
    private JTextField idstock;
    private JComboBox proveedorBox;

    private JLabel id;
    Connect jj=new Connect();
    DefaultTableModel pp = new DefaultTableModel();

    public void RellenarTabla() {
    String sql = "select * from productos;";
    try {
        PreparedStatement ps =jj.con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData resul = rs.getMetaData();
          int cantidadColumnas=resul.getColumnCount();
            pp.addColumn("ID PRODUCTO");
            pp.addColumn("NOMBRE");
            pp.addColumn("PRECIO");
            pp.addColumn("CANTIDAD");
            pp.addColumn("PROVEEDOR");

            while(rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                pp.addRow(filas);

                paneton.setViewportView(tablastock);
                tablastock.setVisible(false);
                tablastock.setModel(pp);
                pp.fireTableDataChanged();
                tablastock.setVisible(true);}

    } catch (SQLException le) {
        throw new RuntimeException(le);
    }
}
    public void MostrarTabla() {
        String sql = "select * from productos;";
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
                pp.addRow(filas);
                paneton.setViewportView(tablastock);
                tablastock.setVisible(false);
                tablastock.setModel(pp);
                pp.fireTableDataChanged();
                tablastock.setVisible(true);}
        } catch (SQLException le) {
            throw new RuntimeException(le);
        }
    }
    public stock() {
        jj.conectar();
        RellenarTabla();
        ConsultarProveedor(proveedorBox);

        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Connection con=jj.cc();
                PreparedStatement ps;

                String nomdb, nomproveedor;
                int cantdb, preciodb;
                nomdb = nombrestock.getText();
                preciodb = Integer.parseInt(preciostock.getText());
                cantdb = Integer.parseInt(cantidadstock.getText());
                nomproveedor = proveedorBox.getSelectedItem().toString();

                try {

                    ps = con.prepareStatement("insert into productos(nombre,precio,cantidad,nombre_razonsocial) values(?,?,?,?);");
                    ps.setString(1, nomdb);
                    ps.setInt(2, preciodb);
                    ps.setInt(3, cantdb);
                    ps.setString(4,nomproveedor);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Producto Guardado");
                    int filas=pp.getRowCount();
                    for(int i=0; filas>i; i++) {
                        pp.removeRow(0);
                    }
                    MostrarTabla();
                    limpiar();
                    IDstock();
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
                int fila = tablastock.getSelectedRow();
                String codigo = tablastock.getValueAt(fila, 0).toString();
                try {
                    ps = con.prepareStatement("DELETE FROM productos WHERE id=?");
                    ps.setString(1, codigo);
                    ps.execute();
                } catch (SQLException exc) {
                    throw new RuntimeException(exc);
                }
                pp.removeRow(fila);
                JOptionPane.showMessageDialog(null, "Producto Eliminado");
                limpiar();
            }
        });
        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientes cc = new clientes();
                cc.setVisible(true);
                JFrame stockFrame = (JFrame) SwingUtilities.getRoot(panel2);
                stockFrame.dispose();
            }
        });
        proveedorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proveedores dd=new proveedores();
                dd.setVisible(true);
                JFrame stockFrame = (JFrame) SwingUtilities.getRoot(panel2);
                stockFrame.dispose();
            }
        });
        facturarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                facturar ff = new facturar();
                ff.setVisible(true);
                JFrame stockFrame = (JFrame) SwingUtilities.getRoot(panel2);
                stockFrame.dispose();
            }
        });
        MODIFICARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Connection con=jj.cc();
                PreparedStatement ps;
                String nomdb;
                int cantdb, preciodb,iddb;
                nomdb = nombrestock.getText();
                cantdb = Integer.parseInt(preciostock.getText());
                preciodb = Integer.parseInt(cantidadstock.getText());
                iddb = Integer.parseInt((idstock.getText()));
                try {
                    ps = con.prepareStatement("UPDATE productos SET nombre=?, precio=?, cantidad=? WHERE id_producto=?;");
                    ps.setString(1, nomdb);
                    ps.setInt(2, cantdb);
                    ps.setInt(3, preciodb);
                    ps.setInt(4,iddb);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Producto Modificado");
                    int filas=pp.getRowCount();
                    for(int i=0; filas>i; i++) {
                        pp.removeRow(0);
                    }
                    MostrarTabla();
                    limpiar();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        tablastock.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int fila = tablastock.rowAtPoint(e.getPoint());
                idstock.setText(tablastock.getValueAt(fila,0).toString());
                nombrestock.setText(tablastock.getValueAt(fila,1).toString());
                preciostock.setText(tablastock.getValueAt(fila,2).toString());
                cantidadstock.setText(tablastock.getValueAt(fila,3).toString());
            }
        });
    }
    public void ConsultarProveedor(JComboBox proveedor){
        String sql ="SELECT nombre_razonsocial FROM proveedor";
        try {
            PreparedStatement ps =jj.con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
               proveedor.addItem(rs.getString("nombre_razonsocial"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
   /* public void ID_proveedor(JLabel id_proveedor){
        String sql = "Select id_proveedor from proveedor";
        try {
            PreparedStatement ps = jj.con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
  /*  int d;
    public int fid(){
        if (d==0)
        { return (d=1);
        }else{
            d=d+1;
            return d;
        }
    }*/
    private void limpiar() {
        nombrestock.setText("");
        preciostock.setText("");
        cantidadstock.setText("");
    }
    public void IDstock() {
        String sql = "SELECT id_producto FROM productos WHERE id_producto = ?;";
        String sql1 = "INSERT INTO productosxproveedor (fk_producto) VALUES (?);";
        try {
            PreparedStatement ps = jj.con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData resul = rs.getMetaData();
            int idproducto = resul.getColumnCount();
            ps.setInt(1, idproducto);
            if (rs.next()) {
                int id = rs.getInt("id_producto");
                PreparedStatement ps1 = jj.con.prepareStatement(sql1);
                ps1.setInt(1, id);
                ps1.executeUpdate();

                ps1.close();
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setVisible(boolean c) {
        JFrame frame = new JFrame("stock");
        frame.setContentPane(new stock().panel2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}