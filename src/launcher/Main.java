package launcher;

import java.sql.SQLException;

import model.Expediente;

public class Main {

	public static void main(String[] args) throws SQLException {

		Expediente exp = Expediente.getByID(1);
		System.out.println(exp.getNumExpediente() + "  " + exp.getAnio());
	}
}
