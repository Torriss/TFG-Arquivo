package model;

public class Solicitante {
	
	private int idEmpleado;
	private String nombre;
	private String apellidos;
	private int edad;
	private String cargo;
	
	public Solicitante(String nombre, String apellidos, int edad, String cargo) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.cargo = cargo;
	}
	
	public int getIdEmpleado() {
        return idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}







}
