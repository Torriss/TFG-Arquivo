package view;

import javax.swing.table.DefaultTableModel;

public class ModeloTabla extends DefaultTableModel{

	private static final long serialVersionUID = 8451366906105239287L;
	private String[] titulos;
	private Object[][] datos;
	
	public ModeloTabla() {}

	public ModeloTabla(Object[][] d, String[] t) {
		super();
		titulos=t;
		datos=d;
		setDataVector(datos, titulos);
	}
	
	public ModeloTabla(Object[][] d, String[] t, boolean modificable) {
		super();
		titulos=t;
		datos=d;
		setDataVector(datos, titulos);
		
	}
	
	@Override
	public boolean isCellEditable (int row, int column)
	{
		boolean editable = false;
		if (row != 0 && column != 11){
			editable = true;
		}
		return editable;
	}
}
