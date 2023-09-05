package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Caja;
import model.Conexion;

public class CajaDAOImpl implements CajaDAO {

	@Override
	public boolean insert(Caja caja) throws SQLException, ClassNotFoundException{
        String query = "INSERT INTO cajas (paginas, ubicacion, tipo, anio) VALUES (" +
                caja.getPaginas() + ", '" +
                caja.getUbicacion() + "', '" +
                caja.getTipo() + "', " +
                caja.getAnio() + ")";
        return Conexion.execute(query);
    }

	@Override
    public ArrayList<Caja> obtenerTodasCajas() throws SQLException, ClassNotFoundException{
        ArrayList<Caja> cajas = new ArrayList<Caja>();
        String query = "SELECT * FROM cajas";
        ResultSet rs = Conexion.executeSelect(query);
        
        while (rs.next()) {
        	int idCaja = rs.getInt("idCaja");
            int paginas = rs.getInt("paginas");
            String ubicacion = rs.getString("ubicacion");
            String tipo = rs.getString("tipo");
            int anio = rs.getInt("anio");
            Caja caja = new Caja(idCaja, paginas, ubicacion, tipo, anio);
            cajas.add(caja);
            
        }
        return cajas;
    }

	@Override
    public boolean update(Caja caja) throws SQLException, ClassNotFoundException{
        String query = "UPDATE cajas SET paginas = " + caja.getPaginas() +
                ", ubicacion = '" + caja.getUbicacion() +
                "', tipo = '" + caja.getTipo() +
                "', anio = " + caja.getAnio() +
                " WHERE idCaja = " + caja.getIdCaja();
        return Conexion.execute(query);
    }

	@Override
    public boolean delete(int idCaja) throws SQLException, ClassNotFoundException{
        String query = "DELETE FROM cajas WHERE idCaja = " + idCaja;
        return Conexion.execute(query);
    }
    
	@Override
    public Caja getById(int idCaja) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM cajas WHERE idCaja = " + idCaja;
        ResultSet rs = Conexion.executeSelect(query);

        if (rs.next()) {
            int paginas = rs.getInt("paginas");
            String ubicacion = rs.getString("ubicacion");
            String tipo = rs.getString("tipo");
            int anio = rs.getInt("anio");

            Caja caja = new Caja(idCaja, paginas, ubicacion, tipo, anio);
            caja.setIdCaja(idCaja);

            return caja;
        }

