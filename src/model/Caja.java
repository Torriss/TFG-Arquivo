package model;

public class Caja {
	private static final int PAGINAS_FIJAS = 100; // Numero de paginas fijas que caben en cada caja

    private int idCaja;
    private int paginas;
    private String ubicacion;
    private String tipo;
    private int anio;

    public Caja(int paginas, String ubicacion, String tipo, int anio) {
        this.paginas = PAGINAS_FIJAS;
        this.tipo = tipo;
        this.anio = anio;
    }

    // Getters y setters

    public int getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(int idCaja) {
        this.idCaja = idCaja;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
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
    
    public void restarPaginas(int resta) {
    	this.paginas =- resta;
    }
    
    public void sumarPaginas(int suma) {
    	this.paginas =+ suma;
    }
    
    public String toString() {
        return "ID Caja: " + idCaja +
                ", Paginas: " + paginas +
                ", Ubicacion: " + ubicacion +
                ", Tipo: " + tipo +
                ", Anio: " + anio;
    }
   
}

