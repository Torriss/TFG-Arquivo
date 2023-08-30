package DAO;

import java.sql.SQLException;
import java.util.List;

import model.Expediente;

public interface PrestamoDAO {
	List<Expediente> realizarPrestamo(int numExp, String tipo, int anio, String solicitante, String juzgado) throws SQLException;
	boolean existePrestamo(int numExp, String tipo, int anio, String juzgado) throws SQLException;
	boolean eliminarPrestamo(int numExpediente, String tipo, int anio, String juzgado) throws SQLException;
}
