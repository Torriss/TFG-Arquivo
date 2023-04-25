package model;

public class Usuario {
	private int idUsuario;
	private String nombre;
	private String rol;
	
	public Usuario(int idUsuario, String nombre, String rol) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.rol = rol;
	}
	
	public boolean altaUsuario() {
		return false;
	}
	
	public boolean bajaUsuario() {
		return false;
	}
	
	public boolean editUsuario() {
		return false;
	}
}
