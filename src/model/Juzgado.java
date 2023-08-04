package model;

public class Juzgado {
	
	private int _id;
	private String _nombre;

	public Juzgado() {}
	
	public Juzgado(int id, String nombre)
	{
		_id = id;
		_nombre = nombre;
	}

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		_id = id;
	}

	public String getNombre() {
		return _nombre;
	}

	public void setNombre(String nombre) {
		_nombre = nombre;
	}


}
