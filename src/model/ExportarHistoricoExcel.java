package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import DAO.HistoricoDAO;
import DAO.HistoricoDAOImpl;

public class ExportarHistoricoExcel {
    public static void exportarHistorico(String tabla) throws IOException, ClassNotFoundException, SQLException {
    	String filePath = tabla + ".xlsx";
    	HistoricoDAO hist = new HistoricoDAOImpl();
    	List<Historico> historico = hist.getAllRows(tabla);
        // Crear un nuevo libro de Excel
        Workbook workbook = new XSSFWorkbook();
        
        // Crear una hoja en el libro
        Sheet sheet = workbook.createSheet("Historica");
        
        // Crear el encabezado de la tabla
        createHeaderRow(sheet);
        
        // Llenar los datos de los expedientes
        fillDataRows(sheet, historico);
        
        // Guardar el libro como archivo Excel
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            throw e;
        }
        
        // Cerrar el libro
        try {
            workbook.close();
        } catch (IOException e) {
            throw e;
        }
    }
    
    private static void createHeaderRow(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        
        CellStyle headerCellStyle = sheet.getWorkbook().createCellStyle();
        Font headerFont = sheet.getWorkbook().createFont();
        headerFont.setBold(true);
        headerCellStyle.setFont(headerFont);
        
        String[] headers = {"Tipo", "Numero Expediente", "Anio", "Juzgado", "Fecha Hito"};
        
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerCellStyle);
        }
    }
    
    private static void fillDataRows(Sheet sheet, List<Historico> filas) {
        int rowNum = 1;
        
        for (Historico hist : filas) {
            Row row = sheet.createRow(rowNum++);
            
            row.createCell(1).setCellValue(hist.getTipo());
            row.createCell(2).setCellValue(hist.getNumExpediente());
            row.createCell(3).setCellValue(hist.getAnio());
            row.createCell(7).setCellValue(hist.getJuzgado());
            row.createCell(11).setCellValue(hist.getFechaHito());
        }
    }
}

