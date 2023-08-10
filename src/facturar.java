import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class facturar extends facturar1 {
    private JPanel panel5;
    private JTextField nombre;
    private JTextField cuitcuil;
    private JTextField telefono;
    private JTextField domicilio;
    private JTextField mail;
    private JTextField desc;
    private JTextField precio;
    private JTextField cantidad;
    private JButton clientesButton;
    private JButton stockButton;
    private JButton proveedorButton;
    private JTable tablafacturar;
    private JButton calcularButton;
    private JTextField descuento;
    private JButton ADDButton;
    private JButton ELIMINARButton;
    private JLabel subtotalll;
    private JLabel totalll;
    private JLabel subtotal;
    private JLabel total;
    private JTextField cuitcuilcomp;
    private JButton COMPROBARButton;
    private JTextField idstock;
    private JButton COMPROBARSTOCKButton;
    private JTextField idcliente;
    private JButton FACTURARButton;
    private JScrollPane paneton5;
    private JComboBox boxmediopago;
    private JLabel mediopago;
    private JLabel hora;
    private JLabel fecha;
    private JTextField idproducto;
    private JTextField apellido;
    private JLabel usuario;
    facturar1 vv= new facturar1();
    Connect jj=new Connect();
    DefaultTableModel zz = new DefaultTableModel();

    public void RellenarTabla() {

            zz.addColumn("ID CLIENTE");
            zz.addColumn("PRODUCTO");
            zz.addColumn("PRECIO");
            zz.addColumn("CANTIDAD");
            zz.addColumn("FECHA");
            zz.addColumn("HORA");
            zz.addColumn("MEDIO PAGO");


            paneton5.setViewportView(tablafacturar);
            tablafacturar.setVisible(false);
            tablafacturar.setModel(zz);
            zz.fireTableDataChanged();
            tablafacturar.setVisible(true);
    }
    public facturar() {
        jj.conectar();
        RellenarTabla();
        fechayhora();
        MostrarUsuario();

        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    Object[] linea = new Object[7];
                    linea[0] = idcliente.getText();
                    linea[1] = desc.getText();
                    linea[2] = precio.getText();
                    linea[3] = cantidad.getText();
                    linea[4] = fecha.getText();
                    linea[5] = hora.getText();
                    linea[6] = boxmediopago.getSelectedItem();


                    zz.addRow(linea);
                    tablafacturar.setModel(zz);

                    String b, c;
                    float n2, n3;
                    b = precio.getText();
                    n2 = Float.parseFloat(b);
                    c = cantidad.getText();
                    n3 = Float.parseFloat(c);
                    vv.ingresarn2(n3);
                    vv.ingresarn3(n2);

                    String d;
                    d = String.valueOf(vv.calcular());
                    subtotalll.setText(d);
                }
            }
        );
        stockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stock ee = new stock();
                ee.setVisible(true);
                JFrame facturarFrame = (JFrame) SwingUtilities.getRoot(panel5);
                facturarFrame.dispose();
            }
        });
        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientes cc = new clientes();
                cc.setVisible(true);
                JFrame facturarFrame = (JFrame) SwingUtilities.getRoot(panel5);
                facturarFrame.dispose();
            }
        });
        proveedorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proveedores dd = new proveedores();
                dd.setVisible(true);
                JFrame facturarFrame = (JFrame) SwingUtilities.getRoot(panel5);
                facturarFrame.dispose();
            }
        });
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a;
                float n1;
                a = descuento.getText();
                n1 = Float.parseFloat(a);
                vv.ingresarn1(n1);
                vv.calcular2();
                String g;
                g = String.valueOf(vv.calcular2());
                totalll.setText(g);

            }
        });

        COMPROBARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String cuitcuildb;
                cuitcuildb = cuitcuilcomp.getText();

                String sql0;
                try {
                    sql0 = "select id_clientes from clientes where dni=" + cuitcuildb + "";

                    PreparedStatement ss = jj.con.prepareStatement(sql0);
                    ResultSet rs = ss.executeQuery();
                    while (rs.next()) {
                        String id = rs.getString(1);
                        idcliente.setText(id);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                String sql;
                try {
                    sql = "select nombre from clientes where dni=" + cuitcuildb + "";

                    PreparedStatement ss = jj.con.prepareStatement(sql);
                    ResultSet rs = ss.executeQuery();
                    while (rs.next()) {
                        String nom = rs.getString(1);
                        nombre.setText(nom);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                String sql6;
                try {
                    sql6 = "select apellido from clientes where dni=" + cuitcuildb + "";

                    PreparedStatement ss = jj.con.prepareStatement(sql6);
                    ResultSet rs = ss.executeQuery();
                    while (rs.next()) {
                        String ape = rs.getString(1);
                        apellido.setText(ape);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                String sql2;
                try {
                    sql2 = "select dni from clientes where dni=" + cuitcuildb + "";

                    PreparedStatement ss = jj.con.prepareStatement(sql2);
                    ResultSet rs = ss.executeQuery();
                    while (rs.next()) {
                        String dni = rs.getString(1);
                        cuitcuil.setText(dni);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                String sql3;
                try {
                    sql3 = "select telefono from clientes where dni=" + cuitcuildb + "";

                    PreparedStatement ss = jj.con.prepareStatement(sql3);
                    ResultSet rs = ss.executeQuery();
                    while (rs.next()) {
                        String tel = rs.getString(1);
                        telefono.setText(tel);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                String sql4;
                try {
                    sql4 = "select domicilio from clientes where dni=" + cuitcuildb + "";

                    PreparedStatement ss = jj.con.prepareStatement(sql4);
                    ResultSet rs = ss.executeQuery();
                    while (rs.next()) {
                        String dom = rs.getString(1);
                        domicilio.setText(dom);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                String sql5;
                try {
                    sql5 = "select mail from clientes where dni=" + cuitcuildb + "";

                    PreparedStatement ss = jj.con.prepareStatement(sql5);
                    ResultSet rs = ss.executeQuery();
                    while (rs.next()) {
                        String correo = rs.getString(1);
                        mail.setText(correo);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        COMPROBARSTOCKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id_stock;
                id_stock = Integer.parseInt(idstock.getText());
                String sql6;
                try {
                    sql6 = "select nombre from productos where id_producto=" + id_stock + "";

                    PreparedStatement ss = jj.con.prepareStatement(sql6);
                    ResultSet rs = ss.executeQuery();
                    while (rs.next()) {
                        String descr = rs.getString(1);
                        desc.setText(descr);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                String sql7;
                try {
                    sql7 = "select precio from productos where id_producto=" + id_stock + "";

                    PreparedStatement ss = jj.con.prepareStatement(sql7);
                    ResultSet rs = ss.executeQuery();
                    while (rs.next()) {
                        String costo = rs.getString(1);
                        precio.setText(costo);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                String sql8;
                try {
                    sql8 = "select cantidad from productos where id_producto=" + id_stock + "";

                    PreparedStatement ss = jj.con.prepareStatement(sql8);
                    ResultSet rs = ss.executeQuery();
                    while (rs.next()) {
                        String cant = rs.getString(1);
                        cantidad.setText(cant);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                String sql20;
                try {
                    sql20 = "select id_producto from productos where id_producto=" + id_stock + "";

                    PreparedStatement ss = jj.con.prepareStatement(sql20);
                    ResultSet rs = ss.executeQuery();
                    while (rs.next()) {
                        String idprod = rs.getString(1);
                        idproducto.setText(idprod);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Connection con = jj.cc();
                PreparedStatement ps;

                int fila = tablafacturar.getSelectedRow();
                float var = Float.parseFloat(tablafacturar.getValueAt(fila, 2).toString());
                float cantid = Float.parseFloat(tablafacturar.getValueAt(fila, 3).toString());
                zz.removeRow(fila);

                float valores = (var * cantid);

                vv.ingresarn3(valores);

                String sub;
                sub = String.valueOf(vv.restar());
                subtotalll.setText(sub);
                limpiar();
            }
        });

        FACTURARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con = jj.cc();
                float totaldb;
                int idproddb,idclientedb,userdb;
                idproddb = Integer.parseInt(idproducto.getText());
                totaldb = Float.parseFloat(totalll.getText());
                idclientedb = Integer.parseInt(idcliente.getText());
                userdb = Integer.parseInt(usuario.getText());

                if (tablafacturar.getRowCount() > 0 && !totalll.getText().isEmpty()) {
                    try {
                        PreparedStatement ps = con.prepareStatement("insert into facturas (fecha,hora,total,mediopago) values (?,?,?,?);", PreparedStatement.RETURN_GENERATED_KEYS);
                        ps.setString(1, tablafacturar.getValueAt(0, 4).toString());
                        ps.setString(2, tablafacturar.getValueAt(0, 5).toString());
                        ps.setFloat(3, totaldb);
                        ps.setString(4, boxmediopago.getSelectedItem().toString());


                        ps.executeUpdate();

                        ResultSet generatedKeys = ps.getGeneratedKeys();
                        int idFacturaGenerada = -1;
                        if (generatedKeys.next()) {
                            idFacturaGenerada = generatedKeys.getInt(1);
                        }

                        if (idFacturaGenerada != -1) {
                            for (int i = 0; i < tablafacturar.getRowCount(); i++) {
                                try {
                                    vv.ingresarn3(Float.parseFloat(tablafacturar.getValueAt(i, 2).toString()));
                                    vv.ingresarn2(Float.parseFloat(tablafacturar.getValueAt(i, 3).toString()));

                                    PreparedStatement ps1 = con.prepareStatement("Insert into detalle_factura (fk2_producto,fk3_facturas,cantidad,precio) values (?,?,?,?);");

                                    ps1.setInt(1,idproddb);
                                    ps1.setInt(2,idFacturaGenerada);
                                    ps1.setInt(3, Integer.parseInt(tablafacturar.getValueAt(i,3).toString()));
                                    ps1.setInt(4, Integer.parseInt(tablafacturar.getValueAt(i,2).toString()));

                                    ps1.executeUpdate();

                                    PreparedStatement ps2 = con.prepareStatement("Insert into clientesXfacturas (fk_clientes, fk2_factura) values (?,?);");

                                    ps2.setInt(1,idclientedb);
                                    ps2.setInt(2,idFacturaGenerada);

                                    ps2.executeUpdate();

                                    PreparedStatement ps3 = con.prepareStatement("Insert into usuarioXfacturas (fk_usuarios, fk_factura) values (?,?);");

                                    ps3.setInt(1,userdb);
                                    ps3.setInt(2,idFacturaGenerada);

                                    ps3.executeUpdate();

                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            while (zz.getRowCount() > 0) {
                                zz.removeRow(0);
                            }
                            limpiar();
                            vv.reinicio();
                            JOptionPane.showMessageDialog(null, "Factura Guardada");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al obtener el ID de la factura generada");
                        }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error: Campo Vacío");
                }
            }
        });
    }

        public void fechayhora(){

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String fechax = dateFormat.format(new Date());
        fecha.setText(fechax);

        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm:ss");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                        hora.setText(formateador.format(LocalDateTime.now()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread hilo = new Thread(runnable);
        hilo.start();
    }
    private void MostrarUsuario() {
        String sql;
        try {
            sql = "select id_usuarios from usuarios";

            PreparedStatement ss = jj.con.prepareStatement(sql);
            ResultSet  rs = ss.executeQuery();
            while (rs.next()) {
                String id_user = rs.getString(1);
                usuario.setText(id_user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void limpiar() {
        subtotalll.setText("");
        totalll.setText("");
        descuento.setText("");
    }
    public void setVisible(boolean c) {
        JFrame frame = new JFrame("facturar");
        frame.setContentPane(new facturar().panel5);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}