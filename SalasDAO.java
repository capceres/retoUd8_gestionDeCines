package retoUd8_gestionDeCines;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalasDAO {
	
private ConexionBD conexionBD;
	
	//constructor sin parametros
	public SalasDAO() {
		this.conexionBD = new ConexionBD("jdbc:mysql://localhost:3307/tucine", "root", "");
	}
	
	//si existe una conexion abierta, se llama al metodo close() para cerrarla. La excepcion puede ser lanzada
	private void close(Connection connection) {
		if (connection !=null) {
			try {
				connection.close();
				
			} catch (SQLException e) {
				//No se hace nada en caso de error
			}
		}
	}
		
	//Si el objeto Statement no es null, es decir, si existe un objeto para ejecutar consultas, se llama al metodo close() del objeto para liberar los recursos utilizados por la consulta.
	private void close(PreparedStatement preparedStatement) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				//No se hace nada en caso de error
			}
		}
	}
	
	//Este metodo recibe un objeto de la clase Cines que usaremos para acceder a los getter de cada atributo de dicho objeto
	public void insertarSala(Salas sala) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = conexionBD.getConnection();
			String query = "INSERT INTO salas (id, capacidad, metrosCuadrados)"
					+ "VALUES (?, ?, ?) ";
			
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, sala.getId());
			pstmt.setInt(2, sala.getCapacidad());
			pstmt.setDouble(3, sala.getMetrosCuadrados());
			
			int rowsInserted = pstmt.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("\nSe ha añadido la sala correctamente.");
			} else {
				System.out.println("\nNo se ha podido añadir la sala. Inténtalo de nuevo.");
			}
			
		
		close(connection);
		
					
					
		} catch (SQLException e) {
			//No se hace nada en caso de error
		}
		
	}
	
	//este método muestra todas las salas
	public void mostrarTodoSalas() throws SQLException {

	Connection connection = null;
	PreparedStatement pstmt = null;

	try {
		connection = conexionBD.getConnection();
		String query = "SELECT * FROM salas ORDER BY id ASC ";

		pstmt = connection.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		if (!rs.isBeforeFirst()) {
			/*
			 * Si isBeforeFirst() devuelve true, no hay registros en el conjunto de
			 * resultados
			 */

			System.out.println("No hay cines registrados.");
		} else {
			/*
			 * Si isBeforeFirst() devuelve false, hay al menos un registro en el conjunto de
			 * resultados
			 */

			while (rs.next()) {

				// Se muestran todas las salas
				int id = rs.getInt("id");
				int capacidad = rs.getInt("capacidad");
				Double metrosCuadrados = rs.getDouble("metrosCuadrados");

				// Crear un objeto Salas con los valores obtenidos
				Salas sala = new Salas(id, capacidad, metrosCuadrados);

				System.out.println("\n--------------------");

				System.out.println(sala.toString());
			}
			
			System.out.println("\n--------------------");
		}

		} finally {
			close(pstmt);
			close(connection);
		}
	}
	
	/* este método recibe el id de la sala a buscar */
	public void obtenerSalaPorID(int id) throws SQLException {


		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = conexionBD.getConnection();
			String query = "SELECT * FROM salas WHERE id = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, id);

			/*
			 * Ejecutar sentencia SQL y cargar el Resulset con los datos del registro
			 * encontrado en la tabla
			 */

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				/* leer los valores del ResultSet */
				int idSala = rs.getInt("id");
				int capacidad = rs.getInt("capacidad");
				Double metrosCuadrados = rs.getDouble("metrosCuadrados");

				// Crear un objeto Cines con los valores obtenidos
				Salas sala = new Salas(idSala, capacidad, metrosCuadrados);

				System.out.println("\nSALA ENCONTRADA\n" + "-------------------");
				System.out.println(sala.toString());
				System.out.println("-------------------");

			}
			
			
		} finally {

			close(pstmt);
			close(connection);
		}
	}
		
	//Este metodo actualiza la sala indicada por Id	
	public void actualizarSala(Salas sala, int id) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = conexionBD.getConnection();
			String query = "UPDATE salas SET id=?, capacidad=?, metrosCuadrados=? WHERE id=?";

			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, sala.getId());
			pstmt.setInt(2, sala.getCapacidad());
			pstmt.setDouble(3, sala.getMetrosCuadrados());
			pstmt.setInt(4, id);
			

			int rowsUpdated = pstmt.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("\nSala actualizada con éxito.");
			} else {
				System.out.println("\\nNo se pudo actualizar la sala.");
			}
			
		} finally {
			close(pstmt);
			close(connection);
		}
	}
	
	
	//Este metodo borra la sala indicada por Id
	void eliminarSala(int id) throws SQLException {
		/*
		 * este método recibe un objeto de la clase Salas que usaremos para acceder a
		 * los getter de cada atributo
		 */
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = conexionBD.getConnection();
			String query = "DELETE FROM salas WHERE id = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, id);
			int rowsDeleted = pstmt.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Se ha eliminado la sala" + " con el Id = " + id);
			}
		} finally {
			close(pstmt);
			close(connection);
		}

	}

}
