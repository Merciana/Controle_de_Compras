package index.js.controle_de_compras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "index.js.controle_de_compras.model")
public class ControleDeComprasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleDeComprasApplication.class, args);
	}

}
