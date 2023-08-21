package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import model.Caja;
import model.Conexion;
import model.Expediente;

public class CajaDAOImpl implements CajaDAO {

	@Override
	public boolean insert(Caja caja) throws SQLException{
        String query = "INSERT INTO cajas (paginas, ubicacion, tipo, anio) VALUES (" +
                caja.getPaginas() + ", '" +
                caja.getUbicacion() + "', '" +
                caja.getTipo() + "', " +
                caja.getAnio() + ")";
        return Conexion.execute(query);
    }

	@Override
    public List<Caja> obtenerTodasCajas() throws SQLException{
        List<Caja> cajas = new ArrayList<>();
        String query = "SELECT * FROM cajas";
        ResultSet rs = Conexion.executeSelect(query);
        
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
        return cajas;
    }

	@Override
    public boolean update(Caja caja) throws SQLException{
        String query = "UPDATE cajas SET paginas = " + caja.getPaginas() +
                ", ubicacion = '" + caja.getUbicacion() +
                "', tipo = '" + caja.getTipo() +
                "', anio = " + caja.getAnio() +
                " WHERE idCaja = " + caja.getIdCaja();
        return Conexion.execute(query);
    }

	@Override
    public boolean delete(int idCaja) throws SQLException{
        String query = "DELETE FROM cajas WHERE idCaja = " + idCaja;
        return Conexion.execute(query);
    }
    
	@Override
    public Caja getById(int idCaja) throws SQLException {
        String query = "SELECT * FROM cajas WHERE idCaja = " + idCaja;
        ResultSet rs = Conexion.executeSelect(query);

        if (rs.next()) {
            int paginas = rs.getInt("paginas");
            String ubicacion = rs.getString("ubicacion");
            String tipo = rs.getString("tipo");
            int anio = rs.getInt("anio");

            Caja caja = new Caja(paginas, ubicacion, tipo, anio);
            caja.setIdCaja(idCaja);

            return caja;
        }

        return null; // Retorna null si no se encuentra la caja con el ID especificado
    }

   private Caja crearNuevaCaja(String tipo, int numPaginas, int anio) throws SQLException{
        // Obtener todas las cajas del mismo tipo con ubicaciones en el formato B-19-4-0
        List<Caja> cajasMismoTipo = obtenerCajasPorTipo(tipo);
        cajasMismoTipo.sort(Comparator.comparing(Caja::getUbicacion)); // Ordenar por ubicación

        // Calcular la ubicación de la nueva caja
        String ubicacionNuevaCaja = generarUbicacionNuevaCaja(cajasMismoTipo);

        // Crear la nueva caja y guardarla en la base de datos
        Caja nuevaCaja = new Caja(numPaginas, ubicacionNuevaCaja, tipo, anio);
        if (insert(nuevaCaja)) {
            return nuevaCaja;
        } else {
            // Error al crear la nueva caja
            return null;
        }
    }

    private String generarUbicacionNuevaCaja(List<Caja> cajas) {
        // Recorrer la lista de cajas y encontrar la última ubicación en el formato B-19-4-0
        String ultimaUbicacion = "A-0-0-0"; // Supongamos que la primera ubicación posible es A-0-0-0

        for (Caja caja : cajas) {
            String ubicacion = caja.getUbicacion();
            if (ubicacion.matches("[A-Z]-\\d{1,2}-\\d-\\d")) { // Verificar el formato B-19-4-0
                ultimaUbicacion = ubicacion;
            }
        }

        // Extraer los elementos de la última ubicación (sección, estantería, balda y posición)
        String[] elementosUbicacion = ultimaUbicacion.split("-");
        char seccion = elementosUbicacion[0].charAt(0);
        int estanteria = Integer.parseInt(elementosUbicacion[1]);
        int balda = Integer.parseInt(elementosUbicacion[2]);
        int posicion = Integer.parseInt(elementosUbicacion[3]);

        // Generar la nueva ubicación justo después de la última
        if (posicion < 5) {
            posicion++;
        } else {
            if (balda < 5) {
                balda++;
                posicion = 0;
            } else {
                estanteria++;
                balda = 0;
                posicion = 0;
            }
        }

        return String.format("%c-%02d-%d-%d", seccion, estanteria, balda, posicion);
    }

