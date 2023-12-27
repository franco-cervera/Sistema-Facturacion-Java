import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class registro {
    private JPanel panelRegistro;
    private JTextField nombre;
    private JTextField apellido;
    private JTextField usuario;
    private JPasswordField password;
    private JTextField correo;
    private JTextField categoria;
    private JButton registrarButton;
    private JButton regresar;
    Connect jj=new Connect();
    public registro() {
        jj.conectar();
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con = jj.cc();
                PreparedStatement ps;

                String nomdb,apedb,userdb,passdb,maildb,catdb;
                nomdb = nombre.getText();
                apedb = apellido.getText();
                userdb = usuario.getText();
                passdb = new String(password.getPassword());
                maildb = correo.getText();
                catdb = categoria.getText();
                    try {
                        ps = con.prepareStatement("INSERT into usuarios(nombre,apellido,usuario,contraseña,correo,categoría) values (?,?,?,?,?,?);");
                        ps.setString(1,nomdb);
                        ps.setString(2,apedb);
                        ps.setString(3,userdb);
                        ps.setString(4,passdb);
                        ps.setString(5,maildb);
                        ps.setString(6,catdb);
                        ps.executeUpdate();

                        JOptionPane.showMessageDialog(null,"Usuario Registrado","Completado",JOptionPane.INFORMATION_MESSAGE);

                        } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                limpiar();


            }
        });
       regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log lg = new log();
                lg.setVisible(true);
                JFrame logFrame = (JFrame) SwingUtilities.getRoot(panelRegistro);
                logFrame.dispose();
            }
        });
    }
    private void limpiar() {
        nombre.setText("");
        apellido.setText("");
        usuario.setText("");
        password.setText("");
        correo.setText("");
        categoria.setText("");
    }

    public void setVisible(boolean c) {
        JFrame frame = new JFrame("registro");
        frame.setContentPane(new registro().panelRegistro);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
