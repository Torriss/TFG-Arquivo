package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GenerarTestigo {

    public static void imprimirTestigo(ArrayList<Expediente> expedientes, String solicitante, String fecha) throws IOException {
    	for (Expediente exp : expedientes) {
    		String cajaStr = String.valueOf(exp.getCaja());
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("testigo_" + exp.getTomos()+ ".txt"))) {
	            writer.write("    Madrid                                                    ARQUIVO          \n");
	            writer.write("---------------------------------------------------------------------------\n");
	            writer.write("             Préstamo               |               Tipo: " + exp.getTipo() + "\n");
	            writer.write("                                    |                                       \n");
	            writer.write("                                    |               Num Expediente: " + exp.getNumExpediente() + "\n");
	            writer.write("                                    |                                       \n");
	            writer.write("                                    |               Año: " + exp.getAnio() + "\n");
	            writer.write("                                    |                                       \n");
	            writer.write("                                    |               Juzgado: " + exp.getJuzgado() + "\n");
	            writer.write("                                    |                                       \n");
	            writer.write("                                    |               Solicitante: " + solicitante + "\n");
	            writer.write("                                    |                                       \n");
	            writer.write("                                    |               Fecha: " + fecha + "\n");
	            writer.write("                                    |                                       \n");
	            writer.write("           Firma solicitante        |                                       \n");
	            writer.write("...........................................................................\n");
	            writer.write("                                                                       \n");
	            writer.write("                                                                       \n");
	            writer.write("                     TIPO: " + exp.getTipo() + "\n");
	            writer.write("                                                                       \n");
	            writer.write("                     JUZGADO: " + exp.getJuzgado() + "\n");
	            writer.write("                                                                       \n");
	            writer.write("                     NUM EXPEDIENTE: " + exp.getNumExpediente() + "\n");
	            writer.write("                                                                       \n");
	            writer.write("                     AÑO: " + exp.getAnio() + "\n");
	            writer.write("                                                                       \n");
	            writer.write("                     SOLICITANTE: " + solicitante + "\n");
	            writer.write("                                                                       \n");
	            writer.write("                     FECHA: " + fecha + "\n");
	            writer.write("                                                                       \n");
	            writer.write("                                                                       \n");
	            writer.write("                       							                     \n");
	            writer.write("    __________________________________________________________________ \n");
	            writer.write("    | " + padCenter("CAJA", 7) + " | " + padCenter("UBICACION", 12) + " | " + padCenter("NOTAS", 10) + " | " + padCenter("TOMOS", 7) + " | " + padCenter("LUGAR", 10) + " |                  \n");
	            writer.write("    | " + padCenter(cajaStr, 7) + " | " + padCenter(exp.getUbicacion(), 12) + " | " + padCenter(exp.getNotas(), 10) + " | " + padCenter(exp.getTomos(), 7) + " | " + padCenter(exp.getLugar(), 10) + " |                  \n");	          
	            writer.write("    |-----------------------------------------------------------------|                  \n");
	
	            System.out.println("Archivo de testigo generado con éxito.");
	        } catch (IOException e) {
	            throw e;
	        }
    	}
    }
    
//    private static String padRight(String s, int n) {
//        return String.format("%-" + n + "s", s);
//    }
    private static String padCenter(String s, int n) {
        int leftPadding = (n - s.length()) / 2;
        int rightPadding = n - s.length() - leftPadding;
        return " ".repeat(leftPadding) + s + " ".repeat(rightPadding);
    }
}
