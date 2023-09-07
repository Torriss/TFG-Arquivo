package DAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Expediente;

public interface ExpurgoDAO {
	public boolean expurgo(String filePath) throws IOException, ClassNotFoundException, SQLException;
}
