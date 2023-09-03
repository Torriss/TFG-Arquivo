package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Expediente;

public interface ExpedienteDAO {
	
	boolean insert(Expediente exp) throws SQLException, ClassNotFoundException;
    //Expediente getByID(int id);
    boolean update(Expediente exp) throws SQLException, ClassNotFoundException;
    boolean delete(int numExpediente) throws SQLException, ClassNotFoundException;
    List<Expediente> buscaExpediente(int numExpe, String type, int year, String judge) throws SQLException, ClassNotFoundException;
    //Expediente buscaExpedientesTomos(int numExpe, String tipe, int year, String tomos);
    boolean existeExpediente(int numExpediente, String tipo, int anio, String juzgado) throws SQLException, ClassNotFoundException;
    List<Expediente> getAllExpedientes() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllTiposExp() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllJuzgados() throws SQLException, ClassNotFoundException;
    //String getUbicacionExp(Expediente exp);
	String getUbicacionExp(String tipoExp, int numExp, int anioExp, String juzgado) throws SQLException, ClassNotFoundException;

}
