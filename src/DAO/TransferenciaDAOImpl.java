package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Caja;
import model.Expediente;

public class TransferenciaDAOImpl implements TransferenciaDAO {

    public ArrayList<Expediente> transferirExpedientes(String filePath) throws ClassNotFoundException, SQLException, IOException {
    	ArrayList<Expediente> expedientesNuevos = new ArrayList<Expediente>();
    	ArrayList<Expediente> expedientesActualizar = new ArrayList<Expediente>();
        CajaDAO cajas = new CajaDAOImpl();
        ExpedienteDAO exp = new ExpedienteDAOImpl();

        try {
            FileInputStream fis = new FileInputStream(new File(filePath));
            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            // Leer la primera hoja del archivo Excel
            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();

            // Saltar la primera fila si contiene encabezados
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                //Crear un objeto Expediente y asignar valores desde el archivo Excel
                Expediente expediente = new Expediente();

                expediente.setTipo(cellIterator.next().getStringCellValue());
                expediente.setNumExpediente((int) cellIterator.next().getNumericCellValue());
                expediente.setAnio((int) cellIterator.next().getNumericCellValue());
                expediente.setUbicacion(cellIterator.next().getStringCellValue());
                expediente.setNotas(cellIterator.next().getStringCellValue());
                expediente.setTomos(cellIterator.next().getStringCellValue());
                expediente.setJuzgado(cellIterator.next().getStringCellValue());
                expediente.setLugar(cellIterator.next().getStringCellValue());
                expediente.setCaja((int) cellIterator.next().getNumericCellValue());
                expediente.setPaginas((int) cellIterator.next().getNumericCellValue());
                // TODO: duda, aqui no deberia ser transferido??
                expediente.setEstado(cellIterator.next().getStringCellValue());
                
                //Expediente existe en bbdd por lo que solo actualizamos
                if(exp.existeExpediente(expediente.getNumExpediente(), expediente.getTipo(), expediente.getAnio(), expediente.getJuzgado())) {
                	expedientesActualizar.add(expediente);
                }
                else expedientesNuevos.add(expediente);
            }

            fis.close();
            workbook.close();
        } catch (IOException e) {
            throw e;
        }

        for (Expediente expediente : expedientesNuevos) {
        	//buscamos caja nueva para expediente
        	Caja caja = cajas.buscarCajasParaExpedienteNuevo(expediente.getAnio(), expediente.getTipo(), expediente.getPaginas());
        	//actualizar ubi de expediente
        	expediente.setUbicacion(caja.getUbicacion());
        	//actualizar caja de expediente
        	expediente.setCaja(caja.getIdCaja());
        	//restar paginas de caja
        	caja.restarPaginas(expediente.getPaginas());
        	//hacemos update e insert en bbdd
        	cajas.update(caja);
        	exp.insert(expediente);
        }
        for (Expediente expediente : expedientesActualizar) {
        	exp.update(expediente);
        }
        
        //juntamos listas para retorno
        expedientesNuevos.addAll(expedientesActualizar);
        
        return expedientesNuevos;
    }
}

