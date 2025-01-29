package index.js.controle_de_compras.controller;

import index.js.controle_de_compras.model.compra;
import index.js.controle_de_compras.repository.compraRepository;
import index.js.controle_de_compras.service.compraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import index.js.controle_de_compras.service.exportarParaExcelService;

import java.util.Optional;
import java.io.File;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.HashMap;
import java.io.IOException;
//import java.time.LocalDate;
import java.time.LocalDate;

@Controller
@RequestMapping("/compras")
public class compraController {

    @Autowired
    private compraRepository compraRepository;

    @Autowired
    private compraService compraService;

    @Autowired
    private exportarParaExcelService exportarParaExcelService;

    @GetMapping("")
    public String listarCompras(Model model) {
        List<compra> compras = compraService.findAll();
        model.addAttribute("compras", compras);
        return "compras";
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("compra", new compra());
        return "formulario";
    }

    @PostMapping("/salvar")
    public String salvarCompra(@Valid @ModelAttribute compra compra, BindingResult result, Model model) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println("Erro: " + error.getDefaultMessage()));
            model.addAttribute("compra", compra);
            return "formulario";
        }

        System.out.println("Dados da Compra: " + compra);
        
        compraService.save(compra);

        List<compra> compras = compraService.findAll();
        String caminhoArquivo = "files/compras.xlsx";
        try {
            exportarParaExcelService.exportarParaExcel(compras, caminhoArquivo);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/compras?erro=geracaoExcel";
        }

        return "redirect:/compras";
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirCompra(@PathVariable Long id) {
        boolean sucesso = compraService.excluirCompra(id);
        if (sucesso) {
            List<compra> compras = compraService.findAll();
            String caminhoArquivo = "files/compras.xlsx";
            try {
                exportarParaExcelService.exportarParaExcel(compras, caminhoArquivo);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("success", false));
            }
            return ResponseEntity.ok().body(Collections.singletonMap("success", true));
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("success", false));
    }



    @PostMapping("/concluido/{id}")
    public ResponseEntity<Map<String, Object>> concluirCompra(@PathVariable Long id) {
        Optional<compra> compraOptional = compraRepository.findById(id);
        if (compraOptional.isPresent()) {
            compra compra = compraOptional.get();
            compra.setConcluido(true);
            compra.setDataRecebido(LocalDate.now());
            compraRepository.save(compra);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("success", false));
    }

    @PostMapping("/atualizarPorteiro/{id}")
    public ResponseEntity<Map<String, Object>> atualizarNomePorteiro(@PathVariable Long id, @RequestBody  Map<String, String> body) {
        String nomePorteiro = body.get("nomePorteiro");
        boolean success = compraService.atualizarNomePorteiro(id, nomePorteiro);
    
        if(success) {
            return ResponseEntity.ok(Map.of("success", true));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("success", false, "message", "Compra não encontrada"));
        }
    }

    @GetMapping("/exportar")
    public ResponseEntity<String> exportarParaExcel() {
        List<compra> compras = compraService.findAll();
        String caminhoArquivo = "files/compras.xlsx";

        try {
            exportarParaExcelService.exportarParaExcel(compras, caminhoArquivo);
            return ResponseEntity.ok("Arquivo Excel gerado com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao gerar o arquivo Excel.");
        }
    }

    @GetMapping("/baixarExcel")
    public ResponseEntity<FileSystemResource> baixarExcel() {
        String caminhoArquivo = "files/compras.xlsx";
        File file = new File(caminhoArquivo);
        if (file.exists()) {
            FileSystemResource resource = new FileSystemResource(file);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=" + file.getName());

            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/listarDetalhado")
    public String listarComprasDetalhadas(Model model) {
        List<compra> compras = compraService.findAll();
        model.addAttribute("compras", compras);
        return "compras";
    }
}
