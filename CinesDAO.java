package retoUd8_gestionDeCines;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class CinesDAO {

	private ConexionBD conexionBD;
	
	//constructor sin parametros
	public CinesDAO() {
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
	public void insertarCine(Cines cine) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = conexionBD.getConnection();
			String query = "INSERT INTO cines (id, nombre, direccion) "
					+ "VALUES (?, ?, ?) ";
			
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, cine.getId());
			pstmt.setString(2, cine.getNombre());
			pstmt.setString(3, cine.getDireccion());
			
			int rowsInserted = pstmt.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("\nSe ha añadido el cine correctamente.");
			} else {
				System.out.println("\nNo se ha podido añadir el cine. Inténtalo de nuevo.");
			}
			
		
		close(connection);
		
					
					
		} catch (SQLException e) {
			//No se hace nada en caso de error
		}
		
	}
	
	//este método muestra todos los cines
	public void mostrarTodoCines() throws SQLException {

	Connection connection = null;
	PreparedStatement pstmt = null;

	try {
		connection = conexionBD.getConnection();
		String query = "SELECT * FROM cines ORDER BY id ASC ";

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

				// Se muestran todos los cines
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String direccion = rs.getString("direccion");

				// Crear un objeto Cines con los valores obtenidos
				Cines cine = new Cines(id, nombre, direccion);

				System.out.println("\n--------------------");

				System.out.println(cine.toString());
			}
			
			System.out.println("\n--------------------");
		}

		} finally {
			close(pstmt);
			close(connection);
		}
	}
	
	/* este método recibe el id del cine a buscar */
	public void obtenerCinePorID(int id) throws SQLException {


		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = conexionBD.getConnection();
			String query = "SELECT * FROM cines WHERE id = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, id);

			/*
			 * Ejecutar sentencia SQL y cargar el Resulset con los datos del registro
			 * encontrado en la tabla
			 */

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				/* leer los valores del ResultSet */
				int idCine = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String direccion = rs.getString("direccion");

				// Crear un objeto Cines con los valores obtenidos
				Cines cine = new Cines(id, nombre, direccion);

				System.out.println("\nCINE ENCONTRADO\n" + "-------------------");
				System.out.println(cine.toString());
				System.out.println("-------------------");

			}
			
			
		} finally {

			close(pstmt);
			close(connection);
		}
	}
		
	//Este metodo actualiza el cine indicado por Id	
	public void actualizaCine(Cines cine, int id) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = conexionBD.getConnection();
			String query = "UPDATE cines SET id=?, nombre=?, direccion=? WHERE id=?";

			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, cine.getId());
			pstmt.setString(2, cine.getNombre());
			pstmt.setString(3, cine.getDireccion());
			pstmt.setInt(4, id);
			

			int rowsUpdated = pstmt.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("\nCine actualizado con éxito.");
			} else {
				System.out.println("\\nNo se pudo actualizar el cine.");
			}
			
		} finally {
			close(pstmt);
			close(connection);
		}
	}
	
	//Este metodo borra el cine indicado por Id
	void eliminarCine(int id) throws SQLException {
		/*
		 * este método recibe un objeto de la clase Cines que usaremos para acceder a
		 * los getter de cada atributo
		 */
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = conexionBD.getConnection();
			String query = "DELETE FROM cines WHERE id = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, id);
			int rowsDeleted = pstmt.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Se ha eliminado el cine" + " con el Id = " + id);
			}
		} finally {
			close(pstmt);
			close(connection);
		}

	}

}
