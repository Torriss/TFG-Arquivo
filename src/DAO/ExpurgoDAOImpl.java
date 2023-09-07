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
        // Crear una lista para almacenar los expedientes desde el archivo Excel
        ArrayList<Expediente> expedientesAEliminar = new ArrayList<Expediente>();

        try {
            FileInputStream fis = new FileInputStream(new File(filePath));
            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            //Leer la primera hoja del archivo Excel (puedes ajustar esto seg�n tu estructura)
            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();

            //Saltar la primera fila si contiene encabezados
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                //Crear un objeto Expediente y asignar valores desde el archivo Excel 
                Expediente expediente = new Expediente();
                //"Tipo", "Numero Expediente", "Anio", "Ubicacion", "Notas", "Tomos", "Juzgado", "Lugar", "Caja", "Paginas", "Estado"
                expediente.setTipo(cellIterator.next().getStringCellValue());
                expediente.setNumExpediente((int) cellIterator.next().getNumericCellValue());
                expediente.setAnio((int) cellIterator.next().getNumericCellValue());
                expediente.setUbicacion("");
                expediente.setNotas(cellIterator.next().getStringCellValue());
                expediente.setTomos(cellIterator.next().getStringCellValue());
                expediente.setJuzgado(cellIterator.next().getStringCellValue());
                expediente.setLugar("");
                expediente.setCaja((int) cellIterator.next().getNumericCellValue());
                expediente.setPaginas((int) cellIterator.next().getNumericCellValue());
                expediente.setEstado("Expurgado");

                expedientesAEliminar.add(expediente);
            }

            fis.close();
            workbook.close();
        } catch (IOException e) {
           throw e;
        }
        
        //Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        //Crear un formato de fecha
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //Formatear la fecha actual como una cadena
        String fechaHito = fechaActual.format(formatoFecha);
        
        //Recorremos la lista y cambiamos le estado de todos los expedientes a expurgado
        //Aniadir todos los expedientes a tabla expurgos
        ExpedienteDAO exp = new ExpedienteDAOImpl();
        HistoricoDAO hist = new HistoricoDAOImpl();
        CajaDAO caja = new CajaDAOImpl();
        for (Expediente expediente : expedientesAEliminar) {
        	Caja box = caja.getById(expediente.getCaja());
        	box.sumarPaginas(expediente.getPaginas());
        	expediente.setCaja(-1);
            exp.update(expediente);
            Historico fila = new Historico(expediente.getNumExpediente(), expediente.getTipo(), expediente.getAnio(), expediente.getJuzgado(), fechaHito);
            if(!hist.existeHistorico(fila, "historicaExpurgo")); hist.insert(fila, "historicaExpurgo");
        }
        
        return true;
    }
}

