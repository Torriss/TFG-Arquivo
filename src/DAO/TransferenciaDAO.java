package DAO;

import java.util.List;

import model.Expediente;

public interface TransferenciaDAO {
	public List<Expediente> transferirExpedientes(String filePath);
}
