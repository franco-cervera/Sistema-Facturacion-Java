import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class log1 {
    Connect jj = new Connect();
    public int logInButton(String username, String password) {
        jj.conectar();
        Connection con = jj.cc();
        PreparedStatement ps;
        String sql = "SELECT id_usuarios FROM usuarios WHERE nombre = ? AND contraseña = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id_usuarios"); // Obtener el ID del usuario y retornarlo
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

}