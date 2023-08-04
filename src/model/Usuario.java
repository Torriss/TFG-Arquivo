package model;

public class Usuario {
	private String nombre;
	private String password;
	private String rol;

	public Usuario(String nombre, String password, String rol) {
		this.nombre = nombre;
		this.password = password;
		this.rol = rol;
	}
	
	public static boolean altaUsuario(Usuario user) {
		
		//Construir la query
		String query = "INSERT INTO usuarios (nombre, password, rol) VALUES ('"+user.nombre+"','"+user.password+"','"+user.rol+"')";
		
		//Ejecutar la query en la BBDD
		return Conexion.execute(query);
	}
	
	
	public static boolean bajaUsuario(Usuario user) {
		return false;
	}
	
	public static boolean editUsuario(Usuario user, Usuario edit) {
		return false;
	}
}
