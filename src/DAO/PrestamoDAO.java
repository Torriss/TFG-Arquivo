package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Expediente;
import model.Prestamo;

public interface PrestamoDAO {
	ArrayList<Expediente> realizarPrestamo(int numExp, String tipo, int anio, int solicitante, String juzgado, String fechaPrestamo) throws SQLException, IllegalArgumentException, ClassNotFoundException;
	boolean expedienteDisponible(int numExp, String tipo, int anio, String juzgado) throws SQLException, ClassNotFoundException;
	boolean insert(Prestamo prest) throws SQLException, ClassNotFoundException;
	Prestamo existePrestamoSinDevolver(int numExpediente, String tipo, int anio, String juzgado) throws ClassNotFoundException, SQLException;
	boolean update(Prestamo prest) throws SQLException, ClassNotFoundException;
}
