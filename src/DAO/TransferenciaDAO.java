package DAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Expediente;

public interface TransferenciaDAO {
	public boolean transferirExpedientes(String filePath) throws ClassNotFoundException, SQLException, IOException;
}
