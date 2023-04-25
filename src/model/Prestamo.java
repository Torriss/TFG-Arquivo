package model;

public class Prestamo {
	private int numExpediente;
	private String tipo;
	private int año;
	private int caja;
	private String ubicacion;
	private String notas;
	private String tomos;
	private String juzgado;
	private String lugar;
	private String fechaPrestamo;
	private String fechaMaxDevolucion;
	
	public Prestamo(int numExpediente, String tipo, int año, int caja, String ubicacion, String notas, String tomos, 
					String juzgado, String lugar, String fechaPrestamo, String fechaMaxDevolucion) {
		this.numExpediente = numExpediente;
		this.tipo = tipo;
		this.caja = caja;
		this.año = año;
		this.ubicacion = ubicacion;
		this.notas = notas;
		this.tomos = tomos;
		this.juzgado = juzgado;
		this.lugar = lugar;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaMaxDevolucion = fechaMaxDevolucion;
	}
	
	public boolean realizarPrestamo() {
		return false;
	}
	
	public boolean realizarDevolucion() {
		return false;
	}
	
	
}
