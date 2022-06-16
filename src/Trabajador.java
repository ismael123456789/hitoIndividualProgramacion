
public class Trabajador extends Persona{
	private String departamento;
	
	public Trabajador(String nombre, String telefono, String correo, String departamento) {
		super(nombre, telefono,correo);
		this.departamento = departamento;
	}
	
	
	public Trabajador(String nombre, String telefono, String departamento) {
		super(nombre, telefono);
		this.departamento=departamento;
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
	return "Trabajador: " + super.toString() +" del departamento " + this.departamento + "]";

	}
	
	public String toString2() {
		return "Trabajador: " + super.toString2() + " del departameto " + this.departamento;
	}
}
