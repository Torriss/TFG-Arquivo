package view;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class GestionCeldas extends DefaultTableCellRenderer{

	private static final long serialVersionUID = -483388843413858079L;
	private JCheckBox checkbox;
	
	public JCheckBox getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(JCheckBox checkbox) {
		this.checkbox = checkbox;
	}

	public GestionCeldas(){	}

	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
		
		if(String.valueOf(value).equals("checkbox") )
        {
			checkbox = new JCheckBox();
			checkbox.setHorizontalAlignment(JCheckBox.CENTER);
        	return checkbox;
        }
		
		return this;
	}
}
