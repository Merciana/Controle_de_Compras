package index.js.controle_de_compras.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        try {
            // Registro do driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Retorne a conexão com o banco de dados
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database_planilha", "Merciana", "Mercy031!@#");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC não encontrado.", e);
        }
    }
}
