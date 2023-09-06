package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import DAO.ExpedienteDAO;
import DAO.ExpedienteDAOImpl;
import utils.FuncComunes;

public class ExportarExpedientesExcel {
    public static String exportarExpedientes(int numEXp, int anio, String tipo, String juzgado) throws IOException, ClassNotFoundException, SQLException {
        String fecha = FuncComunes.getFechaHora();
        
    	String filePath = "exportarExpedientes_" + fecha +".xlsx";
    	ExpedienteDAO exp = new ExpedienteDAOImpl();
    	ArrayList<Expediente> expedientes = exp.aplicaFiltrosExpediente(numEXp, tipo, anio, juzgado);
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
            throw e;
        }
        
        // Cerrar el libro
        try {
            workbook.close();
        } catch (IOException e) {
            throw e;
        }
        return filePath;
    }
    
    public static void imprimirExpedientes(ArrayList<Expediente> expedientes) throws IOException {
    	String fecha = FuncComunes.getFechaHora();

    	String filePath = "expedientes_" + fecha +".xlsx";
    	
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
        
        String[] headers = {"Tipo", "Número Expediente", "Año", "Ubicación", "Notas", "Tomos", "Juzgado", "Lugar", "Caja", "Páginas", "Estado"};
        
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerCellStyle);
        }
    }
    
    private static void fillDataRows(Sheet sheet, ArrayList<Expediente> expedientes) {
        int rowNum = 1;
        
        for (Expediente expediente : expedientes) {
            Row row = sheet.createRow(rowNum++);
            
            row.createCell(0).setCellValue(expediente.getTipo());
            row.createCell(1).setCellValue(expediente.getNumExpediente());
            row.createCell(2).setCellValue(expediente.getAnio());
            row.createCell(3).setCellValue(expediente.getUbicacion());
            row.createCell(4).setCellValue(expediente.getNotas());
            row.createCell(5).setCellValue(expediente.getTomos());
            row.createCell(6).setCellValue(expediente.getJuzgado());
            row.createCell(7).setCellValue(expediente.getLugar());
            row.createCell(8).setCellValue(expediente.getCaja());
            row.createCell(9).setCellValue(expediente.getPaginas());
            row.createCell(10).setCellValue(expediente.getEstado());
        }
    }
}
