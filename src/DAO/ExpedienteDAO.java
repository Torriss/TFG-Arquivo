package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Expediente;

public interface ExpedienteDAO {
	
	boolean insert(Expediente exp) throws SQLException;
    //Expediente getByID(int id);
    boolean update(Expediente exp) throws SQLException;
    boolean delete(int numExpediente) throws SQLException;
    List<Expediente> buscaExpediente(int numExpe, String type, int year, String judge) throws SQLException;
    //Expediente buscaExpedientesTomos(int numExpe, String tipe, int year, String tomos);
    boolean existeExpediente(int numExpediente, String tipo, int anio, String juzgado) throws SQLException;
    List<Expediente> getAllExpedientes() throws SQLException;
    ArrayList<String> getAllTiposExp() throws SQLException;
    //String getUbicacionExp(Expediente exp);
	String getUbicacionExp(String tipoExp, int numExp, int anioExp, String juzgado) throws SQLException;

}
