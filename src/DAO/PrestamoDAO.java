package DAO;

import java.sql.SQLException;
import java.util.List;

import model.Expediente;
import model.Prestamo;

public interface PrestamoDAO {
	List<Expediente> realizarPrestamo(int numExp, String tipo, int anio, int solicitante, String juzgado, String fechaPrestamo) throws SQLException, IllegalArgumentException, ClassNotFoundException;
	boolean expedienteDisponible(int numExp, String tipo, int anio, String juzgado) throws SQLException, ClassNotFoundException;
	boolean insert(Prestamo prest) throws SQLException, ClassNotFoundException;
	//boolean eliminarPrestamo(int numExpediente, String tipo, int anio, String juzgado) throws SQLException;
}
