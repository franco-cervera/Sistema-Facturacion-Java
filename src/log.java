import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class log{
    public JPanel panel1;
    public JTextField usuario;
    public JButton logInButton;
    public JPasswordField passwordField1;
    private JButton registrar;


    log1 rr = new log1();
    facturar ff = new facturar();

    public log() {
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usuario.getText();
                String password = new String(passwordField1.getPassword());

                int userId = rr.logInButton(username,password);
                ff.setUserId(userId);
                System.out.println("ID USUARIO: " + userId);

                if (userId != -1) {
                    proveedores dd = new proveedores();
                    dd.setVisible(true);
                    JFrame logFrame = (JFrame) SwingUtilities.getRoot(panel1);
                    logFrame.dispose();

                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña o Usuario incorrecto","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registro rr = new registro();
                rr.setVisible(true);
                JFrame logFrame = (JFrame) SwingUtilities.getRoot(panel1);
                logFrame.dispose();
            }
        });

    }
/*
    public String getUser(){
        String username;
        username = usuario.getText();
        System.out.println("USUARIO:" + username);
        return username;
    }
    public String getPass(){
        String password;
        password = new String(passwordField1.getPassword());
        System.out.println("PASSWORD: " + password);
        return password;
    }
*/

    public static void main (String[] args) {
        JFrame frame = new JFrame("log");
        frame.setContentPane(new log().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
    public void setVisible(boolean c) {
        JFrame frame = new JFrame("log");
        frame.setContentPane(new log().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}