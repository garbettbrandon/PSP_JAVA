package ejercicios;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Ejer5_2 {

	public static void main(String[] args) throws IOException {
		/*
		 * A continuación, crea un segundo programa de Java que introduzca por teclado
		 * una cadena y ejecute el programa anterior para visualizar 5 veces esa cadena
		 */
		File directorio = new File(".\\bin");
		ProcessBuilder pb = new ProcessBuilder("JAVA", "psp_argumentos.Ejer5", "hola");
		pb.directory(directorio);
		Process p = pb.start();

		// lectura del proceso anterior
		InputStream is = p.getInputStream();
		int c;
		while ((c = is.read()) != -1) {
			System.out.print((char) c);
		}
		is.close();

		// COMPROBACION DE ERROR - 0 bien - 1 mal
		int exitVal;
		try {
			exitVal = p.waitFor();
			System.out.println("Valor de Salida: " + exitVal);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}