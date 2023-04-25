package model;

public class Archivo {
	private int numExpediente;
	private String tipo;
	private int año;
	private int caja;
	private String ubicacion;
	private String notas;
	private String tomos;
	private String juzgado;
	private String lugar;
	
	public Archivo(int numExpediente, String tipo, int año, int caja, String ubicacion, String notas, String tomos, String juzgado, String lugar) {
		this.numExpediente = numExpediente;
		this.tipo = tipo;
		this.caja = caja;
		this.año = año;
		this.ubicacion = ubicacion;
		this.notas = notas;
		this.tomos = tomos;
		this.juzgado = juzgado;
		this.lugar = lugar;
	}
	
	public boolean añadirArchivo() {
		return false;
	}
	
	public boolean eliminarArchivo() {
		return false;
	}
	
	public boolean modificarArchivo() {
		return false;
	}
}
