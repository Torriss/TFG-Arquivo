package DAO;

import java.sql.SQLException;
import java.util.List;

import model.Caja;
import model.Expediente;

public interface CajaDAO {
	
	boolean insert(Caja caja) throws SQLException;
	List<Caja> obtenerTodasCajas() throws SQLException;
	boolean update(Caja caja) throws SQLException;
    boolean delete(int idCaja) throws SQLException;
    Caja getById(int idCaja) throws SQLException;
    List<Caja> buscarCajasParaExpedienteNuevo(int paginasExpediente) throws SQLException;
    String buscarNuevaUbicacionContigua(String ultimaUbicacion);
    //boolean insertarExpedienteEnCaja(Expediente expediente) throws SQLException;
    //List<Caja> obtenerCajasPorTipo(String tipo) throws SQLException;
    //Caja buscarCajaDisponible(String tipo, int anio, int numPaginasExpediente) throws SQLException;
}
