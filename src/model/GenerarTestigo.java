package model;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class GenerarTestigo {
    public static void imprimirTestigo(ArrayList<Expediente> expedientes, String solicitante, String fecha) {
//        String nombreJuzgado = "Nombre del Juzgado"; //Reemplaza con el nombre del juzgado
//        String tipo = "Tipo"; //Reemplaza con el tipo
//        String numeroExpediente = "12345"; //Reemplaza con el número de expediente
//        String año = "2023"; //Reemplaza con el año
//        String solicitante = "Solicitante"; //Reemplaza con el solicitante
//        String fecha = "12 de septiembre de 2023"; //Reemplaza con la fecha
    	Expediente expediente = expedientes.get(0);

        try {
            //Crear un nuevo documento de Word
            XWPFDocument document = new XWPFDocument();

            //Crear una sección
            XWPFHeaderFooterPolicy headerFooterPolicy = document.getHeaderFooterPolicy();
            if (headerFooterPolicy == null) {
                headerFooterPolicy = document.createHeaderFooterPolicy();
            }

            //Agregar un encabezado
            XWPFHeader header = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);

            //Crear un párrafo en el encabezado
            XWPFParagraph headerParagraph = header.createParagraph();

            //Configurar alineación para el párrafo
            headerParagraph.setAlignment(ParagraphAlignment.LEFT);

            //Agregar el nombre del juzgado a la izquierda
            XWPFRun leftRun = headerParagraph.createRun();
            leftRun.setText(expediente.getJuzgado());

            //Crear otro párrafo en el encabezado para "ARQUIVO"
            XWPFParagraph headerParagraph2 = header.createParagraph();

            //Configurar alineación para el párrafo
            headerParagraph2.setAlignment(ParagraphAlignment.RIGHT);

            //Agregar "ARQUIVO" en mayúsculas a la derecha
            XWPFRun rightRun = headerParagraph2.createRun();
            rightRun.setText("ARQUIVO");
            rightRun.setBold(true);

            //Dividir la página en dos secciones con una línea de puntos
            XWPFParagraph divider = document.createParagraph();
            XWPFRun dividerRun = divider.createRun();
            dividerRun.setText("........................................................................................................................................");
            dividerRun.setFontSize(8);

            //Dividir la sección superior en dos secciones izquierda y derecha con una línea de puntos
            XWPFParagraph topDivider = document.createParagraph();
            XWPFRun topDividerRun = topDivider.createRun();
            topDividerRun.setText("........................................................................................................................................");
            topDividerRun.setFontSize(8);

            //Sección izquierda en la parte superior (Firma del solicitante)
            XWPFParagraph leftSection = document.createParagraph();
            XWPFRun leftRun2 = leftSection.createRun();
            leftRun2.setText("Firma del solicitante");
            leftRun2.setFontSize(10);

            //Sección derecha en la parte superior (Formulario)
            XWPFParagraph rightSection = document.createParagraph();
            XWPFRun rightRun2 = rightSection.createRun();
            rightRun2.setText("Tipo: " + expediente.getTipo() + "\nNúmero de Expediente: " + expediente.getNumExpediente() + "\nAño: " + expediente.getAnio() + "\nJuzgado: " + expediente.getJuzgado() + "\nSolicitante: " + solicitante + "\nFecha: " + fecha);
            rightRun2.setFontSize(10);

            //Sección inferior (Tabla)
            XWPFParagraph bottomSection = document.createParagraph();

            //Agregar una tabla con encabezado
            int numRows = expedientes.size() + 1; // +1 para el encabezado
            int numCols = 5; // Número de columnas

            XWPFTable table = document.createTable(numRows, numCols);
            XWPFTableRow tableRow = table.getRow(0);

            //Encabezado de la tabla
            String[] encabezadoTabla = {"Caja", "Ubicación", "Notas", "Tomos", "Lugar"};

            for (int i = 0; i < encabezadoTabla.length; i++) {
                XWPFTableCell cell = tableRow.getCell(i);
                XWPFParagraph cellParagraph = cell.getParagraphs().get(0);
                XWPFRun cellRun = cellParagraph.createRun();
                cellRun.setText(encabezadoTabla[i]);
                cellRun.setBold(true);
            }

            //Agregar filas a la tabla con los datos de Expediente
            for (int i = 0; i < expedientes.size(); i++) {
                Expediente exp = expedientes.get(i);
                XWPFTableRow newRow = table.getRow(i + 1);
                newRow.getCell(0).setText(String.valueOf(exp.getCaja()));
                newRow.getCell(1).setText(exp.getUbicacion());
                newRow.getCell(2).setText(exp.getNotas());
                newRow.getCell(3).setText(exp.getTomos());
                newRow.getCell(4).setText(exp.getLugar());
            }

            //Guardar el documento en un archivo
            FileOutputStream out = new FileOutputStream("testigo_" + fecha + "_" + solicitante +".docx");
            document.write(out);
            out.close();

            System.out.println("Documento de Word completo con encabezado y tabla generado con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

