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

public class ExpurgoDAOImpl implements ExpurgoDAO{
    
    public boolean expurgo(String filePath) throws IOException, ClassNotFoundException, SQLException {
    	ExpedienteDAO exp = new ExpedienteDAOImpl();
    	HistoricoDAO hist = new HistoricoDAOImpl();
    	CajaDAO caja = new CajaDAOImpl();
        boolean res = false;
    	ArrayList<Expediente> expedientesAEliminar = new ArrayList<Expediente>();

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
                
                for (int i = 0; i < 6; i++) {
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
                            expediente.setTomos(getStringCellValue(cell));
                            break;
                        case 4:
                            expediente.setJuzgado(getStringCellValue(cell));
                            break;
                        case 5:
                            expediente.setLugar(getStringCellValue(cell));
                            break;
                    }
                }
                Expediente estado = exp.buscaExpedienteTomos(expediente.getNumExpediente(), expediente.getTipo(), expediente.getAnio(), expediente.getJuzgado(), expediente.getTomos());
                if(estado.getEstado().equalsIgnoreCase("disponible") || estado.getEstado().equalsIgnoreCase("transferido")) {
                	expediente.setCaja(estado.getCaja());
                	expediente.setPaginas(estado.getPaginas());
                	expedientesAEliminar.add(expediente);
                }
                else throw new IllegalArgumentException("No se puede expurgar este expedietne");
            }
            fis.close();
            workbook.close();
        } catch (IOException e) {
            throw e;
        }
        
        if (!expedientesAEliminar.isEmpty()) res = true;

        
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaHito = fechaActual.format(formatoFecha);
        
        //Recorremos la lista y cambiamos le estado de todos los expedientes a expurgado
        //Aniadir todos los expedientes a tabla expurgos
        for (Expediente expediente : expedientesAEliminar) {
        	expediente.setEstado("expurgado");
        	Caja box = caja.getById(expediente.getCaja());
        	box.sumarPaginas(expediente.getPaginas());
        	caja.update(box);
        	expediente.setCaja(-1);
            exp.update(expediente);
            Historico fila = new Historico(expediente.getNumExpediente(), expediente.getTipo(), expediente.getAnio(), expediente.getJuzgado(), fechaHito);
            if(!hist.existeHistorico(fila, "historicaExpurgo")) hist.insert(fila, "historicaExpurgo");
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

