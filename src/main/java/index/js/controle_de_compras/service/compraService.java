package index.js.controle_de_compras.service;

import index.js.controle_de_compras.model.compra;
import index.js.controle_de_compras.repository.compraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

//import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class compraService {

    @Autowired
    private compraRepository compraRepository;

    @Transactional
    public compra save(compra compra) {
        return compraRepository.save(compra);
    }

    public List<compra> findAll() {
        return compraRepository.findByIsExemploFalse();
    }

    @Transactional
    public boolean excluirCompra(Long id) {
        try {
            if (compraRepository.existsById(id)) {
                compraRepository.deleteById(id);
                return true;
            }
        } catch (Exception e) {
            System.err.println("Erro ao excluir compra: " + e.getMessage());
        }
        return false;
    }

    @Transactional
    public boolean concluidoCompra(Long id) {
        Optional<compra> compraOpt = compraRepository.findById(id);
        if (compraOpt.isPresent()) {
            compra compra = compraOpt.get();
            compra.setConcluido(true);
            compraRepository.save(compra);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean atualizarNomePorteiro(Long id, String nomePorteiro) {
        Optional<compra> compraOptional = compraRepository.findById(id);
        
        if (compraOptional.isPresent()) {
            compra compra = compraOptional.get();
            compra.setNomePorteiro(nomePorteiro);
            compraRepository.save(compra);
            return true;
        }
        
        return false;
    }
}
