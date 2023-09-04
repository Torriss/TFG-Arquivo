package DAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.Expediente;

public interface ExpurgoDAO {
	public List<Expediente> expurgo(String filePath) throws IOException, ClassNotFoundException, SQLException;
}
