package coleccionLista;

public class Triangulo {
	private int lado1;
	private int lado2;
	private int lado3;
	
	public Triangulo(int lado1, int lado2, int lado3) {
		this.lado1 = lado1;
		this.lado2 = lado2;
		this.lado3 = lado3;
	}
	@Override
	public String toString() {
		return "Triangulo [" + lado1 + ", " + lado2 + ", " + lado3 + "] ";
	}
	
	public String verTipo() {
		if (this.lado1==this.lado2&&this.lado2==this.lado3) {
			return this.toString() + " Equilátero";	
		}
		else if (this.lado1==this.lado2||this.lado2==this.lado3||this.lado1==this.lado3) {
			return this.toString() + " Isósceles";
		}
		else {
			return this.toString() + " Escaleno";
		}
	}
}


