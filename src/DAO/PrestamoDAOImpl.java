package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Conexion;
import model.Expediente;
import model.Prestamo;

public class PrestamoDAOImpl implements PrestamoDAO{
	
	@Override
	public List<Expediente> realizarPrestamo(int numExp, String tipo, int anio, int solicitante, String juzgado, String fechaPrestamo) throws SQLException, IllegalArgumentException, ClassNotFoundException{
		ExpedienteDAO exp = new ExpedienteDAOImpl();
		SolicitanteDAO sol = new SolicitanteDAOImpl();
		List<Expediente> expList = new ArrayList<>();
		
		//Comprobamos que existe expediente
		if (!exp.existeExpediente(numExp, tipo, anio, juzgado)) throw new IllegalArgumentException("No existe ese expediente en la BBDD");
	    //comprobar que expediente se puede prestar(su estado no es ni expurgado ni prestado)
		if(!expedienteDisponible(numExp, tipo, anio, juzgado)) throw new IllegalArgumentException("Este expediente se encuentra prestado o expurgado");
		//comprobamos que solicitante tiene permiso
		if(!sol.existeEmpleado(solicitante)) throw new IllegalArgumentException("Dicho empleado no puede solicitar el expediente");
		//Insertamos prestamo en la bbdd
		Prestamo prestamo = new Prestamo(numExp, tipo, anio, juzgado, fechaPrestamo, null, solicitante);
		this.insert(prestamo);
		//Actualizamos expedientes en bbdd
	    expList = exp.buscaExpediente(numExp, tipo, anio, juzgado);
	    for (Expediente expediente : expList) {
	    	//actualizamos estado
	    	expediente.setEstado("prestado");
	    	//actualizamos en bbdd
	    	exp.update(expediente);
	    }
	    
	    //Devolvemos lista expedientes a prestar (solo tendra una posicion en caso de no estar dividido entre varias cajas)
	    return expList;
	}
	
	@Override
	public boolean insert(Prestamo prest) throws SQLException, ClassNotFoundException{
        // Construir la query
        String query = "INSERT INTO prestamos (idSolicitante, numExpediente, tipo, anio, juzgado, fechaPrestamo, fechaDevolucion) VALUES ('"
                + prest.getSolicitante() + "','" + prest.getNumExpediente() + "','" + prest.getTipo() + "','"
        		+ prest.getAnio() + "','" + prest.getJuzgado() + "','" + prest.getFechaPrestamo() + "','"
        		+ prest.getFechaDevolucion() + "')";

        // Ejecutar la query en la BBDD
        return Conexion.execute(query);
    }
	
	@Override
	public boolean expedienteDisponible(int numExpediente, String tipo, int anio, String juzgado) throws SQLException, ClassNotFoundException{
	    String query = "SELECT DISTINCT * FROM expedientes WHERE numExpediente = " + numExpediente + " AND tipo = '" + tipo + "'" 
	    				+ " AND anio = " + anio + "" + " AND juzgado = " + juzgado + "";
	    ResultSet rs = Conexion.executeSelect(query);
	    
	    String estado = null;
	    if (rs.next()) {
	    	estado = rs.getString("estado");
	    }
	    if(estado == "expurgado" || estado == "prestado") return false;
	    else return true;
	}
//	
//	@Override
//	public boolean eliminarPrestamo(int numExpediente, String tipo, int anio, String tomos) throws SQLException{
//        String query = "DELETE FROM Prestamos WHERE numExpediente = " + numExpediente +
//                " AND tipo = '" + tipo + "'" +
//                " AND anio = " + anio +
//                " AND tomos = '" + tomos + "'";
//        return Conexion.execute(query);
//    }
}
