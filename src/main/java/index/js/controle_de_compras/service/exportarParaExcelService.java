package index.js.controle_de_compras.service;

import index.js.controle_de_compras.model.compra;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


@Service
public class exportarParaExcelService {
    public void exportarParaExcel(List<compra> compras, String caminhoArquivo) throws IOException {

        File diretorio = new File("files");
    if (!diretorio.exists()) {
        diretorio.mkdirs();
    }

    String caminhoCompleto = "files/compras.xlsx";

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Compras");

        Row headerRow = sheet.createRow(0);
        String[] colunas = {"ID", "Comprador", "Solicitante", "Almoxerifado", "Empresa", "Nota Fiscal", "Palavra Chave", "Produto", "Data de Chegada", "Nome Porteiro", "Data Recebido", "Tarefa Concluída"};

        for (int i = 0; i < colunas.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(colunas[i]);
        }

        int rowNum = 1;
        for (compra compra : compras) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(compra.getId());
            row.createCell(1).setCellValue(compra.getNomeComprador());
            row.createCell(2).setCellValue(compra.getNomeSolicitante());
            row.createCell(3).setCellValue(compra.getAlmoxerifado());
            row.createCell(5).setCellValue(compra.getNomeEmpresa());
            row.createCell(6).setCellValue(compra.getNotaFiscal());
            row.createCell(8).setCellValue(compra.getPalavraChave());
            row.createCell(4).setCellValue(compra.getNomeProduto());
            row.createCell(7).setCellValue(compra.getDataChegada().toString());
            row.createCell(9).setCellValue(compra.getNomePorteiro());
            String dataRecebido = (compra.getDataRecebido() != null) ? compra.getDataRecebido().toString() : "N/A";
            row.createCell(10).setCellValue(dataRecebido);

            row.createCell(11).setCellValue(compra.getConcluido() ? "Concluída" : "Pendente");
        }

        try (FileOutputStream fileOut = new FileOutputStream(caminhoCompleto)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo Excel: " + e.getMessage());
            throw e;
        } finally {
            workbook.close();
        }
    }
    
}
