import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Log {
    private JPanel panel1;
    private JTextField usuario;
    private JButton logInButton;
    private JPasswordField passwordField1;
    private JButton registrar;

    public Log() {
        initComponents();
    }

    private void initComponents() {
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logIn();
            }
        });

        registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });
    }

    private void logIn() {
        String username = usuario.getText();
        String password = new String(passwordField1.getPassword());

        // Validar usuario y contraseña usando los datos de Registro
        if (username.equals(Registro.getRegisteredUsername()) && password.equals(Registro.getRegisteredPassword())) {
            JOptionPane.showMessageDialog(panel1, "Inicio de sesión exitoso.");
            Proveedores proveedores = new Proveedores();
            proveedores.setVisible(true);
            JFrame logFrame = (JFrame) SwingUtilities.getWindowAncestor(panel1);
            if (logFrame != null) {
                logFrame.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(panel1, "Usuario o contraseña incorrectos.");
        }
    }

    private void register() {
        Registro registro = new Registro();
        registro.setVisible(true);
        JFrame logFrame = (JFrame) SwingUtilities.getRoot(panel1);
        logFrame.dispose();
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JTextField getUsuario() {
        return usuario;
    }

    public void setUsuario(JTextField usuario) {
        this.usuario = usuario;
    }

    public JButton getLogInButton() {
        return logInButton;
    }

    public void setLogInButton(JButton logInButton) {
        this.logInButton = logInButton;
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public void setPasswordField1(JPasswordField passwordField1) {
        this.passwordField1 = passwordField1;
    }

    public JButton getRegistrar() {
        return registrar;
    }

    public void setRegistrar(JButton registrar) {
        this.registrar = registrar;
    }

    @Override
    public String toString() {
        return "Log{" +
                "usuario=" + usuario.getText() +
                ", passwordField1=" + new String(passwordField1.getPassword()) +
                '}';
    }

    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("Log");
        frame.setContentPane(this.getPanel1());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(visible);
    }

    public static void main(String[] args) {
        Log log = new Log();
        log.setVisible(true);
    }
}