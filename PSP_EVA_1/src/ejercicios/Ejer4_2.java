package ejercicios;

import java.io.*;

public class Ejer4_2 {

	public static void main(String[] args) throws IOException {
		/*
		 * Realiza un segundo programa Java que ejecute al anterior. Deberá mostrar en
		 * pantalla lo que pasa dependiendo del valor devuelto al ejecutar el programa
		 * anterior.
		 */
		File directorio = new File(".\\bin");
		ProcessBuilder pb = new ProcessBuilder("JAVA", "senalesPSP_1.Ejer4", "2", "cadena");
		pb.directory(directorio);
		Process p = pb.start();

		// lectura del proceso anterior
		InputStream is = p.getInputStream();
		int c = 0;
		c = is.read();
		is.close();

		// COMPROBACION DE ERROR - 0 bien - 1 mal
		int exitVal;
		try {
			exitVal = p.waitFor();
			System.out.println("Valor de Salida: " + exitVal);
			switch (exitVal) {
			case 0:
				System.out.println("Se ha introducido mas de un argumento");
				break;
			case 1:
				System.out.println("Se ha introducido una cadena");
				break;
			case 2:
				System.out.println("El número es menor de 0");
				break;
			case 3:
				System.out.println("No se ha introducido nada de lo anterior");
				break;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}