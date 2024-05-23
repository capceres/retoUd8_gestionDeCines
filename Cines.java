package retoUd8_gestionDeCines;

public class Cines {

	//atributos
	private int id;
	private String nombre;
	private String direccion;
	
	//constructor
	public Cines(int id, String nombre, String direccion) {
		this.setId(id);
		this.setNombre(nombre);
		this.setDireccion(direccion);
	}

	//metodos
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String toString() {
		return "\nId: " + id + "\nNombre: " + nombre + "\nDirección: " + direccion;
	}
}
