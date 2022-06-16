import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner lector = new Scanner(System.in);
		String opc="";
		String seguir="";
		int id=0;
		String nombreP="";
		String fechaEnvasado="";
		int unidades=0;
		double precio=0;
		String disponiblilidad="";
		boolean disponible=true;
		int avanzar=0;
		// Cargar el driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Nose ha podido cargar el driver");
			return;
		}
		System.out.println("Paso 1: Se ha cargado el driver de MySQL");
				
		String cadenaConexion = "jdbc:mysql://localhost:3306/productos";
		String user = "root";
		String pass = ""; 
		Connection con;
				
		//Conectar con la base de datos
		try {
			con = DriverManager.getConnection(cadenaConexion, user, pass);
		} catch (SQLException e) {
			System.out.println("No se ha podido conectar a la base de datos");
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("Paso 2: Hemos conectado a la base de datos");
		
		//Interactura con BD
		
		do {
			System.out.println("----------------------Gestión de productos--------------------------");
			
			System.out.println("1. Añadir producto");
			System.out.println("2. Eliminar producto");
			System.out.println("3. Actualizar producto");
			System.out.println("4. Mostrar productos");
			System.out.println("5. Apagar máquina");
			System.out.println("---------------------------------------------------");
			System.out.println("Escoja una opción (1-5)");
			
			opc = lector.nextLine();
			switch (opc) {
				case "1":
					do {
						System.out.println("Introduzca identificador del producto");
						id=lector.nextInt();
						lector.nextLine();
						System.out.println("Introduzca nombre del producto");
						nombreP=lector.nextLine();
						System.out.println("Introduzca fecha de envasado");
						fechaEnvasado=lector.nextLine();
						System.out.println("Introduzca cantidad de unidades");
						unidades=lector.nextInt();
						lector.nextLine();
						System.out.println("Introduzca el precio del producto");
						precio=lector.nextDouble();
						lector.nextLine();
						
						do {
							System.out.println("¿El producto esta disponible?");
							disponiblilidad=lector.nextLine();
							switch (disponiblilidad.toUpperCase()) {
								case "SI":
									disponible=true;
									avanzar=0;
									break;
								case "NO":
									disponible=false;
									avanzar=0;
									break;
								default:
									avanzar=1;
									System.out.println("Elija SI o NO");
							}
						}while(avanzar==1);
						try {
							Statement sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
							ResultSet rs = sentencia.executeQuery("SELECT * FROM producto");

							rs.moveToInsertRow(); 
							rs.updateInt("IdProducto", id); 
							rs.updateString("nombre", nombreP); 
							rs.updateString("fechaEnvasado", fechaEnvasado); 
							rs.updateInt("unidades", unidades); 
							rs.updateDouble("precio", precio); 
							rs.updateBoolean("disponible", disponible); 
							rs.insertRow(); 

						} catch(SQLIntegrityConstraintViolationException e1) {
							System.out.println("Ya existe un producto con el mimo identificador");
						}
						catch (SQLException e) {
							System.out.println("Error al añadir el nuevo producto");
							System.out.println(e.getMessage());
						}		

						do {
							System.out.println("¿Quiere añadir otro producto?");
							seguir=lector.nextLine();
							switch (seguir.toUpperCase()) {
								case "SI":
									avanzar=0;
									break;
								case "NO":
									avanzar=0;
									break;
								default:
									System.out.println("Elija SI o NO");
									avanzar=1;
							}
						}while(avanzar==1);
					}while(!seguir.toUpperCase().equals("NO"));
					
					System.out.println("---------------------------------------------------");
					break;
				case "2":
					do {
						System.out.println("Introduzca el identificador del producto que desea eliminar");
						id=lector.nextInt();
						lector.nextLine();
						do {
							System.out.println("¿Esta seguro?");
							seguir=lector.nextLine();
							switch (seguir.toUpperCase()) {
								case "SI":
									avanzar=0;
									try {
										Statement sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
										String sql = "SELECT * FROM producto WHERE IdProducto = "+id;
										System.out.println(sql);
										ResultSet rs = sentencia.executeQuery(sql);
										boolean existe = rs.next();
										if (existe) {
											rs.deleteRow();
										}
									} catch (SQLException e) {
										System.out.println("Error al eliminar el producto");
										System.out.println(e.getMessage());
									}
									break;
								case "NO":
									avanzar=0;
									break;
								default:
									System.out.println("Elija SI o NO");
									avanzar=1;
							}
						}while(avanzar==1);
						do {
							System.out.println("Desea eliminar otro producto");
							seguir=lector.nextLine();
							switch (seguir.toUpperCase()) {
								case "SI":
									avanzar=0;
									break;
								case "NO":
									avanzar=0;
									break;
								default:
									System.out.println("Elija SI o NO");
									avanzar=1;
							}
						}while(avanzar==1);
					}while(!seguir.toUpperCase().equals("NO"));
					
					System.out.println("---------------------------------------------------");
					break;
				case "3":
					do {
						System.out.println("Introduzca el identificador del producto que desea actualizar");
						id=lector.nextInt();
						lector.nextLine();
						do {
							System.out.println("¿Desea modificar el nombre?");
							seguir=lector.nextLine();
							switch (seguir.toUpperCase()) {
								case "SI":
									avanzar=0;
									System.out.println("Introduzca nombre del producto");
									nombreP=lector.nextLine();
									try {
										Statement sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
										ResultSet rs = sentencia.executeQuery("SELECT * FROM producto WHERE IdProducto = "+id);
										boolean existe = rs.next();
										if (existe) {
											rs.updateString("nombre", nombreP);
											rs.updateRow();
										}
									} catch (SQLException e) {
										System.out.println("Error al actualizar el producto");
										System.out.println(e.getMessage());
									}	
									break;
								case "NO":
									avanzar=0;
									break;
								default:
									System.out.println("Elija SI o NO");
									avanzar=1;
							}
						}while(avanzar==1);
						do {
							System.out.println("¿Desea modificar la fecha de envasado?");
							seguir=lector.nextLine();
							switch (seguir.toUpperCase()) {
								case "SI":
									avanzar=0;
									System.out.println("Introduzca la fecha de envasado");
									fechaEnvasado=lector.nextLine();
									try {
										Statement sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
										ResultSet rs = sentencia.executeQuery("SELECT * FROM producto WHERE IdProducto = "+id);
										boolean existe = rs.next();
										if (existe) {
											rs.updateString("fechaEnvasado", fechaEnvasado);
											rs.updateRow();
										}
									} catch (SQLException e) {
										System.out.println("Error al actualizar el producto");
										System.out.println(e.getMessage());
									}	
									break;
								case "NO":
									avanzar=0;
									break;
								default:
									System.out.println("Elija SI o NO");
									avanzar=1;
							}
						}while(avanzar==1);
						do {
							System.out.println("¿Desea modificar las unidades?");
							seguir=lector.nextLine();
							switch (seguir.toUpperCase()) {
								case "SI":
									avanzar=0;
									System.out.println("Introduzca la cantidad de unidades");
									unidades=lector.nextInt();
									lector.nextLine();
									try {
										Statement sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
										ResultSet rs = sentencia.executeQuery("SELECT * FROM producto WHERE IdProducto = "+id);
										boolean existe = rs.next();
										if (existe) {
											rs.updateInt("unidades", unidades);
											rs.updateRow();
										}
									} catch (SQLException e) {
										System.out.println("Error al actualizar el producto");
										System.out.println(e.getMessage());
									}	
									break;
								case "NO":
									avanzar=0;
									break;
								default:
									System.out.println("Elija SI o NO");
									avanzar=1;
							}
						}while(avanzar==1);
						do {
							System.out.println("¿Desea modificar el precio?");
							seguir=lector.nextLine();
							switch (seguir.toUpperCase()) {
								case "SI":
									avanzar=0;
									System.out.println("Introduzca el precio del producto");
									precio=lector.nextDouble();
									lector.nextLine();
									try {
										Statement sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
										ResultSet rs = sentencia.executeQuery("SELECT * FROM producto WHERE IdProducto = "+id);
										boolean existe = rs.next();
										if (existe) {
											rs.updateDouble("precio", precio);
											rs.updateRow();
										}
									} catch (SQLException e) {
										System.out.println("Error al actualizar el producto");
										System.out.println(e.getMessage());
									}	
									break;
								case "NO":
									avanzar=0;
									break;
								default:
									System.out.println("Elija SI o NO");
									avanzar=1;
							}
						}while(avanzar==1);
						do {
							System.out.println("¿Desea modificar la disponibilidad?");
							seguir=lector.nextLine();
							switch (seguir.toUpperCase()) {
								case "SI":
									avanzar=0;
									do {
										System.out.println("¿El producto esta disponible?");
										disponiblilidad=lector.nextLine();
										switch (disponiblilidad.toUpperCase()) {
											case "SI":
												disponible=true;
												avanzar=0;
												break;
											case "NO":
												disponible=false;
												avanzar=0;
												break;
											default:
												avanzar=1;
												System.out.println("Elija SI o NO");
										}
									}while(avanzar==1);
									
									try {
										Statement sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
										ResultSet rs = sentencia.executeQuery("SELECT * FROM producto WHERE IdProducto = "+id);
										boolean existe = rs.next();
										if (existe) {
											rs.updateBoolean("disponible", disponible);
											rs.updateRow();
										}
									} catch (SQLException e) {
										System.out.println("Error al actualizar el producto");
										System.out.println(e.getMessage());
									}	
									break;
								case "NO":
									avanzar=0;
									break;
								default:
									System.out.println("Elija SI o NO");
									avanzar=1;
							}
						}while(avanzar==1);
						do {
							System.out.println("Desea eliminar otro producto");
							seguir=lector.nextLine();
							switch (seguir.toUpperCase()) {
								case "SI":
									avanzar=0;
									break;
								case "NO":
									avanzar=0;
									break;
								default:
									System.out.println("Elija SI o NO");
									avanzar=1;
							}
						}while(avanzar==1);
					}while(!seguir.toUpperCase().equals("NO"));
					System.out.println("---------------------------------------------------");
					break;
				case "4":
					System.out.println("---------------------------------------------------");
					ResultSet rs=null;
					try {
						Statement sentencia =con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
								ResultSet.CONCUR_UPDATABLE);
						rs = sentencia.executeQuery("SELECT * FROM producto");
						while (rs.next()) {
							System.out.print("Identificador: "+rs.getString("IdProducto"));
							System.out.print(" - "); 
							System.out.print("Nombre: "+rs.getString("nombre"));
							System.out.print(" - "); 
							System.out.print("Fecha de envasado: "+rs.getString("fechaEnvasado"));
							System.out.print(" - "); 
							System.out.print("Unidades: "+rs.getString("unidades"));
							System.out.print(" - "); 
							System.out.print("Precio: "+rs.getString("precio"));
							System.out.print(" - "); 
							System.out.print("Disponibilidad: "+rs.getString("disponible"));
							System.out.println(); // Retorno de carro
						}
					} catch (SQLException e) {
						System.out.println("Error al realizar el listado de clientes");
						System.out.println(e.getMessage());
					}
					System.out.println("---------------------------------------------------");
					break;
				case "5":
					System.out.println("Adios");
					break;
				default:
					System.out.println("Ópcion incorrecta");
			}
		}while(!opc.equals("5"));
		
		//Cerrar la conexion con la BD
		
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("No se ha podido cerrar la conexion");
			System.out.println(e.getMessage());
		}
		System.out.println("Paso 3: Hemos cerrado la conexion con la base de datos");
	}

}
