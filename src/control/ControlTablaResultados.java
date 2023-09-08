package control;

import java.io.IOException;
import java.util.ArrayList;

import model.Expediente;
import model.ExportarExpedientesExcel;
import view.ModeloTabla;
import view.TablaResultados;

public class ControlTablaResultados {

	private TablaResultados tabla;
	private ArrayList<Expediente> expedientes;
	private ModeloTabla modelo;
	
	public ControlTablaResultados(TablaResultados ctr, ArrayList<Expediente> exp) {
		tabla = ctr;
		expedientes = exp;
		initView();
	}
	
	private void initView() {
		borrarTabla();
		tabla.getBtnImprimir().setEnabled(false);

		ArrayList<String> nombresCol = new ArrayList<String>();
		nombresCol.add("Número expediente");
		nombresCol.add("Tipo");
		nombresCol.add("Año");
		nombresCol.add("Caja");
		nombresCol.add("Ubicación");
		nombresCol.add("Notas");
		nombresCol.add("Tomos");
		nombresCol.add("Juzgado");
		nombresCol.add("Lugar");
		nombresCol.add("Páginas");
		nombresCol.add("Estado");
		
		String nombresCols[] = new String[nombresCol.size()];
		for (int i = 0; i < nombresCols.length; i++) {
			nombresCols[i]=nombresCol.get(i);
		}
		
		Object[][] data = obtenerMatrizDatos(nombresCol);
		modelo = new ModeloTabla(data, nombresCols);
		tabla.getTabla().setModel(modelo);
	}
	
	private Object[][] obtenerMatrizDatos(ArrayList<String> nombresCols) {
		
		String informacion[][] = new String[expedientes.size()][nombresCols.size()];
		
		for (int x = 0; x < informacion.length; x++) {
			informacion[x][0] = expedientes.get(x).getNumExpediente()+ "";
			informacion[x][1] = expedientes.get(x).getTipo()+ "";
			informacion[x][2] = expedientes.get(x).getAnio()+ "";
			informacion[x][3] = expedientes.get(x).getCaja()+ "";
			informacion[x][4] = expedientes.get(x).getUbicacion()+ "";
			informacion[x][5] = expedientes.get(x).getNotas()+ "";
			informacion[x][6] = expedientes.get(x).getTomos()+ "";
			informacion[x][7] = expedientes.get(x).getJuzgado()+ "";
			informacion[x][8] = expedientes.get(x).getLugar()+ "";
			informacion[x][9] = expedientes.get(x).getPaginas()+ "";
			informacion[x][10] = expedientes.get(x).getEstado()+ "";
		}
		return informacion;
	}
	
	public void initControl() {
		if(!expedientes.isEmpty()) {
			tabla.getBtnImprimir().setEnabled(true);
			tabla.getBtnImprimir().addActionListener(e -> imprimirTabla());
		}
	}
	
	public void hideButtonImprimir() {
		tabla.getBtnImprimir().setVisible(false);
	}
	
	public void enableEditCells() {
		//TODO: ver si es posible hacer asi
		for (int i = 0; i < tabla.getColumnasTabla(); i++) {
			for (int j = 0; j < expedientes.size(); j++) {
				
			}
		}
	}
	
	private void imprimirTabla() {
		try {
			ExportarExpedientesExcel.imprimirExpedientes(expedientes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void borrarTabla() {
		//TODO: completar si es necesario
	}
}
