package index.js.controle_de_compras.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "compra")
public class compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O nome do comprador não pode ser nulo")
    @Size(min = 3, max = 100, message = "O nome do comprador deve ter entre 3 e 100 caracteres")
    private String nomeComprador;

    @NotNull(message = "Ramal é obrigatório")
    private String nomeSolicitante;

    @Column(name = "almoxerifado")
    @NotEmpty(message = "Setor é obrigatório")
    private String almoxerifado;

    @NotEmpty(message = "Nome da empresa é obrigatório")
    private String nomeEmpresa;

    @NotNull(message = "Número da nota fiscal é obrigatório")
    private long notaFiscal;

    @NotNull(message = "Número da nota fiscal é obrigatório")
    private String palavraChave;

    @NotEmpty(message = "Nome do produto é obrigatório")
    private String nomeProduto;

    @NotNull(message = "Data de recebimento é obrigatória")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataChegada;

    @Column(name = "nome_porteiro") 
    private String nomePorteiro;

    @Column(name = "data_recebido") 
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataRecebido;

    @Column(name = "tarefa_concluida")
    private Boolean concluido = false;

    @Column(nullable = false)
    private boolean isExemplo = false;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeComprador() {
        return nomeComprador;
    }

    public void setNomeComprador(String nomeComprador) {
        this.nomeComprador = nomeComprador;
    }

    public String getNomeSolicitante() {
        return nomeSolicitante;
    }

    public void setNomeSolicitante(String nomeSolicitante) {
        this.nomeSolicitante = nomeSolicitante;
    }

    public String getAlmoxerifado() {
        return almoxerifado;
    }

    public void setAlmoxerifado(String almoxerifado) {
        this.almoxerifado = almoxerifado;
    }

    
    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    
    public long getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(long notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    
    public String getPalavraChave() {
        return palavraChave;
    }

    public void setPalavraChave(String palavraChave) {
        this.palavraChave = palavraChave;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public LocalDate getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(LocalDate dataChegada) {
        this.dataChegada = dataChegada;
    }

    public String getNomePorteiro() {
        return nomePorteiro;
    }

    public void setNomePorteiro(String nomePorteiro) {
        this.nomePorteiro = nomePorteiro;
    }

    public LocalDate getDataRecebido() {
        return dataRecebido;
    }

    public void setDataRecebido(LocalDate dataRecebido) {
        this.dataRecebido = dataRecebido;
    }

    public Boolean getConcluido() {
        return concluido;
    }

    public void setConcluido(Boolean concluido) {
        this.concluido = concluido;
    }

    public Boolean getIsExemplo() {
        return isExemplo;
    }

    public void setIsExemplo(Boolean isExemplo) {
        this.isExemplo = isExemplo;
    }
}