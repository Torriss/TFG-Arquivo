package model;

public class Archivo {
	private int numExpediente;
	private String tipo;
	private int a�o;
	private int caja;
	private String ubicacion;
	private String notas;
	private String tomos;
	private String juzgado;
	private String lugar;
	
	public Archivo(int numExpediente, String tipo, int a�o, int caja, String ubicacion, String notas, String tomos, String juzgado, String lugar) {
		this.numExpediente = numExpediente;
		this.tipo = tipo;
		this.caja = caja;
		this.a�o = a�o;
		this.ubicacion = ubicacion;
		this.notas = notas;
		this.tomos = tomos;
		this.juzgado = juzgado;
		this.lugar = lugar;
	}
	
	public boolean a�adirArchivo() {
		return false;
	}
	
	public boolean eliminarArchivo() {
		return false;
	}
	
	public boolean modificarArchivo() {
		return false;
	}
}
