//package model;
//
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.sql.SQLException;
//
//public class ImportExcel {
//	
//	//faltaria comprobar que el orden en que accede a las celdas del excel es el correcto 
//    public static void importDataFromExcel(String filePath) throws IOException, SQLException {
//        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
//        Workbook workbook = new XSSFWorkbook(fileInputStream);
//        Sheet sheet = workbook.getSheetAt(0);
//
//        for (Row row : sheet) {
//            // Saltar la primera fila que puede ser el encabezado
//            if (row.getRowNum() == 0) {
//                continue;
//            }
//
//            int numExpediente = (int) row.getCell(0).getNumericCellValue();
//            String tipo = row.getCell(1).getStringCellValue();
//            int anio = (int) row.getCell(2).getNumericCellValue();
//            int caja = (int) row.getCell(3).getNumericCellValue();
//            String ubicacion = row.getCell(4).getStringCellValue();
//            String notas = row.getCell(5).getStringCellValue();
//            String tomos = row.getCell(6).getStringCellValue();
//            String juzgado = row.getCell(7).getStringCellValue();
//            String lugar = row.getCell(8).getStringCellValue();
//            int paginas = (int) row.getCell(9).getNumericCellValue();
//
//            Expediente exp = new Expediente(numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, paginas);
//
//            //if (Expediente.existeExpediente(numExpediente, tipo, anio, caja, ubicacion)) {
//            if (Expediente.existeExpediente(numExpediente, tipo)) {
//                // Actualizar el expediente existente en la tabla
//                Expediente.update(exp);
//            } else {
//                // Insertar el nuevo expediente en la tabla
//                Expediente.insert(exp);
//            }
//        }
//
//        workbook.close();
//        fileInputStream.close();
//    }
//
//}
