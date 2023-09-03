package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import DAO.*;
import model.Caja;
import model.Expediente;

public class TransferenciaDAOImpl implements TransferenciaDAO {

    public List<Expediente> transferirExpedientes(String filePath) {
        List<Expediente> expedientes = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(new File(filePath));
            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            // Leer la primera hoja del archivo Excel (ajusta esto seg�n tu estructura)
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
//                Expediente expediente = new Expediente();
//
//                expediente.setNumExpediente((int) cellIterator.next().getNumericCellValue());
//                expediente.setTipo(cellIterator.next().getStringCellValue());
//                expediente.setAnio((int) cellIterator.next().getNumericCellValue());
//                // ... asignar otros atributos de expediente
//                
//                CajaDAO cajas = new CajaDAOImpl();
//                List<Caja> caja = cajas.buscarCajasParaExpedienteNuevo(expediente.getAnio(), expediente.getTipo(), expediente.getPaginas());
//                //actualizar ubi de expediente
//                //actualizar caja de expediente
//                //restar paginas de caja
//                
//                expedientes.add(expediente);
            }

            fis.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Llamar a la funci�n buscarNuevaCaja para actualizar la ubicaci�n de los expedientes
        ExpedienteDAO expedienteDAO = new ExpedienteDAOImpl();
        for (Expediente expediente : expedientes) {
//            expedienteDAO.insert(expediente);
        }

        return expedientes;
    }
}

