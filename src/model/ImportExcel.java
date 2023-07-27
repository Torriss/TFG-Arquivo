import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

public class ImportExcel {
	
	//faltaria comprobar que el orden en que accede a las celdas del excel es el correcto 
    public static void importDataFromExcel(String filePath) throws IOException, SQLException {
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            // Saltar la primera fila que puede ser el encabezado
            if (row.getRowNum() == 0) {
                continue;
            }

            int numExpediente = (int) row.getCell(0).getNumericCellValue();
            String tipo = row.getCell(1).getStringCellValue();
            int año = (int) row.getCell(2).getNumericCellValue();
            int caja = (int) row.getCell(3).getNumericCellValue();
            String ubicacion = row.getCell(4).getStringCellValue();
            String notas = row.getCell(5).getStringCellValue();
            String tomos = row.getCell(6).getStringCellValue();
            String juzgado = row.getCell(7).getStringCellValue();
            String lugar = row.getCell(8).getStringCellValue();

            Expediente exp = new Expediente(numExpediente, tipo, año, caja, ubicacion, notas, tomos, juzgado, lugar);

            if (Expediente.existeExpediente(numExpediente, tipo, año, caja, ubicacion)) {
                // Actualizar el expediente existente en la tabla
                Expediente.update(exp);
            } else {
                // Insertar el nuevo expediente en la tabla
                Expediente.insert(exp);
            }
        }

        fileInputStream.close();
    }

    public static void main(String[] args) {
        String filePath = "ruta_del_archivo.xlsx";
        try {
            importDataFromExcel(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
