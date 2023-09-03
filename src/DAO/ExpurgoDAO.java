package DAO;

import java.util.List;

import model.Expediente;

public interface ExpurgoDAO {
	public List<Expediente> expurgo(String filePath);
}
