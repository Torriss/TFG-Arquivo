package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExporter {
    public static void exportExpedientes(List<Expediente> expedientes, String filePath) {
        // Crear un nuevo libro de Excel
        Workbook workbook = new XSSFWorkbook();
        
        // Crear una hoja en el libro
        Sheet sheet = workbook.createSheet("Expedientes");
        
        // Crear el encabezado de la tabla
        createHeaderRow(sheet);
        
        // Llenar los datos de los expedientes
        fillDataRows(sheet, expedientes);
        
        // Guardar el libro como archivo Excel
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Cerrar el libro
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void createHeaderRow(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        
        CellStyle headerCellStyle = sheet.getWorkbook().createCellStyle();
        Font headerFont = sheet.getWorkbook().createFont();
        headerFont.setBold(true);
        headerCellStyle.setFont(headerFont);
        
        String[] headers = {"ID", "Tipo", "Numero de Expediente", "Anio", "Ubicacion", "Notas", "Tomos", "Juzgado", "Lugar"};
        
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerCellStyle);
        }
    }
    
    private static void fillDataRows(Sheet sheet, List<Expediente> expedientes) {
        int rowNum = 1;
        
        for (Expediente expediente : expedientes) {
            Row row = sheet.createRow(rowNum++);
            
            //TODO: crear metodo getId()
            //row.createCell(0).setCellValue(expediente.getId());
            row.createCell(1).setCellValue(expediente.getTipo());
            row.createCell(2).setCellValue(expediente.getNumExpediente());
            row.createCell(3).setCellValue(expediente.getAnio());
            row.createCell(4).setCellValue(expediente.getUbicacion());
            row.createCell(5).setCellValue(expediente.getNotas());
            row.createCell(6).setCellValue(expediente.getTomos());
            row.createCell(7).setCellValue(expediente.getJuzgado());
            row.createCell(8).setCellValue(expediente.getLugar());
        }
    }
}
