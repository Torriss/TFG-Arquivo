package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model.ExportarExpedientesExcel;
import model.ExportarHistoricoExcel;
import utils.FuncComunes;
import view.Exportar;

public class ControlExportar {
	
	private Exportar exportar;
	private FuncComunes func;
	
	public ControlExportar(Exportar ex) {
		exportar = ex;
		func = new FuncComunes();
		initView();
	}
	
	private void initView() {
		try {
			JComboBox<String> comboBoxJuzgado;
			comboBoxJuzgado = func.iniciarListaJuzgados(exportar.getComboBoxJuzgado());
			exportar.setComboBoxJuzgado(comboBoxJuzgado);
			JComboBox<String> comboBoxExpediente = func.iniciarListaTipoExp(exportar.getComboBoxExpediente());
			exportar.setComboBoxExpediente(comboBoxExpediente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initControl() {
		exportar.getBtnExportExpedientes().addActionListener(e -> exportarExpedientes());
		exportar.getBtnExportTransferencias().addActionListener(e -> exportarHistorico("historicaTransferencia"));
		exportar.getBtnExportExpurgos().addActionListener(e -> exportarHistorico("historicaExpurgo"));
	}
	
	private void exportarExpedientes() {
		try {
			int numExp = -1;
			int anio = -1;
			String tipo = exportar.getComboBoxExpediente().getSelectedItem().toString();
			String juzgado = exportar.getComboBoxJuzgado().getSelectedItem().toString();
			
			if(!exportar.getTextFieldNumExp().getText().contains(""))
			{
				numExp = Integer.parseInt(exportar.getTextFieldNumExp().getText());
			}
			if(!exportar.getTextFieldAnioExp().getText().contains(""))
			{
				anio = Integer.parseInt(exportar.getTextFieldAnioExp().getText());
			}
			String filePath = ExportarExpedientesExcel.exportarExpedientes(numExp, anio, tipo, juzgado);
			JOptionPane.showMessageDialog(null,
					"<html>Exportación realizada con éxito.<br>Nombre del archivo: " + filePath, "Exportar",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void exportarHistorico(String tabla) {
		try {
			int numExp = -1;
			int anio = -1;
			String tipo = exportar.getComboBoxExpediente().getSelectedItem().toString();
			String juzgado = exportar.getComboBoxJuzgado().getSelectedItem().toString();
			
			if(!exportar.getTextFieldNumExp().getText().contains(""))
			{
				numExp = Integer.parseInt(exportar.getTextFieldNumExp().getText());
			}
			if(!exportar.getTextFieldAnioExp().getText().contains(""))
			{
				anio = Integer.parseInt(exportar.getTextFieldAnioExp().getText());
			}
			
			//TODO: pasar argumentos cuando se cambie la funcion
			String filePath = ExportarHistoricoExcel.exportarHistorico(tabla);
			JOptionPane.showMessageDialog(null,
					"<html>Exportación realizada con éxito.<br>Ruta del archivo: " + filePath, "Exportar",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
