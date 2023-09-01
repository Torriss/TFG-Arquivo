package model;

import java.sql.Date;

public class Expediente {
    private int numExpediente;
    private String tipo;
    private int anio;
    private int caja;
    private String ubicacion;
    private String notas;
    private String tomos;
    private String juzgado;
    private String lugar;
    private String solicitante;
    private Date fechaPrestamo;
    private int paginas;

    public Expediente(int numExpediente, String tipo, int anio, int caja, String ubicacion, String notas, 
    					String tomos, String juzgado, String lugar, int paginas, String solicitante, Date fechaPrestamo) {
        this.numExpediente = numExpediente;
        this.tipo = tipo;
        this.anio = anio;
        this.caja = caja;
        this.ubicacion = ubicacion;
        this.notas = notas;
        this.tomos = tomos;
        this.juzgado = juzgado;
        this.lugar = lugar;
        this.paginas = paginas;
        this.solicitante = solicitante;
        this.fechaPrestamo = fechaPrestamo;
    }

    public Expediente(String tipoExp, int numExp, int anioExp) {
        this.tipo = tipoExp;
        this.numExpediente = numExp;
        this.anio = anioExp;
	}

	public int getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(int numExpediente) {
        this.numExpediente = numExpediente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getCaja() {
        return caja;
    }

    public void setCaja(int caja) {
        this.caja = caja;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getTomos() {
        return tomos;
    }

    public void setTomos(String tomos) {
        this.tomos = tomos;
    }

    public String getJuzgado() {
        return juzgado;
    }

    public void setJuzgado(String juzgado) {
        this.juzgado = juzgado;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }
    
    public void sumarPaginas(int paginas) {
    	this.paginas =+ paginas;
    }
    
    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }
    
    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaActual) {
        this.fechaPrestamo = fechaActual;
    }

}