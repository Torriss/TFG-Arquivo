package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Expediente;

public interface ExpedienteDAO {
	
	boolean insert(Expediente exp) throws SQLException, ClassNotFoundException;
    boolean update(Expediente exp) throws SQLException, ClassNotFoundException;
    public boolean updateListas(ArrayList<Expediente> viejos, ArrayList<Expediente> nuevos) throws SQLException, ClassNotFoundException;
    boolean delete(ArrayList<Expediente> expedientes) throws SQLException, ClassNotFoundException;
    ArrayList<Expediente> buscaExpediente(int numExpe, String type, int year, String judge) throws SQLException, ClassNotFoundException;
    boolean existeExpediente(int numExpediente, String tipo, int anio, String juzgado) throws SQLException, ClassNotFoundException;
    ArrayList<Expediente> getAllExpedientes() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllTiposExp() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllJuzgados() throws SQLException, ClassNotFoundException;
	String getUbicacionExp(String tipoExp, int numExp, int anioExp, String juzgado) throws SQLException, ClassNotFoundException;
	ArrayList<Expediente> aplicaFiltrosExpediente(int numExp, String tipo, int anio, String juzgado) throws SQLException, ClassNotFoundException;
	boolean obtenerExpedientesPorIdCaja(int idCaja) throws SQLException, ClassNotFoundException;
	Expediente buscaExpedienteTomos(int numExpe, String type, int year, String judge, String tomos) throws SQLException, ClassNotFoundException;
	boolean existeExpedienteTomos(int numExpediente, String tipo, int anio, String juzgado, String tomos) throws SQLException, ClassNotFoundException;
}
