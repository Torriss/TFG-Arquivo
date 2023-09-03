package model;

public class Prestamo {
	private int numExpediente;
	private String tipo;
	private int anio;
	private String juzgado;
	private String fechaPrestamo;
	private String fechaDevolucion;
	private int idSolicitante;
	
	public Prestamo(int numExpediente, String tipo, int anio, String juzgado, String fechaPrestamo, String fechaDevolucion, int idSolicitante) {
		this.numExpediente = numExpediente;
		this.tipo = tipo;
		this.anio = anio;
		this.juzgado = juzgado;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
		this.idSolicitante = idSolicitante;
	}
	
	public int getNumExpediente() {
        return numExpediente;
    }

    public String getTipo() {
        return tipo;
    }

    public int getAnio() {
        return anio;
    }

    public String getJuzgado() {
        return juzgado;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }
    
    public String getFechaDevolucion() {
        return fechaDevolucion;
    }
    
    public int getSolicitante() {
    	return idSolicitante;
    }
    
    public void setFechaDevolucion(String fecha) {
    	this.fechaDevolucion = fecha;
    }
	
}