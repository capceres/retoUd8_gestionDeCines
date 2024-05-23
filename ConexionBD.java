package retoUd8_gestionDeCines;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

	//atributos de la clase ConexionBD, que guardaran datos de la conexion a la base de datos
	private String jdbcUrl;
	private String user;
	private String password;
	
	//constructor de 3 parametros que recibe los datos de conexion y los almacena en sus correspondientes atributos
	public ConexionBD(String jdbcUrl, String user, String password) {
		this.jdbcUrl = jdbcUrl;
		this.user = user;
		this.password = password;
	}
	
	//metodo getter que retorna un objeto de tipo conexion
	public Connection getConnection() throws SQLException {
		
		//Establecemos la conexion. La clase DriverManager proporciona metodos para administrar las conexiones a la base de datos
		Connection connection = DriverManager.getConnection(this.jdbcUrl, this.user, this.password);
		
		//Se retorna el objeto que representa nuestra conexion a la base de datos
		return connection;
	}
}