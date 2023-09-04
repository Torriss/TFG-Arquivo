package DAO;

import java.sql.SQLException;
import java.util.List;

import model.Historico;

public interface HistoricoDAO {
	List<Historico> getAllRows(String tabla) throws ClassNotFoundException, SQLException;
}
