package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Caja;

public interface CajaDAO {
	
	boolean insert(Caja caja) throws SQLException, ClassNotFoundException;
	ArrayList<Caja> obtenerTodasCajas() throws SQLException, ClassNotFoundException;
	boolean update(Caja caja) throws SQLException, ClassNotFoundException;
    boolean delete(int idCaja) throws SQLException, ClassNotFoundException;
    Caja getById(int idCaja) throws SQLException, ClassNotFoundException;
    Caja buscarCajasParaExpedienteNuevo(int anio, String tipo, int paginasExpediente) throws SQLException, ClassNotFoundException;
}
