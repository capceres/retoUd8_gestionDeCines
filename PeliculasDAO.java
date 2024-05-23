package retoUd8_gestionDeCines;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PeliculasDAO {

	private ConexionBD conexionBD;
	
	//constructor sin parametros
	public PeliculasDAO() {
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
	
	//Este metodo recibe un objeto de la clase Peliculas que usaremos para acceder a los getter de cada atributo de dicho objeto
	public void insertarPelicula(Peliculas pelicula) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = conexionBD.getConnection();
			String query = "INSERT INTO peliculas (id, titulo, duracionMinutos, genero, director, clasificacionEdad, precio) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?) ";
			
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, pelicula.getId());
			pstmt.setString(2, pelicula.getTitulo());
			pstmt.setInt(3, pelicula.getDuracionMinutos());
			pstmt.setString(4, pelicula.getGenero());
			pstmt.setString(5, pelicula.getDirector());
			pstmt.setInt(6, pelicula.getClasificacionEdad());
			pstmt.setDouble(7, pelicula.getPrecio());
			
			int rowsInserted = pstmt.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("\nSe ha añadido la película correctamente.");
			} else {
				System.out.println("\nNo se ha podido añadir la película. Inténtalo de nuevo.");
			}
			
		
		close(connection);
		
					
					
		} catch (SQLException e) {
			//No se hace nada en caso de error
		}
		
	}
	
	// este método muestra todas las peliculas
	public void mostrarTodoPeliculas() throws SQLException {

	Connection connection = null;
	PreparedStatement pstmt = null;

	try {
		connection = conexionBD.getConnection();
		String query = "SELECT * FROM peliculas ORDER BY id ASC ";

		pstmt = connection.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		if (!rs.isBeforeFirst()) {
			/*
			 * Si isBeforeFirst() devuelve true, no hay registros en el conjunto de
			 * resultados
			 */

			System.out.println("No hay peliculas registradas.");
		} else {
			/*
			 * Si isBeforeFirst() devuelve false, hay al menos un registro en el conjunto de
			 * resultados
			 */

			while (rs.next()) {

				// Se muestran todos los cines
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				int duracion = rs.getInt("duracionMinutos");
				String genero = rs.getString("genero");
				String director = rs.getString("director");
				int clasificacion = rs.getInt("clasificacionEdad");
				double precio = rs.getDouble("precio");

				// Crear un objeto Cines con los valores obtenidos
				Peliculas pelicula = new Peliculas(id, titulo, duracion, genero, director, clasificacion, precio);

				System.out.println("\n--------------------");

				System.out.println(pelicula.toString());
			}
			
			System.out.println("\n--------------------");
		}

		} finally {
			close(pstmt);
			close(connection);
		}
	}
	
	/* este método recibe el id de la pelicula a buscar */
	public void obtenerPeliculaPorID(int id) throws SQLException {

		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = conexionBD.getConnection();
			String query = "SELECT * FROM peliculas WHERE id = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, id);

			/*
			 * Ejecutar sentencia SQL y cargar el Resulset con los datos del registro
			 * encontrado en la tabla
			 */

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				/* leer los valores del ResultSet */
				int idPeli = rs.getInt("id");
				String titulo = rs.getString("titulo");
				int duracion = rs.getInt("duracionMinutos");
				String genero = rs.getString("genero");
				String director = rs.getString("director");
				int clasificacionEdad = rs.getInt("clasificacionEdad");
				double precio = rs.getInt("precio");

				// Crear un objeto Peliculas con los valores obtenidos
				Peliculas pelicula = new Peliculas(id, titulo, duracion, genero, director, clasificacionEdad, precio);

				System.out.println("\nPELICULA ENCONTRADA\n" + "-------------------");
				System.out.println(pelicula.toString());
				System.out.println("-------------------");

			}
			
			
		} finally {

			close(pstmt);
			close(connection);
		}
	}
		
	//Este metodo actualiza el cine indicado por Id	
	public void actualizarPelicula(Peliculas pelicula, int id) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = conexionBD.getConnection();
			String query = "UPDATE peliculas SET id=?, titulo=?, duracionMinutos=?, genero=?, director=?, clasificacionEdad=?, precio=? WHERE id=?";

			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, pelicula.getId());
			pstmt.setString(2, pelicula.getTitulo());
			pstmt.setInt(3, pelicula.getDuracionMinutos());
			pstmt.setString(4, pelicula.getGenero());
			pstmt.setString(5, pelicula.getDirector());
			pstmt.setInt(6, pelicula.getClasificacionEdad());
			pstmt.setDouble(7, pelicula.getPrecio());
			
			pstmt.setInt(8, id);
			

			int rowsUpdated = pstmt.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("\nPelícula actualizada con éxito.");
			} else {
				System.out.println("\nNo se pudo actualizar la película.");
			}
			
		} finally {
			close(pstmt);
			close(connection);
		}
	}
	
	
	//Este metodo borra la pelicula indicada por Id
	void eliminarPelicula(int id) throws SQLException {
		/*
		 * este método recibe un objeto de la clase Peliculas que usaremos para acceder a
		 * los getter de cada atributo
		 */
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = conexionBD.getConnection();
			String query = "DELETE FROM peliculas WHERE id = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, id);
			int rowsDeleted = pstmt.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("\nSe ha eliminado la película" + " con el Id = " + id);
			}
		} finally {
			close(pstmt);
			close(connection);
		}

	}
	
}
