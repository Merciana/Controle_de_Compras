package index.js.controle_de_compras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseValidator implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        try {
            jdbcTemplate.execute("SELECT 1");
            System.out.println("Conexão com o banco de dados bem-sucedida!");
        } catch (Exception e) {
            System.err.println("Falha na conexão com o banco de dados: " + e.getMessage());
        }
    }
}

