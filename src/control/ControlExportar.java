package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.ExportarExpedientesExcel;
import model.ExportarHistoricoExcel;
import view.Exportar;

public class ControlExportar {
	
	private Exportar exportar;
	
	public ControlExportar(Exportar ex) {
		exportar = ex;
	}
	
	public void initControl() {
		exportar.getBtnExportExpedientes().addActionListener(e -> exportarExpedientes());
		exportar.getBtnExportTransferencias().addActionListener(e -> exportarHistorico("historicaTransferencia"));
		exportar.getBtnExportExpurgos().addActionListener(e -> exportarHistorico("historicaExpurgo"));
	}
	
	private void exportarExpedientes() {
		try {
			//TODO: meter argumentos para exportar expediente: int numEXp, int anio, String tipo, String juzgado
			String filePath = ExportarExpedientesExcel.exportarExpedientes();
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
