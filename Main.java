package retoUd8_gestionDeCines;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		String respuestaIncorrecta = "\n*Respuesta incorrecta. Introduzca '1', '2' o '3'. ";
		String respuestaSN = "\n*Respuesta incorrecta. Introduzca 'S' o 'N'. ";
		String idIntroduzca = "\nIntroduzca el identificador numérico: ";
		

		//saludo de bienvenida
		System.out.println("Te damos la bienvenida a Tu cine, tu gestor de cines :)");
		
		//menu de opciones para introducir registro por consola
		String respuesta = "";
		try (Scanner sc = new Scanner(System.in)) {
			
			//mientras no se introduzcan S o N se repite la pregunta
			while (!"S".equalsIgnoreCase(respuesta) && !"N".equalsIgnoreCase(respuesta)) {
				System.out.println("\n¿Quieres introducir un registro nuevo de cines, películas o salas? Responde 'S' o 'N': ");
				respuesta = sc.nextLine();
					
				if("S".equalsIgnoreCase(respuesta)) {
					
					//se pregunta para introducir una de las 3 opciones
					System.out.println("\n¿Qué quieres introducir? Escribe 1, 2 o 3: \n\n1. Cines \n2. Películas \n3. Salas \n");
					respuesta = sc.nextLine();
					
					//se repite la consulta hasta introducir uno de los 3 numeros
					while(!"1".equalsIgnoreCase(respuesta) && !"2".equalsIgnoreCase(respuesta) && !"3".equalsIgnoreCase(respuesta)) {
						
						System.out.println(respuestaIncorrecta);
						respuesta = sc.nextLine();
						
					}
					
					switch (respuesta) {
					
					case "1":
						System.out.println("Se van a introducir los datos de un nuevo cine: ");
						System.out.println(idIntroduzca);
						int idCine = sc.nextInt();
						sc.nextLine(); //esto evita que el siguiente nextLine() tome el salto de linea anterior como valor

						System.out.println("\nIntroduzca el nombre del cine: ");
						String nombreCine = sc.nextLine();
						
						System.out.println("\nIntroduzca la dirección del cine: ");
						String direccionCine = sc.nextLine();
						
						
						//inserta el objeto cine con los atributos introducidos
						CinesDAO cinesDAO = new CinesDAO();
						
						try {											
							Cines cine1 = new Cines(idCine, nombreCine, direccionCine);
							cinesDAO.insertarCine(cine1);
							
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						break;
						
					case "2":
						System.out.println("\nSe van a introducir los datos de una nueva película.");
						
						System.out.println(idIntroduzca);
						int idPeli = sc.nextInt();
						sc.nextLine();
						
						System.out.println("\nIntroduzca el título de la película: ");
						String tituloPeli = sc.nextLine();
						
						System.out.println("\nIntroduzca la duración en minutos: ");
						int minPeli = sc.nextInt();
						sc.nextLine();
						
						System.out.println("\nIntroduzca el género de la película: ");
						String generoPeli = sc.nextLine();
						
						System.out.println("\nIntroduzca el director de la película: ");
						String directorPeli = sc.nextLine();
						
						System.out.println("\nIntroduzca el número de la clasificación por edad de la película: ");
						int clasEdadPeli = sc.nextInt();
						sc.nextLine();
						
						System.out.println("\nIntroduzca el precio de la película (se puede usar coma): ");
						double precioPeli = sc.nextDouble();
						sc.nextLine();
						
						//inserta el objeto pelicula con los atributos introducidos
						PeliculasDAO peliculasDAO = new PeliculasDAO();
						
						try {				
							Peliculas pelicula1 = new Peliculas(idPeli, tituloPeli, minPeli, generoPeli, directorPeli, clasEdadPeli, precioPeli);
							peliculasDAO.insertarPelicula(pelicula1);
							
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						break;
						
					case "3":
						System.out.println("\nSe van a introducir los datos de una sala.");
						
						System.out.println(idIntroduzca);
						int idSala = sc.nextInt();
						sc.nextLine();
						
						System.out.println("\nIntroduzca el número de plazas de la sala: ");
						int capacidadSala = sc.nextInt();
						sc.nextLine();
						
						System.out.println("\nIntroduzca el número de metros cuadrados de la sala (se puede usar coma): ");
						double metrosCuadradosSala = sc.nextDouble();
						sc.nextLine();
						
						//inserta el objeto sala con los atributos introducidos
						SalasDAO salasDAO = new SalasDAO();
						
						try {				
							Salas sala1 = new Salas(idSala, capacidadSala, metrosCuadradosSala);
							salasDAO.insertarSala(sala1);
							
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						break;
						
					default:
						System.out.println("\nFin de la introducción de datos.");


					}
					
				} else if ("N".equalsIgnoreCase(respuesta)) {
					
					System.out.println("\nNo se introduce ningún registro.");
					
				} else {
					System.out.println(respuestaSN);
				}
					
					
			}
			
			//menu de otras opciones: modificar/consultar/borrar uno, consultar todos o borrar registros
			try (Scanner sc1 = new Scanner(System.in)) {
				respuesta = "";
				String respuesta2 = "";
				
				while (!"1".equalsIgnoreCase(respuesta) && !"2".equalsIgnoreCase(respuesta) && !"3".equalsIgnoreCase(respuesta)) {
					System.out.println("\n¿Quieres realizar otra acción? Escribe 1, 2 o 3: \n\n1. Consultar/modificar/borrar registro \n2. Mostrar todos los registros \n3. Salir ");
					respuesta = sc.nextLine();
					
					//Opcion 1 Consultar/modificar/borrar registro
					if("1".equals(respuesta)) {
						respuesta = "";
						respuesta2 = "";
						
						//Elegir entre cines, peliculas o salas
						while (!"1".equalsIgnoreCase(respuesta2) && !"2".equalsIgnoreCase(respuesta2) && !"3".equalsIgnoreCase(respuesta2)) {
							System.out.println("\n¿Qué quieres consultar? Elige una de las opciones: \n\n1. Cines \n2. Películas \n3. Salas \n");
							respuesta2 = sc.nextLine();
							
							//Opcion Cines
							if("1".equals(respuesta2)) {								
								System.out.println("\nIntroduce el identificador numérico (ID) del cine: ");
								int respuesta3 = sc.nextInt();								
								
								try {				
									CinesDAO cinesDAO = new CinesDAO();
									cinesDAO.obtenerCinePorID(respuesta3);
									
									respuesta2 = "";
									
									while (!"1".equalsIgnoreCase(respuesta2) && !"2".equalsIgnoreCase(respuesta2) && !"3".equalsIgnoreCase(respuesta2)) {
										System.out.println("\n¿Qué acción quieres realizar en este registro?: \n1. Modificar \n2. Borrar \n3. Salir");
										respuesta2 = sc.nextLine();
										
										//Modificar cine
										if("1".equals(respuesta2)) {
											System.out.println("\nSe va a proceder a modificar el registro indicado: ");
											
											System.out.println(idIntroduzca);
											int idCine = sc.nextInt();
											sc.nextLine(); //esto evita que el siguiente nextLine() tome el salto de linea anterior como valor

											System.out.println("\nIntroduzca el nombre del cine: ");
											String nombreCine = sc.nextLine();
											
											System.out.println("\nIntroduzca la dirección del cine: ");
											String direccionCine = sc.nextLine();
											
											//inserta el objeto cine con los atributos introducidos
											CinesDAO cinesDAO2 = new CinesDAO();
											
											try {											
												Cines cine1 = new Cines(idCine, nombreCine, direccionCine);
												cinesDAO2.actualizaCine(cine1, respuesta3);
												
											} catch (SQLException e) {
												e.printStackTrace();
											}					
											
											
										//borrar cine	
										} else if("2".equals(respuesta2)) {
											
											System.out.println("\nSe va a proceder a eliminar el registro indicado. Escribe 'S' para confirmar el borrado o 'N' para retroceder: ");
											respuesta2 = sc.nextLine();
											
											while (!"S".equalsIgnoreCase(respuesta2) && !"N".equalsIgnoreCase(respuesta2)) {
												System.out.println(respuestaSN);
												respuesta2 = sc.nextLine();
											}

											if("S".equalsIgnoreCase(respuesta2)) {
												
												try {											
													cinesDAO.eliminarCine(respuesta3);
													
												} catch (SQLException e) {
													e.printStackTrace();
												}
												
											} else if ("N".equalsIgnoreCase(respuesta2)) {
												System.out.println("\nNo se ha borrado el registro.");
												
											}
											
										}	
									}
									
									
									
									
								} catch (SQLException e) {
									e.printStackTrace();
								}
								
								
							//Opcion Peliculas	
							} else if("2".equals(respuesta2)) {
								System.out.println("\nIntroduce el identificador numérico (ID) de la película: ");
								int respuesta3 = sc.nextInt();								
								
								try {				
									PeliculasDAO peliculasDAO = new PeliculasDAO();
									peliculasDAO.obtenerPeliculaPorID(respuesta3);
									
									respuesta2 = "";
									
									while (!"1".equalsIgnoreCase(respuesta2) && !"2".equalsIgnoreCase(respuesta2) && !"3".equalsIgnoreCase(respuesta2)) {
										System.out.println("\n¿Qué acción quieres realizar en este registro?: \n1. Modificar \n2. Borrar \n3. Salir");
										respuesta2 = sc.nextLine();
										
										//Modificar película
										if("1".equals(respuesta2)) {
											System.out.println("\nSe va a proceder a modificar el registro indicado: ");
											
											System.out.println(idIntroduzca);
											int idPeli = sc.nextInt();
											sc.nextLine();
											
											System.out.println("\nIntroduzca el título de la película: ");
											String tituloPeli = sc.nextLine();
											
											System.out.println("\nIntroduzca la duración en minutos: ");
											int minPeli = sc.nextInt();
											sc.nextLine();
											
											System.out.println("\nIntroduzca el género de la película: ");
											String generoPeli = sc.nextLine();
											
											System.out.println("\nIntroduzca el director de la película: ");
											String directorPeli = sc.nextLine();
											
											System.out.println("\nIntroduzca el número de la clasificación por edad de la película: ");
											int clasEdadPeli = sc.nextInt();
											sc.nextLine();
											
											System.out.println("\nIntroduzca el precio de la película (se puede usar coma): ");
											double precioPeli = sc.nextDouble();
											sc.nextLine();
											
											try {											
												Peliculas pelicula1 = new Peliculas(idPeli, tituloPeli, minPeli, generoPeli, directorPeli, clasEdadPeli, precioPeli);
												peliculasDAO.actualizarPelicula(pelicula1, respuesta3);
												
											} catch (SQLException e) {
												e.printStackTrace();
											}					
											
											
										//borrar pelicula	
										} else if("2".equals(respuesta2)) {
											respuesta2 ="";
											
											while (!"S".equalsIgnoreCase(respuesta2) && !"N".equalsIgnoreCase(respuesta2)) {
												System.out.println("\nSe va a proceder a eliminar el registro indicado. Escribe 'S' para confirmar el borrado o 'N' para retroceder: ");
												respuesta2 = sc.nextLine();
												
												if("S".equalsIgnoreCase(respuesta2)) {
													
													try {											
														peliculasDAO.eliminarPelicula(respuesta3);
														
													} catch (SQLException e) {
														e.printStackTrace();
													}
													
												} else if ("N".equalsIgnoreCase(respuesta2)) {
													System.out.println("\nNo se ha borrado el registro.");
												} else {
													System.out.println(respuestaSN);
												}
												
											}												

										} 
									}									
									
									
									
								} catch (SQLException e) {
									e.printStackTrace();
								}
								
							//Opcion Salas	
							} else if("3".equals(respuesta2)) {
								System.out.println("\nIntroduce el identificador numérico (ID) de la sala: ");
								int respuesta3 = sc.nextInt();
								
								try {				
									SalasDAO salasDAO = new SalasDAO();
									salasDAO.obtenerSalaPorID(respuesta3);
									
									respuesta2 = "";
									
									while (!"1".equalsIgnoreCase(respuesta2) && !"2".equalsIgnoreCase(respuesta2) && !"3".equalsIgnoreCase(respuesta2)) {
										System.out.println("\n¿Qué acción quieres realizar en este registro?: \n1. Modificar \n2. Borrar \n3. Salir");
										respuesta2 = sc.nextLine();
										
										//Modificar sala
										if("1".equals(respuesta2)) {
											System.out.println("\nSe va a proceder a modificar el registro indicado: ");
											
											System.out.println(idIntroduzca);
											int idSala = sc.nextInt();
											sc.nextLine();
											
											System.out.println("\nIntroduzca el nº de plazas de la sala: ");
											int capacidad = sc.nextInt();
											
											System.out.println("\nIntroduzca los metros cuadrados de la sala: ");
											Double metrosCuadrados = sc.nextDouble();
											sc.nextLine();
											
											
											try {											
												Salas sala1 = new Salas(idSala, capacidad, metrosCuadrados);
												salasDAO.actualizarSala(sala1, respuesta3);
												
											} catch (SQLException e) {
												e.printStackTrace();
											}					
											
											
										//borrar sala	
										} else if("2".equals(respuesta2)) {
											respuesta2 ="";
											
											while (!"S".equalsIgnoreCase(respuesta2) && !"N".equalsIgnoreCase(respuesta2)) {
												System.out.println("\nSe va a proceder a eliminar el registro indicado. Escribe 'S' para confirmar el borrado o 'N' para retroceder: ");
												respuesta2 = sc.nextLine();
												
												if("S".equalsIgnoreCase(respuesta2)) {
													
													try {											
														salasDAO.eliminarSala(respuesta3);
														
													} catch (SQLException e) {
														e.printStackTrace();
													}
													
												} else if ("N".equalsIgnoreCase(respuesta2)) {
													System.out.println("\nNo se ha borrado el registro.");
												} else {
													System.out.println(respuestaSN);
												}
												
											}												

										} 
									}									
									
									
									
								} catch (SQLException e) {
									e.printStackTrace();
								}
								
								
							} else {
								System.out.println(respuestaIncorrecta);
							}
						}
						
						
					
					//Opcion 2 Mostrar todos los registros
					} else if ("2".equals(respuesta)) {
						respuesta = "";
						respuesta2 = "";
						
						//Elegir entre cines, peliculas o salas
						while (!"1".equalsIgnoreCase(respuesta2) && !"2".equalsIgnoreCase(respuesta2) && !"3".equalsIgnoreCase(respuesta2)) {
							System.out.println("\nSe van a mostrar todos los registros. Elige una de las opciones: \n\n1. Cines \n2. Películas \n3. Salas \n");
							respuesta2 = sc.nextLine();
							
							if("1".equals(respuesta2)) {								
								System.out.println("\nEstos son todos los registros de Cines: ");
								try {				
									CinesDAO cinesDAO2 = new CinesDAO();
									cinesDAO2.mostrarTodoCines();
									
								} catch (SQLException e) {
									e.printStackTrace();
								}
								
									
							} else if("2".equals(respuesta2)) {
								System.out.println("\nEstos son todos los registros de Películas: ");
								try {				
									PeliculasDAO peliculasDAO2 = new PeliculasDAO();
									peliculasDAO2.mostrarTodoPeliculas();
									
								} catch (SQLException e) {
									e.printStackTrace();
								}
								
							} else if("3".equals(respuesta2)) {
								System.out.println("\nEstos son todos los registros de Salas: ");
								try {				
									SalasDAO salasDAO2 = new SalasDAO();
									salasDAO2.mostrarTodoSalas();
									
								} catch (SQLException e) {
									e.printStackTrace();
								}
								
							} else {
								System.out.println(respuestaIncorrecta);
							}
						}
								
								
					} else if ("3".equals(respuesta)){
						
						System.out.println("\nNo se realizan más acciones.");
						
					} else {
						System.out.println(respuestaIncorrecta);
					}
					
					
				}
			}
			
						
		}
		
		
		System.out.println("\nHasta luego! Esperamos que vuelvas a utilizar Tu cine pronto :D");
		
	
	}

}
