package DAO;

import java.sql.SQLException;
import java.util.List;

import model.Expediente;

public interface DevolucionDAO {
	
	//List<Expediente> buscarUbi(int numExp, int anio, String tipo, String juzgado) throws SQLException;
	List<Expediente> devolucion(int numExp, int anio, String tipo, String juzgado, String notas, int paginaNuevas, String fechaDevolucion) throws SQLException, ClassNotFoundException;
	Expediente nuevo(int numExp, int anio, String tipo, String juzgado, int paginas) throws SQLException, ClassNotFoundException;
}
