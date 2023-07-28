package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Caja {
    private static final int PAGINAS = 100; // Número de páginas que caben en cada caja

    private int idCaja;
    private int paginas;
    private String ubicacion;
    private String tipo;
    private int anio;

    public Caja(int paginas, String ubicacion, String tipo, int anio) {
        this.paginas = PAGINAS;
        this.ubicacion = ubicacion;
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

    // Métodos CRUD

    public static boolean crearCaja(Caja caja) {
        String query = "INSERT INTO cajas (paginas, ubicacion, tipo, anio) VALUES (" +
                caja.getPaginas() + ", '" +
                caja.getUbicacion() + "', '" +
                caja.getTipo() + "', " +
                caja.getAnio() + ")";
        return Conexion.execute(query);
    }

    public static List<Caja> obtenerTodasCajas() {
        List<Caja> cajas = new ArrayList<>();
        String query = "SELECT * FROM cajas";
        ResultSet rs = Conexion.executeSelect(query);
        try {
            while (rs.next()) {
                int idCaja = rs.getInt("idCaja");
                int paginas = rs.getInt("paginas");
                String ubicacion = rs.getString("ubicacion");
                String tipo = rs.getString("tipo");
                int anio = rs.getInt("anio");
                Caja caja = new Caja(paginas, ubicacion, tipo, anio);
                caja.setIdCaja(idCaja);
                cajas.add(caja);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cajas;
    }

    public static boolean actualizarCaja(Caja caja) {
        String query = "UPDATE cajas SET paginas = " + caja.getPaginas() +
                ", ubicacion = '" + caja.getUbicacion() +
                "', tipo = '" + caja.getTipo() +
                "', anio = " + caja.getAnio() +
                " WHERE idCaja = " + caja.getIdCaja();
        return Conexion.execute(query);
    }

    public static boolean eliminarCaja(int idCaja) {
        String query = "DELETE FROM cajas WHERE idCaja = " + idCaja;
        return Conexion.execute(query);
    }

    // Resto de métodos (omitiendo para abreviar)
}

