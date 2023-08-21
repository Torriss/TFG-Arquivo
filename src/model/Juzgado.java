package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Juzgado {
	
	private int id;
	private String nombre;

	public Juzgado() {}
	
	public Juzgado(int id, String nombre)
	{
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Juzgado> getJuzgados() throws SQLException{
		ArrayList<Juzgado> listaJuzgados = new ArrayList<>();
		String query = "SELECT * FROM juzgado";
		ResultSet rs = Conexion.executeSelect(query);
			
		while (rs.next()){
			Juzgado juzgado = new Juzgado();
			juzgado.setNombre(rs.getString("nombre"));
			listaJuzgados.add(juzgado);
		}
		return listaJuzgados;
	}

}
