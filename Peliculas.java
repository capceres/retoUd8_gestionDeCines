package retoUd8_gestionDeCines;

public class Peliculas {
	
	//atributos
	private int id;
	private String titulo;
	private int duracionMinutos;
	private String genero;
	private String director;
	private int clasificacionEdad;
	private double precio;
	
	//constructores
	public Peliculas() {
		
	}
	
	public Peliculas(int id, String titulo, int duracionMinutos, String genero, String director, int clasificacionEdad, double precio) {
		this.setId(id);
		this.setTitulo(titulo);
		this.setDuracionMinutos(duracionMinutos);
		this.setGenero(genero);
		this.setDirector(director);
		this.setClasificacionEdad(clasificacionEdad);
		this.setPrecio(precio);
	}
	
	
	//metodos
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public int getDuracionMinutos() {
		return duracionMinutos;
	}

	public void setDuracionMinutos(int duracionMinutos) {
		this.duracionMinutos = duracionMinutos;
	}
	
	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getClasificacionEdad() {
		return clasificacionEdad;
	}

	public void setClasificacionEdad(int clasificacionEdad) {
		this.clasificacionEdad = clasificacionEdad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String toString() {
		return "\nId: " + id + "\nTítulo: " + titulo + "\nDuración en minutos: " + duracionMinutos + "\nGénero: " + genero + "\nDirector: " + director + "\nClasificación por edad: " + clasificacionEdad + "\nPrecio: " + precio;
	}
	
	

	

}
