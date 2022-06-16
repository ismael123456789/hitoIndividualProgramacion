
public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trabajador tra = new Trabajador("Juan","5423156","juan@gmail.com","Contabilidad");
		Trabajador trab = new Trabajador("Luis","7542","Mantenimiento");
		
		System.out.println(tra.toString());
		System.out.println(trab.toString2());
	}

}

//No permite herencia multiple ya si no podriamos tener dos clases con el mismo metodo y para eso estan las interfaces para poder implementar los metodos comunes

