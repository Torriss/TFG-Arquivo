package DAO;

import java.sql.SQLException;
import java.util.List;

import model.Historico;

public interface HistoricoDAO {
	
	List<Historico> getAllRows(String tabla) throws ClassNotFoundException, SQLException;
	boolean insert(Historico fila, String tabla) throws SQLException, ClassNotFoundException;
	boolean existeHistorico(Historico fila, String tabla) throws SQLException, ClassNotFoundException;
}
