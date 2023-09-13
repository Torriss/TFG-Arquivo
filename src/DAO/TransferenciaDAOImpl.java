package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Caja;
import model.Expediente;
import model.Historico;

public class TransferenciaDAOImpl implements TransferenciaDAO {

    public boolean transferirExpedientes(String filePath) throws ClassNotFoundException, SQLException, IOException {
    	boolean res = false;
    	ArrayList<Expediente> expedientesNuevos = new ArrayList<Expediente>();
        CajaDAO cajas = new CajaDAOImpl();
        ExpedienteDAO exp = new ExpedienteDAOImpl();
        HistoricoDAO hist = new HistoricoDAOImpl();

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
                
                Expediente expediente = new Expediente();
                
                for (int i = 0; i < 8; i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    //Verificar el tipo de celda y asignar valores
                    switch (i) {
                        case 0:
                            expediente.setTipo(getStringCellValue(cell));
                            break;
                        case 1:
                            expediente.setNumExpediente((int) getNumericCellValue(cell));
                            break;
                        case 2:
                            expediente.setAnio((int) getNumericCellValue(cell));
                            break;
                        case 3:
                            expediente.setNotas(getStringCellValue(cell));
                            break;
                        case 4:
                            expediente.setTomos(getStringCellValue(cell));
                            break;
                        case 5:
                            expediente.setJuzgado(getStringCellValue(cell));
                            break;
                        case 6:
                            expediente.setLugar(getStringCellValue(cell));
                            break;
                        case 7:
                        	expediente.setPaginas((int)getNumericCellValue(cell));
                    }
                }
                expedientesNuevos.add(expediente);
            }
            fis.close();
            workbook.close();
        } catch (IOException e) {
            throw e;
        }

        if (!expedientesNuevos.isEmpty()) res = true;
        
        
        for (Expediente expediente : expedientesNuevos) {
        	if(!exp.existeExpedienteTomos(expediente.getNumExpediente(), expediente.getTipo(), expediente.getAnio(), expediente.getJuzgado(), expediente.getTomos())) {
	        	//Obtener la fecha actual
	            LocalDate fechaActual = LocalDate.now();
	            //Crear un formato de fecha
	            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            //Formatear la fecha actual como una cadena
	            String fechaHito = fechaActual.format(formatoFecha);
	        	//buscamos caja nueva para expediente
	        	Caja caja = cajas.buscarCajasParaExpedienteNuevo(expediente.getAnio(), expediente.getTipo(), expediente.getPaginas());
	        	//actualizar ubi de expediente
	        	expediente.setUbicacion(caja.getUbicacion());
	        	//actualizar caja de expediente
	        	expediente.setCaja(caja.getIdCaja());
	        	//cambiamos estado a transferido
	        	expediente.setEstado("transferido");
	        	//restar paginas de caja
	        	caja.restarPaginas(expediente.getPaginas());
	        	//hacemos update e insert en bbdd
	        	cajas.update(caja);
	        	exp.insert(expediente);
	        	//numExpediente, String tipo, int anio, String juzgado, String fechaHito
	        	Historico fila = new Historico(expediente.getNumExpediente(), expediente.getTipo(), expediente.getAnio(), expediente.getJuzgado(), fechaHito);
	        	hist.insert(fila, "historicatransferencia");
        	}
        }
        
        return res;
    }
    
    private String getStringCellValue(Cell cell) {
        if (cell != null) {
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                    return String.valueOf(cell.getNumericCellValue());
                default:
                    return "";
            }
        }
        return "";
    }

    private double getNumericCellValue(Cell cell) {
        if (cell != null) {
            switch (cell.getCellType()) {
                case STRING:
                    try {
                        return Double.parseDouble(cell.getStringCellValue());
                    } catch (NumberFormatException e) {
                        return 0.0; 
                    }
                case NUMERIC:
                    return cell.getNumericCellValue();
                default:
                    return 0.0;
            }
        }
        return 0.0;
    }
    
}