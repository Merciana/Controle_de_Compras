package index.js.controle_de_compras.repository;

import index.js.controle_de_compras.model.role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface roleRepository extends JpaRepository<role, Long> {
    role findByName(String name);
}
