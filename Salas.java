package retoUd8_gestionDeCines;

public class Salas {

	//atributos
	private int id;
	private int capacidad;
	private double metrosCuadrados;
	
	//constructor
	public Salas(int id, int capacidad, double metrosCuadrados) {
		this.setId(id);
		this.setCapacidad(capacidad);
		this.setMetrosCuadrados(metrosCuadrados);
	}

	//metodos
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public double getMetrosCuadrados() {
		return metrosCuadrados;
	}

	public void setMetrosCuadrados(double metrosCuadrados) {
		this.metrosCuadrados = metrosCuadrados;
	}
	
	public String toString() {
		return "\nId: " + id + "\nNúmero de plazas: " + capacidad + "\nMetros cuadrados: " + metrosCuadrados;
	}

}
