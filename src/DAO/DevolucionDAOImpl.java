package DAO;

import java.sql.SQLException;
import java.util.List;

import model.Caja;
import model.Expediente;

public class DevolucionDAOImpl implements DevolucionDAO {
	
	@Override
	public List<Expediente> devolucion(int numExp, int anio, String tipo, String juzgado, int paginasNuevas) throws SQLException {
		ExpedienteDAO exp = new ExpedienteDAOImpl();
		CajaDAO caja = new CajaDAOImpl();
		List<Expediente> expList = exp.buscaExpediente(numExp, tipo, anio, juzgado);
		
		int paginasTotales = 0; //Sumamos paginas del expediente para saber si ha aumentado de tama�o
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
	
	@Override
	public Expediente nuevo(int numExp, int anio, String tipo, String juzgado, int paginas) throws SQLException {
		CajaDAO caja = new CajaDAOImpl();
		
		//buscar caja nuevi expediente
		List<Caja> cajas = caja.buscarCajasParaExpedienteNuevo(anio, tipo, paginas);
		//asignar nueva caja al expediente
		Expediente expediente = new Expediente(tipo, numExp, anio);
		//restar paginas de caja
		//actualizar bd
		return expediente;
	}

}
