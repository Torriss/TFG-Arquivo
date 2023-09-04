package model;

public class Historico {

	private int numExpediente;
	private String tipo;
	private int anio;
	private String juzgado;
	private String fechaHito;
	
	public Historico(int numExpediente, String tipo, int anio, String juzgado, String fechaHito) {
		this.numExpediente = numExpediente;
		this.tipo = tipo;
		this.anio = anio;
		this.juzgado = juzgado;
		this.fechaHito = fechaHito;
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

    public String getFechaHito() {
        return fechaHito;
    }
}
