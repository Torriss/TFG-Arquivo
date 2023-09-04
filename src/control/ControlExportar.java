package control;

import java.io.IOException;
import java.sql.SQLException;

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
			ExportarExpedientesExcel.exportarExpedientes();
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
			ExportarHistoricoExcel.exportarHistorico(tabla);
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
