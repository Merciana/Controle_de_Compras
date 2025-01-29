package index.js.controle_de_compras.repository;

import index.js.controle_de_compras.model.appUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<appUser, Long>{
    appUser findByUsername(String username); 
}
