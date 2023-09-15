package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.Expediente;
import model.ExportarExpedientesExcel;
import view.ModeloTabla;
import view.TablaResultados;
import DAO.ExpedienteDAOImpl;
public class ControlTablaResultados {

	private TablaResultados tabla;
	private ArrayList<Expediente> expedientes;
	private ModeloTabla modelo;
	private ExpedienteDAOImpl expDAO = new ExpedienteDAOImpl();
	
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
			tabla.getBtnImprimir().setVisible(true);
			tabla.getBtnModificar().setVisible(true);
			tabla.getBtnEliminar().setVisible(true);
			tabla.getBtnImprimir().setEnabled(true);
			tabla.getBtnImprimir().addActionListener(e -> imprimirTabla());
			tabla.getBtnModificar().addActionListener(e -> modificarExp());
			tabla.getBtnEliminar().addActionListener(e -> eliminarExp());
		}
	}
	
	public void hideButton(JButton button) {
		
		if (button == tabla.getBtnImprimir()) {
			tabla.getBtnImprimir().setVisible(false);
		}
		else if (button == tabla.getBtnModificar()) {
			tabla.getBtnModificar().setVisible(false);
		}
		else if (button == tabla.getBtnEliminar()) {
			tabla.getBtnEliminar().setVisible(false);
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
	
	private void modificarExp() {
		try {
			ArrayList<Expediente> expedientesModif = new ArrayList<Expediente>();
			int filas = tabla.getTabla().getRowCount();
			for (int i = 0; i < filas; i++) {
				Expediente exp = new Expediente();
				exp.setNumExpediente(Integer.valueOf(tabla.getTabla().getModel().getValueAt(i, 0).toString()));
				exp.setTipo(tabla.getTabla().getModel().getValueAt(i, 1).toString());
				exp.setAnio(Integer.valueOf(tabla.getTabla().getModel().getValueAt(i, 2).toString()));
				exp.setCaja(Integer.valueOf(tabla.getTabla().getModel().getValueAt(i, 3).toString()));
				exp.setUbicacion(tabla.getTabla().getModel().getValueAt(i, 4).toString());
				exp.setNotas(tabla.getTabla().getModel().getValueAt(i, 5).toString());
				exp.setTomos(tabla.getTabla().getModel().getValueAt(i, 6).toString());
				exp.setJuzgado(tabla.getTabla().getModel().getValueAt(i, 7).toString());
				exp.setLugar(tabla.getTabla().getModel().getValueAt(i, 8).toString());
				exp.setPaginas(Integer.valueOf(tabla.getTabla().getModel().getValueAt(i, 9).toString()));
				exp.setEstado(tabla.getTabla().getModel().getValueAt(i, 10).toString());
				
				expedientesModif.add(exp);
			}
			if (!expedientesModif.isEmpty()) {
				if (expDAO.updateListas(expedientes, expedientesModif)) {
					JOptionPane.showMessageDialog(null,
							"Modificación realizada con éxito.", "Modificaciones",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void eliminarExp() {
		if (!expedientes.isEmpty()) {
			try {
				if(expDAO.delete(expedientes)) {
					JOptionPane.showMessageDialog(null,
							"Expediente eliminado con éxito.", "Modificaciones",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void borrarTabla() {
		//TODO: completar si es necesario
	}
}
