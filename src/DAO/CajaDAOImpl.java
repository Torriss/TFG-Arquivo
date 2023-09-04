package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public List<Caja> obtenerTodasCajas() throws SQLException, ClassNotFoundException{
        List<Caja> cajas = new ArrayList<>();
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
        List<Caja> cajasMismoTipoYAnio = obtenerCajasPorTipoYAnio(tipo, anio);
        //List<Caja> cajasDisponibles = new ArrayList<>();

        for (Caja caja : cajasMismoTipoYAnio) {
            if (caja.getPaginas() >= paginasExpediente) return caja;
        }

        String ultimaUbicacion = cajasMismoTipoYAnio.get(cajasMismoTipoYAnio.size() - 1).getUbicacion();
        String nuevaUbicacion = buscarNuevaUbicacionContigua(ultimaUbicacion);
        Caja nuevaCaja = new Caja(nuevaUbicacion, tipo, anio);
        this.insert(nuevaCaja);
        
        return nuevaCaja;
    }

    private List<Caja> obtenerCajasPorTipoYAnio(String tipo, int anio) throws SQLException, ClassNotFoundException{
        List<Caja> cajasMismoTipoYAnio = new ArrayList<>();
        String query = "SELECT * FROM cajas WHERE tipo = " + tipo + "AND anio = " + anio;
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
    
    private String buscarNuevaUbicacionContigua(String ultimaUbicacion) {
        char seccion = ultimaUbicacion.charAt(0);
        int estanteria = Integer.parseInt(ultimaUbicacion.substring(2, 4));
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
