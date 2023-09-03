package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Conexion;

public class SolicitanteDAOImpl implements SolicitanteDAO {

	@Override
	public boolean existeEmpleado(int id) throws SQLException, ClassNotFoundException {
		boolean existe = false;

        String query = "SELECT COUNT(*) AS count FROM solicitantes WHERE idEmpleado = " + id;

        ResultSet rs = Conexion.executeSelect(query);
        if (rs.next()) {
        	int count = rs.getInt("count");
        	existe = count > 0;
        }
        return existe;
	}

}
