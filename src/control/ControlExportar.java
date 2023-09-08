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
			comboBoxJuzgado.insertItemAt("", 0);
			comboBoxJuzgado.setSelectedIndex(0);
			exportar.setComboBoxJuzgado(comboBoxJuzgado);
			JComboBox<String> comboBoxExpediente;
			comboBoxExpediente = func.iniciarListaTipoExp(exportar.getComboBoxExpediente());
			comboBoxExpediente.insertItemAt("", 0);
			comboBoxExpediente.setSelectedIndex(0);
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
		exportar.getBtnAyuda().addActionListener(e -> mostrarAyuda());
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

	private void mostrarAyuda() {
		//TODO: meter texto de ayuda
		String msg = "<html>Esto escribe una linea<br><br>"
				+ "Con cada br se añade un salto de linea<br><br>";

		JOptionPane.showMessageDialog(null,
				msg, "Ayuda",
				JOptionPane.QUESTION_MESSAGE);
	}
	
}
