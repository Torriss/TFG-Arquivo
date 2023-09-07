package DAO;

import java.io.IOException;
import java.sql.SQLException;

public interface ExpurgoDAO {
	public boolean expurgo(String filePath) throws IOException, ClassNotFoundException, SQLException;
}
