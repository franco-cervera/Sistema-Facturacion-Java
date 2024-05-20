import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registro {
    private JPanel panelRegistro;
    private JTextField nombre;
    private JTextField apellido;
    private JTextField legajo;
    private JPasswordField password;
    private JTextField correo;
    private JTextField sector;
    private JButton registrarButton;
    private JButton regresar;
    private static String registeredUsername;
    private static String registeredPassword;

    public Registro() {
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registeredUsername = getLegajo();
                registeredPassword = new String(getPassword());

                JOptionPane.showMessageDialog(panelRegistro, "Usuario registrado con éxito.");

                limpiar();

                regresar();
            }
        });
        regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               regresar();
            }
        });
    }

    private void regresar(){

        Log lg = new Log();
        lg.setVisible(true);
        JFrame logFrame = (JFrame) SwingUtilities.getRoot(panelRegistro);
        logFrame.dispose();
    }

    private void limpiar() {
        setNombre("");
        setApellido("");
        setLegajo("");
        setPassword("");
        setCorreo("");
        setSector("");
    }

    public void setVisible(boolean c) {
        JFrame frame = new JFrame("registro");
        frame.setContentPane(panelRegistro);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    // Getters y Setters

    public String getNombre() {
        return nombre.getText();
    }

    public void setNombre(String nombre) {
        this.nombre.setText(nombre);
    }

    public String getApellido() {
        return apellido.getText();
    }

    public void setApellido(String apellido) {
        this.apellido.setText(apellido);
    }

    public String getLegajo() {
        return legajo.getText();
    }

    public void setLegajo(String legajo) {
        this.legajo.setText(legajo);
    }

    public char[] getPassword() {
        return password.getPassword();
    }

    public void setPassword(String password) {
        this.password.setText(password);
    }

    public String getCorreo() {
        return correo.getText();
    }

    public void setCorreo(String correo) {
        this.correo.setText(correo);
    }

    public String getSector() {
        return sector.getText();
    }

    public void setSector(String sector) {
        this.sector.setText(sector);
    }

    public static String getRegisteredUsername() {
        return registeredUsername;
    }

    public static String getRegisteredPassword() {
        return registeredPassword;
    }
}
