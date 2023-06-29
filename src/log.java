import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class log{
    public JPanel panel1;
    private JTextField usuario;
    private JButton logInButton;
    private JPasswordField passwordField1;
    private JButton registrar;
    log1 rr = new log1();
    public log() {
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usuario.getText();
                String password = new String(passwordField1.getPassword());

                rr.ingresar1(username);
                rr.ingresar2(password);
                boolean loginSuccessful = rr.logInButton();

                if (loginSuccessful) {
                    proveedores dd = new proveedores();
                    dd.setVisible(true);
                    JFrame logFrame = (JFrame) SwingUtilities.getRoot(panel1);
                    logFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña o Usuario incorrecto");
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