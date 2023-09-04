package view;

import javax.swing.table.DefaultTableModel;

public class ModeloTabla extends DefaultTableModel{
	 
	 /**
	 * 
	 */
	private static final long serialVersionUID = 8451366906105239287L;
	private String[] titulos;
	private Object[][] datos;
	
	//
	// Determina el modelo con el que se va a construir la tabla
	// @param datos
	// @param titulos
	//
	public ModeloTabla(Object[][] d, String[] t) {
		super();
		titulos=t;
		datos=d;
		setDataVector(datos, titulos);
	}
	
	public ModeloTabla() {
		// TODO Auto-generated constructor stub
	}
}
