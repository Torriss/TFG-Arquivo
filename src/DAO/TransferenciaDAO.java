package DAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.Expediente;

public interface TransferenciaDAO {
	public List<Expediente> transferirExpedientes(String filePath) throws ClassNotFoundException, SQLException, IOException;
}
