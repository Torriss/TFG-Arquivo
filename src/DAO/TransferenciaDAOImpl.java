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
import org.apache.poi.ss.usermodel.CellType;
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
    	//ArrayList<Expediente> expedientesActualizar = new ArrayList<Expediente>();
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
                int columnCount = 0; // Contador de columnas recorridas
                
                for (int i = 0; i < 8; i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    // Verificar el tipo de celda y asignar valores
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
                    //columnCount++;
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
        
        return res;
    }
    
    private String getStringCellValue(Cell cell) {
        if (cell != null) {
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                    // Si la celda contiene un número, conviértelo en cadena
                    return String.valueOf(cell.getNumericCellValue());
                default:
                    return "";
            }
        }
        return ""; // Devolver cadena vacía si la celda es nula o está vacía
    }

    // Función para obtener el valor de la celda como número, manejando celdas vacías y tipos mixtos
    private double getNumericCellValue(Cell cell) {
        if (cell != null) {
            switch (cell.getCellType()) {
                case STRING:
                    try {
                        // Intenta convertir el valor de cadena en número
                        return Double.parseDouble(cell.getStringCellValue());
                    } catch (NumberFormatException e) {
                        return 0.0; // Devolver 0.0 si no se puede convertir a número
                    }
                case NUMERIC:
                    return cell.getNumericCellValue();
                default:
                    return 0.0;
            }
        }
        return 0.0; // Devolver 0.0 si la celda es nula o está vacía
    }
    
}