package index.js.controle_de_compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import index.js.controle_de_compras.model.compra;

@Repository
public interface compraRepository extends JpaRepository<compra, Long> {
    List<compra> findByIsExemploFalse();
}
