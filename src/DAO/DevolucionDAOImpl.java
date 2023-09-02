package DAO;

import java.sql.SQLException;
import java.util.List;

import model.Caja;
import model.Expediente;

public class DevolucionDAOImpl implements DevolucionDAO {
	
	@Override
	public List<Expediente> devolucion(int numExp, int anio, String tipo, String juzgado, String notas, int paginasNuevas) throws SQLException {
		ExpedienteDAO exp = new ExpedienteDAOImpl();
		CajaDAO caja = new CajaDAOImpl();
		List<Expediente> expList = exp.buscaExpediente(numExp, tipo, anio, juzgado);
		
		int paginasTotales = 0; //Sumamos paginas del expediente para saber si ha aumentado de tamanio
		for(Expediente expediente : expList) paginasTotales =+ expediente.getPaginas();
		if(paginasNuevas > paginasTotales) {
			Caja cajaOriginal = caja.getById(expList.get(expList.size() - 1).getCaja());
			//si la caja del ultimo tomo tiene espacio suficiente para las nuevas paginas, se mantiene todo, sino se reubican todas las cajas
			if(cajaOriginal.getPaginas() < (paginasNuevas - paginasTotales)) {
				for(int i = 0; i < expList.size(); i++) {
					Expediente expedient = expList.get(i);
					//buscamos nueva caja para expediente
					Caja nuevaCaja= caja.buscarCajasParaExpedienteNuevo(expedient.getAnio(), expedient.getTipo(), expedient.getPaginas());
					//liberamos espacio de antigua caja
					Caja cajaOrigen = caja.getById(expList.get(i).getCaja());
					cajaOrigen.sumarPaginas(expList.get(i).getPaginas());
					//actualizamos paginas del ultimo tomo
					if(i == expList.size() - 1) expList.get(expList.size() - 1).sumarPaginas(paginasNuevas - paginasTotales);
					//lo restamos de la nueva caja
					nuevaCaja.restarPaginas(expList.get(i).getPaginas());
					//actualizamos caja y ubicacion en expediente
					expList.get(i).setCaja(nuevaCaja.getIdCaja());
					expList.get(i).setUbicacion(nuevaCaja.getUbicacion());
					//update en bbdd
					exp.update(expList.get(i));
					caja.update(cajaOrigen);
					caja.update(nuevaCaja);
				}
			}
			//caben los tomos en sus antiguas cajas
			else {
				//sumar paginas a ultimo tomo a pesar de que quepa y restar de caja
				expList.get(expList.size() - 1).sumarPaginas(paginasNuevas - paginasTotales);
				Caja ultCaja = caja.getById(expList.get(expList.size() - 1).getCaja());
				ultCaja.restarPaginas(paginasNuevas - paginasTotales);
				exp.update(expList.get(expList.size() - 1));
				caja.update(ultCaja);
			}
		}
		return expList;
	}
	
	@Override
	public Expediente nuevo(int numExp, int anio, String tipo, String juzgado, int paginas) throws SQLException {
		ExpedienteDAO exp = new ExpedienteDAOImpl();
		CajaDAO caja = new CajaDAOImpl();
		
		//buscar caja nuevo expediente
		Caja nuevaCaja= caja.buscarCajasParaExpedienteNuevo(anio, tipo, paginas);
		//asignar nueva caja al expediente
		Expediente expediente = new Expediente(numExp, tipo, anio, nuevaCaja.getIdCaja(), nuevaCaja.getUbicacion(), null, null, juzgado, "archivo", paginas, null, null);
		//restar paginas de caja
		nuevaCaja.restarPaginas(paginas);
		//actualizar bd
		exp.update(expediente);
		caja.update(nuevaCaja);
		
		return expediente;
	}

}
