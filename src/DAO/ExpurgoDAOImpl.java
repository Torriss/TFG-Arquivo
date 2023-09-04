package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import DAO.ExpedienteDAO;
import DAO.ExpedienteDAOImpl;
import model.Expediente;

public class ExpurgoDAOImpl implements ExpurgoDAO{
    
    public List<Expediente> expurgo(String filePath) {
        // Crear una lista para almacenar los expedientes desde el archivo Excel
        List<Expediente> expedientesAEliminar = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(new File(filePath));
            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            // Leer la primera hoja del archivo Excel (puedes ajustar esto seg�n tu estructura)
            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();

            // Saltar la primera fila si contiene encabezados
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                // Crear un objeto Expediente y asignar valores desde el archivo Excel
                //hacer comprobaciones de valores correctos?
//                Expediente expediente = new Expediente();
//
//                expediente.setNumExpediente((int) cellIterator.next().getNumericCellValue());
//                expediente.setTipo(cellIterator.next().getStringCellValue());
//                expediente.setAnio((int) cellIterator.next().getNumericCellValue());
//                // ... asignar otros atributos de expediente
//
//                expedientesAEliminar.add(expediente);
            }

            fis.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Recorremos la lista y cambiamos le estado de todos los expedientes a expurgado
        //Aniadir todos los expedientes a tabla expurgos
        ExpedienteDAO expedienteDAO = new ExpedienteDAOImpl();
        for (Expediente expediente : expedientesAEliminar) {
            int numExpediente = expediente.getNumExpediente();
//            expedienteDAO.delete(numExpediente);
        }
        
        
        return expedientesAEliminar;
    }
}

