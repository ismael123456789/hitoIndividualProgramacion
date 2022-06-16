import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		double nota=0;
		String opc="";
		File fichero = new File("preguntas.txt");
		Scanner lectorTeclado = new Scanner(System.in);
		Scanner lector = new Scanner(fichero);
		while (lector.hasNextLine()) {
			String pregunta = lector.nextLine();
			String opcionA = lector.nextLine();
			String opcionB = lector.nextLine();
			String opcionC = lector.nextLine();
			String respuesta = lector.nextLine();
			System.out.println(pregunta);
			System.out.println(opcionA);
			System.out.println(opcionB);
			System.out.println(opcionC);
			System.out.println("¿Qué opción es la correcta?");
			//System.out.println("");
			opc = lectorTeclado.nextLine().toUpperCase();
			System.out.println("");
			if (opc.equals(respuesta)) {
				nota=nota+1;
			}else {
				nota=nota-0.5;
			}
		}
		System.out.println("Tu puntuacion es "+nota);
		lector.close();
		lectorTeclado.close();
		}
	}
