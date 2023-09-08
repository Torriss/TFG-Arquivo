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
		//TODO: dejar posibles valores nulos de juzgado y tipo
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
			
			String numExpText = exportar.getTextFieldNumExp().getText();
	        String anioText = exportar.getTextFieldAnioExp().getText();

	        if (numExpText != null && !numExpText.isEmpty()) {
	            numExp = Integer.parseInt(numExpText);
	        }
	        if (anioText != null && !anioText.isEmpty()) {
	            anio = Integer.parseInt(anioText);
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
			
			String numExpText = exportar.getTextFieldNumExp().getText();
	        String anioText = exportar.getTextFieldAnioExp().getText();

	        if (numExpText != null && !numExpText.isEmpty()) {
	            numExp = Integer.parseInt(numExpText);
	        }
	        if (anioText != null && !anioText.isEmpty()) {
	            anio = Integer.parseInt(anioText);
	        }
			
			//TODO: pasar argumentos cuando se cambie la funcion
			String filePath = ExportarHistoricoExcel.exportarHistorico(numExp, anio, tipo, juzgado, tabla);
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
