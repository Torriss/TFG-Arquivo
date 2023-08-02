package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Caja {
    private static final int PAGINAS = 100; // N�mero de p�ginas que caben en cada caja

    private int idCaja;
    private int paginas;
    private String ubicacion //con el formato B-19-4-0 Siendo la B la sección(rango A-Z), 19 el número de estantería donde esta(rango 0-20), 4 el número de balda(rango 0-5) y 0 la posición en la balda(rango 0-5).;
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

    // M�todos CRUD

    public static boolean crearCaja(Caja caja) {
        String query = "INSERT INTO cajas (paginas, ubicacion, tipo, anio) VALUES (" +
                caja.getPaginas() + ", '" +
                caja.getUbicacion() + "', '" +
                caja.getTipo() + "', " +
                caja.getAnio() + ")";
        return Conexion.execute(query);
    }

    /*public static boolean crearCaja(Caja caja) {
        // Validar caja no nula y otros valores si es necesario
        String query = "INSERT INTO cajas (paginas, ubicacion, tipo, anio) VALUES (?, ?, ?, ?)";
        return Conexion.executePreparedStatement(query, caja.getPaginas(), caja.getUbicacion(), caja.getTipo(), caja.getAnio());
    }*/

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

    /*public static boolean actualizarCaja(Caja caja) {
        // Validar caja no nula y otros valores si es necesario
        String query = "UPDATE cajas SET paginas = ?, ubicacion = ?, tipo = ?, anio = ? WHERE idCaja = ?";
        return Conexion.executePreparedStatement(query, caja.getPaginas(), caja.getUbicacion(), caja.getTipo(), caja.getAnio(), caja.getIdCaja());
    }*/

    public static boolean eliminarCaja(int idCaja) {
        String query = "DELETE FROM cajas WHERE idCaja = " + idCaja;
        return Conexion.execute(query);
    }


    public static Caja obtenerCajaPorID(int idCaja) {
        String query = "SELECT * FROM cajas WHERE idCaja = ?";
        ResultSet rs = Conexion.executePreparedStatement(query, idCaja);

        try {
            if (rs.next()) {
                int paginas = rs.getInt("paginas");
                String ubicacion = rs.getString("ubicacion");
                String tipo = rs.getString("tipo");
                int anio = rs.getInt("anio");
                Caja caja = new Caja(paginas, ubicacion, tipo, anio);
                caja.setIdCaja(idCaja);
                return caja;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public String toString() {
        return "ID Caja: " + idCaja +
                ", Páginas: " + paginas +
                ", Ubicación: " + ubicacion +
                ", Tipo: " + tipo +
                ", Año: " + anio;
    }

   private static Caja crearNuevaCaja(String tipo, int numPaginas) {
        // Obtener todas las cajas del mismo tipo con ubicaciones en el formato B-19-4-0
        List<Caja> cajasMismoTipo = Caja.obtenerCajasPorTipo(tipo);
        cajasMismoTipo.sort(Comparator.comparing(Caja::getUbicacion)); // Ordenar por ubicación

        // Calcular la ubicación de la nueva caja
        String ubicacionNuevaCaja = generarUbicacionNuevaCaja(cajasMismoTipo);

        // Crear la nueva caja y guardarla en la base de datos
        Caja nuevaCaja = new Caja(numPaginas, ubicacionNuevaCaja, tipo, obtenerAnioActual());
        if (Caja.crearCaja(nuevaCaja)) {
            return nuevaCaja;
        } else {
            // Error al crear la nueva caja
            return null;
        }
    }

    private static String generarUbicacionNuevaCaja(List<Caja> cajas) {
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

    public static boolean insertarExpedienteEnCaja(Expediente expediente) {
        Caja cajaDisponible = obtenerCajaDisponible(expediente.getTipo(), expediente.getNumPaginas());
        if (cajaDisponible != null) {
            // Si hay una caja disponible, insertamos el expediente en esa caja
            expediente.setCaja(cajaDisponible.getIdCaja());
            return Expediente.insertarExpediente(expediente);
        } else {
            // Si no hay caja disponible, creamos una nueva caja y luego insertamos el expediente en ella
            Caja nuevaCaja = crearNuevaCaja(expediente.getTipo(), expediente.getNumPaginas());
            if (nuevaCaja != null) {
                expediente.setCaja(nuevaCaja.getIdCaja());
                return Expediente.insertarExpediente(expediente);
            } else {
                // Error al crear una nueva caja
                return false;
            }
        }
    }

    private static Caja obtenerCajaDisponible(String tipo, int numPaginas) {
        // Obtener todas las cajas del mismo tipo
        List<Caja> cajasMismoTipo = Caja.obtenerCajasPorTipo(tipo);

        // Verificar si alguna caja tiene suficientes páginas disponibles para el expediente
        for (Caja caja : cajasMismoTipo) {
            if (caja.getPaginasDisponibles() >= numPaginas) {
                return caja;
            }
        }

        // Si no hay caja disponible del mismo tipo con suficientes páginas, retornar null
        return null;
    }    


    public static List<Caja> obtenerCajasPorTipo(String tipo) {
        List<Caja> cajasMismoTipo = new ArrayList<>();
        String query = "SELECT * FROM cajas WHERE tipo = ?";
        ResultSet rs = Conexion.executePreparedStatement(query, tipo);
        try {
            while (rs.next()) {
                int idCaja = rs.getInt("idCaja");
                int paginas = rs.getInt("paginas");
                String ubicacion = rs.getString("ubicacion");
                int anio = rs.getInt("anio");
                Caja caja = new Caja(paginas, ubicacion, tipo, anio);
                caja.setIdCaja(idCaja);
                cajasMismoTipo.add(caja);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cajasMismoTipo;
    }

    public int getPaginasDisponibles() {
        // Calcular y retornar las páginas disponibles en la caja
        return paginas - Expediente.calcularPaginasOcupadasEnCaja(idCaja);
    }
}

