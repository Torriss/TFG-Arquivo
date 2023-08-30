package DAO;

import java.sql.SQLException;
import java.util.List;

import model.Caja;
import model.Expediente;
import model.Prestamo;

public class DevolucionDAOImpl implements DevolucionDAO {
	
	@Override
	public List<Expediente> devolucion(int numExp, int anio, String tipo, String juzgado, int paginasNuevas) throws SQLException {
		ExpedienteDAO exp = new ExpedienteDAOImpl();
		CajaDAO caja = new CajaDAOImpl();
		List<Expediente> expList = exp.buscaExpediente(numExp, tipo, anio, juzgado);
		
		int paginasTotales = 0; //Sumamos paginas del expediente para saber si ha aumentado de tamaño
		for(Expediente expediente : expList) paginasTotales =+ expediente.getPaginas();
		if(paginasNuevas > paginasTotales) {
			Caja cajaOriginal = caja.getById(expList.get(expList.size() - 1).getCaja());
			//si la caja del ultimo tomo tiene espacio suficiente para las nuevas paginas, se mantiene todo, sino se reubican todas las cajas
			if(cajaOriginal.getPaginas() < (paginasNuevas - paginasTotales)) {
				//actulizamos paginas del ultimo tomo
				expList.get(expList.size() - 1).sumarPaginas(paginasNuevas - paginasTotales); 
				for (Expediente expediente : expList) {
					//buscamos nueva caja para expediente
					//liberamos espacio de antigua caja
					//lo restamos de la nueva caja, se hace en funcion para buscar caja???
					List<Caja> cajas = caja.buscarCajasParaExpedienteNuevo(expediente.getAnio(), expediente.getTipo(), expediente.getPaginas());
				}
			}
			//caben los tomos en sus antiguas cajas
			else {
				//sumar paginas a ultimo tomo a pesar de que quepa y restar de caja
			}
			//hacemos update en la bbdd
		}
		return expList;
	}
	
	//ojito con valor de retorno, podria ser una lista
	@Override
	public Expediente nuevo(int numExp, int anio, String tipo, String juzgado) throws SQLException {
		//Si el prestamo no existe, buscar una ubicacion disponible para el expediente, pues se trata de un alta
		Caja.insertarExpedienteEnCaja(expediente);
		//Prestamo.eliminarPrestamo(expediente.getNumExpediente(), expediente.getTipo(), expediente.getAnio(), expediente.getTomos());
		return Caja.getById(expediente.getCaja());
	}

}
