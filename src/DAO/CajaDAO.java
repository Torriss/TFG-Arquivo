package DAO;

import java.sql.SQLException;
import java.util.List;

import model.Caja;

public interface CajaDAO {
	
	boolean insert(Caja caja) throws SQLException, ClassNotFoundException;
	List<Caja> obtenerTodasCajas() throws SQLException, ClassNotFoundException;
	boolean update(Caja caja) throws SQLException, ClassNotFoundException;
    boolean delete(int idCaja) throws SQLException, ClassNotFoundException;
    Caja getById(int idCaja) throws SQLException, ClassNotFoundException;
    Caja buscarCajasParaExpedienteNuevo(int anio, String tipo, int paginasExpediente) throws SQLException, ClassNotFoundException;
    //String buscarNuevaUbicacionContigua(String ultimaUbicacion);
    //boolean insertarExpedienteEnCaja(Expediente expediente) throws SQLException;
    //List<Caja> obtenerCajasPorTipo(String tipo) throws SQLException;
    //Caja buscarCajaDisponible(String tipo, int anio, int numPaginasExpediente) throws SQLException;
}