        return null;
	}

    
    public Caja buscarCajasParaExpedienteNuevo(int anio, String tipo, int paginasExpediente) throws SQLException, ClassNotFoundException{
        ArrayList<Caja> cajasMismoTipoYAnio = obtenerCajasPorTipoYAnio(tipo, anio);
        Caja nuevaCaja;
        if(!cajasMismoTipoYAnio.isEmpty()) {
        	for (Caja caja : cajasMismoTipoYAnio) {
                if (caja.getPaginas() >= paginasExpediente) return caja;
            }
        	String ultimaUbicacion = cajasMismoTipoYAnio.get(cajasMismoTipoYAnio.size() - 1).getUbicacion();
            String nuevaUbicacion = buscarNuevaUbicacionContigua(ultimaUbicacion);
            nuevaCaja = new Caja(nuevaUbicacion, tipo, anio);
            this.insert(nuevaCaja);
        }
        else {
        	ArrayList<Caja> cajasMismoTipo = obtenerCajasPorTipo(tipo);
        	if(!cajasMismoTipo.isEmpty()) {
            	for (Caja caja : cajasMismoTipo) {
                    if (caja.getPaginas() >= paginasExpediente) return caja;
                }
            	String ultimaUbicacion = cajasMismoTipo.get(cajasMismoTipo.size() - 1).getUbicacion();
                String nuevaUbicacion = buscarNuevaUbicacionContigua(ultimaUbicacion);
                nuevaCaja = new Caja(nuevaUbicacion, tipo, anio);
                this.insert(nuevaCaja);
            }
            else {
            	ArrayList<Caja> cajasMismoAnio = obtenerCajasPorAnio(anio);
                if(!cajasMismoAnio.isEmpty()) {
                	for (Caja caja : cajasMismoAnio) {
                        if (caja.getPaginas() >= paginasExpediente) return caja;
                    }
                	String ultimaUbicacion = cajasMismoAnio.get(cajasMismoAnio.size() - 1).getUbicacion();
                    String nuevaUbicacion = buscarNuevaUbicacionContigua(ultimaUbicacion);
                    nuevaCaja = new Caja(nuevaUbicacion, tipo, anio);
                    this.insert(nuevaCaja);
                }
            }
        }
        String ultimaUbi = ultimaUbicacion();
        nuevaCaja = new Caja(buscarNuevaUbicacionContigua(ultimaUbi), tipo, anio);
        
        return nuevaCaja;
    }
    
    private String ultimaUbicacion() throws SQLException, ClassNotFoundException {
    	String query = "SELECT * FROM cajas ORDER BY ubicacion asc";
        ResultSet rs = Conexion.executeSelect(query);
        String ult = "";
        
        if (rs.next()) ult = rs.getString("ubicacion");
            
        return ult;
    }

    private ArrayList<Caja> obtenerCajasPorTipoYAnio(String tipo, int anio) throws SQLException, ClassNotFoundException{
        ArrayList<Caja> cajasMismoTipoYAnio = new ArrayList<>();
        String query = "SELECT * FROM cajas WHERE tipo = '" + tipo + "' AND anio = " + anio;
        ResultSet rs = Conexion.executeSelect(query);
        
        
        while (rs.next()) {
        	int idCaja = rs.getInt("idCaja");
            int paginas = rs.getInt("paginas");
            String ubicacion = rs.getString("ubicacion");
            Caja caja = new Caja(idCaja, paginas, ubicacion, tipo, anio);
            cajasMismoTipoYAnio.add(caja);
        }
        
        return cajasMismoTipoYAnio;
    }
    
    private ArrayList<Caja> obtenerCajasPorTipo(String tipo) throws SQLException, ClassNotFoundException{
        ArrayList<Caja> cajasMismoTipo = new ArrayList<>();
        String query = "SELECT * FROM cajas WHERE tipo = '" + tipo + "'";
        ResultSet rs = Conexion.executeSelect(query);
        
        
        while (rs.next()) {
        	int idCaja = rs.getInt("idCaja");
            int paginas = rs.getInt("paginas");
            String ubicacion = rs.getString("ubicacion");
            int anio = rs.getInt("anio");
            Caja caja = new Caja(idCaja, paginas, ubicacion, tipo, anio);
            cajasMismoTipo.add(caja);
        }
        
        return cajasMismoTipo;
    }
    
    private ArrayList<Caja> obtenerCajasPorAnio(int anio) throws SQLException, ClassNotFoundException{
        ArrayList<Caja> cajasMismoAnio = new ArrayList<>();
        String query = "SELECT * FROM cajas WHERE anio = " + anio; 
        ResultSet rs = Conexion.executeSelect(query);
        
        
        while (rs.next()) {
        	int idCaja = rs.getInt("idCaja");
            int paginas = rs.getInt("paginas");
            String ubicacion = rs.getString("ubicacion");
            String tipo = rs.getString("tipo");
            Caja caja = new Caja(idCaja, paginas, ubicacion, tipo, anio);
            cajasMismoAnio.add(caja);
        }
        
        return cajasMismoAnio;
    }
    
    private String buscarNuevaUbicacionContigua(String ultimaUbicacion) {
        char seccion = ultimaUbicacion.charAt(0);
        int estanteria = Integer.parseInt(ultimaUbicacion.substring(2, 3));
        int balda = Integer.parseInt(ultimaUbicacion.substring(5, 6));
        int posicion = Integer.parseInt(ultimaUbicacion.substring(8, 9));

        posicion++;
        // si se ha alcanzado el limite de posicion en la balda, mover al siguiente nivel
        if (posicion > 5) {
            posicion = 0;
            balda++;
        }

        // si se ha alcanzado el limite de balda, mover a la siguiente estanteria
        if (balda > 5) {
            balda = 0;
            estanteria++;
        }

        // si se ha alcanzado el limite de estanteria, cambiar a la siguiente seccion
        if (estanteria > 20) {
            estanteria = 0;
            seccion++;
        }
        return seccion + "-" + String.format("%02d", estanteria) + "-" + balda + "-" + posicion;
    }


}