    @Override
    public boolean insertarExpedienteEnCaja(Expediente expediente) throws SQLException {
    	ExpedienteDAO exp = new ExpedienteDAOImpl();
    	
        Caja cajaDisponible = obtenerCajaDisponible(expediente.getTipo(), expediente.getPaginas());
        if (cajaDisponible != null) {
            // Si hay una caja disponible, insertamos el expediente en esa caja
            expediente.setCaja(cajaDisponible.getIdCaja());
            return exp.insert(expediente);
        } else {
            // Si no hay caja disponible, creamos una nueva caja y luego insertamos el expediente en ella
            Caja nuevaCaja = crearNuevaCaja(expediente.getTipo(), expediente.getPaginas(), expediente.getAnio());
            if (nuevaCaja != null) {
                expediente.setCaja(nuevaCaja.getIdCaja());
                return exp.insert(expediente);
            } else {
                // Error al crear una nueva caja
                return false;
            }
        }
    }

    private Caja obtenerCajaDisponible(String tipo, int numPaginas) throws SQLException{
        // Obtener todas las cajas del mismo tipo
        List<Caja> cajasMismoTipo = obtenerCajasPorTipo(tipo);

        // Verificar si alguna caja tiene suficientes páginas disponibles para el expediente
        for (Caja caja : cajasMismoTipo) {
            if (caja.getPaginas() >= numPaginas) return caja;
        // Si no hay caja disponible del mismo tipo con suficientes paginas, retornar null
        }
        return null;
    }    

    @Override
    public List<Caja> obtenerCajasPorTipo(String tipo) throws SQLException{
        List<Caja> cajasMismoTipo = new ArrayList<>();
        String query = "SELECT * FROM cajas WHERE tipo = "+ tipo;
        ResultSet rs = Conexion.executeSelect(query);
        
        while (rs.next()) {
        	int idCaja = rs.getInt("idCaja");
            int paginas = rs.getInt("paginas");
            String ubicacion = rs.getString("ubicacion");
            int anio = rs.getInt("anio");
            Caja caja = new Caja(paginas, ubicacion, tipo, anio);
            caja.setIdCaja(idCaja);
            cajasMismoTipo.add(caja);
        }
        return cajasMismoTipo;
    }

    @Override
    public Caja buscarCajaDisponible(String tipo, int anio, int numPaginasExpediente) throws SQLException {
        List<Caja> cajasDisponibles = new ArrayList<>();
        // Obtener todas las cajas del mismo tipo y anio
        List<Caja> cajasMismoTipoYAnio = obtenerCajasPorTipoYAnio(tipo, anio);
        
        // Filtrar las cajas que tienen suficientes paginas disponibles para el expediente
        for (Caja caja : cajasMismoTipoYAnio) {
            if (caja.getPaginas() >= numPaginasExpediente) cajasDisponibles.add(caja);
        }
        // Si se encuentra una caja adecuada, devolverla
        if (!cajasDisponibles.isEmpty()) {
            // Ordenar las cajas disponibles por cantidad de paginas (ascendente)
            cajasDisponibles.sort(Comparator.comparingInt(Caja::getPaginas));
            return cajasDisponibles.get(0); // Devolver la primera caja con suficiente espacio
        }

        return null; // No se encuentra una caja adecuada
    }

    private List<Caja> obtenerCajasPorTipoYAnio(String tipo, int anio) throws SQLException{
        List<Caja> cajasMismoTipoYAnio = new ArrayList<>();
        String query = "SELECT * FROM cajas WHERE tipo = " + tipo + "AND anio = " + anio;
        ResultSet rs = Conexion.executeSelect(query);
        
        while (rs.next()) {
        	int idCaja = rs.getInt("idCaja");
        	int paginas = rs.getInt("paginas");
        	String ubicacion = rs.getString("ubicacion");
        	Caja caja = new Caja(paginas, ubicacion, tipo, anio);
        	caja.setIdCaja(idCaja);
        	cajasMismoTipoYAnio.add(caja);
        }
        
        return cajasMismoTipoYAnio;
    }

}
