import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class log1 {
    Connect jj = new Connect();
    private String user, pass;

    public void ingresar1(String username) {
        user = username;
    }

    public void ingresar2(String password) {
        pass = password;
    }

    public boolean logInButton() {
        jj.conectar();
        Connection con = jj.cc();
        PreparedStatement ps;
        String sql = "SELECT * FROM usuarios WHERE nombre = ? AND contraseña = ?";
        try {
        ps = con.prepareStatement(sql);
        ps.setString(1, user);
        ps.setString(2, pass);
            try (ResultSet resultSet = ps.executeQuery()) {
                return resultSet.next();

        }} catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }}