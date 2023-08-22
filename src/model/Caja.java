package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
    
    


    public List<Caja> buscarCajasParaExpedienteNuevo(int paginasExpediente) {
        List<Caja> cajasMismoTipoYAnio = obtenerCajasPorTipoYAnio(tipo, anio);
        List<Caja> cajasDisponibles = new ArrayList<>();

        for (Caja caja : cajasMismoTipoYAnio) {
            if (caja.getEspacioDisponible() >= paginasExpediente) {
                cajasDisponibles.add(caja);
            }
        }

        List<Caja> combinacionCajas = new ArrayList<>();
        int paginasRestantes = paginasExpediente;

        for (Caja caja : cajasDisponibles) {
            if (paginasRestantes <= 0) {
                break;
            }

            int paginasUsadas = Math.min(paginasRestantes, caja.getEspacioDisponible());
            combinacionCajas.add(caja);
            paginasRestantes -= paginasUsadas;
        }

        if (paginasRestantes <= 0) {
            return combinacionCajas;
        }

        // si no se encuentra una combinación de cajas chula, buscar una ubicación contigua y crear una nueva caja
        String ultimaUbicacion = cajasMismoTipoYAnio.get(cajasMismoTipoYAnio.size() - 1).getUbicacion();
        String nuevaUbicacion = buscarNuevaUbicacionContigua(ultimaUbicacion);
        //aqui hay que crear una nueva caja en la base de datos, que supongo que sera cn el DAO pero no estaba seguro y luego esa caja se añade debajo
        combinacionCajas.add(nuevaCaja);
        return combinacionCajas;
    }

    //este metodo tampoco estoy seguro de que tenga que estar aqui, no se si deberia estar en DAO
    private static List<Caja> obtenerCajasPorTipoYAnio(String tipo, int anio) {
        List<Caja> cajasMismoTipoYAnio = new ArrayList<>();
        String query = "SELECT * FROM cajas WHERE tipo = " + tipo + "AND anio = " + anio;
        ResultSet rs = Conexion.executeSelect(query);
        
        try {
            while (rs.next()) {
                int idCaja = rs.getInt("idCaja");
                int paginas = rs.getInt("paginas");
                String ubicacion = rs.getString("ubicacion");
                Caja caja = new Caja(paginas, ubicacion, tipo, anio);
                caja.setIdCaja(idCaja);
                cajasMismoTipoYAnio.add(caja);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return cajasMismoTipoYAnio;
    }
    
    public String buscarNuevaUbicacionContigua(String ultimaUbicacion) {
        char seccion = ultimaUbicacion.charAt(0);
        int estanteria = Integer.parseInt(ultimaUbicacion.substring(2, 4));
        int balda = Integer.parseInt(ultimaUbicacion.substring(5, 6));
        int posicion = Integer.parseInt(ultimaUbicacion.substring(8, 9));

        posicion++;
        // si se ha alcanzado el limite de posición en la balda, mover al siguiente nivel
        if (posicion > 5) {
            posicion = 0;
            balda++;
        }

        // si se ha alcanzado el limite de balda, mover a la siguiente estantería
        if (balda > 5) {
            balda = 0;
            estanteria++;
        }

        // si se ha alcanzado el limite de estantería, cambiar a la siguiente sección
        if (estanteria > 20) {
            estanteria = 0;
            seccion++;
        }
        return seccion + "-" + String.format("%02d", estanteria) + "-" + balda + "-" + posicion;
    }
    
    private int getEspacioDisponible() {
        return PAGINAS_FIJAS - paginas;
    }
    

}

