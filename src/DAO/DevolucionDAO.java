package DAO;

import java.sql.SQLException;
import java.util.List;

import model.Caja;

public interface DevolucionDAO {
	
	List<Caja> buscarUbi(int numExp, int anio, String tipo, String juzgado) throws SQLException;
	void devolucion(int numExp, int anio, String tipo, String juzgado) throws SQLException;
	Caja nuevo(int numExp, int anio, String tipo, String juzgado) throws SQLException;
}
