package DAO;

import java.io.IOException;
import java.sql.SQLException;

public interface TransferenciaDAO {
	public boolean transferirExpedientes(String filePath) throws ClassNotFoundException, SQLException, IOException;
}
