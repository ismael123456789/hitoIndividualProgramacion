
public class Persona {
	private String nombre;
	private String telefono;
	private String correo;
	
	public Persona(String nombre, String telefono, String correo) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = correo;
	}
	
	public Persona(String nombre, String telefono) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return this.nombre + " - " + this.telefono+ " - " + this.correo;
	}
	
	public String toString2() {
		return this.nombre + " - " + this.telefono;
	}
}
