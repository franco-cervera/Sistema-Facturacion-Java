import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Connect {
    Statement statement;
    Connection con;

    public Connection cc()
    {
        return con;
    }

    public void conectar() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/facturacion?&useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "root";

        try {
            Class.forName(driver);

            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("Conexion exitosa");
            }

            statement = con.createStatement();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}

