package psp_argumentos;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Repaso8_2 {

	public static void main(String[] args) throws IOException {
		/*
		 * Realiza un segundo programa que ejecute el anterior, debe leer la cadena
		 * desde el teclado y mostrar la salida por pantalla.
		 */
		File directorio = new File(".\\bin");
		ProcessBuilder pb = new ProcessBuilder("java", "psp_argumentos.Repaso8");
		pb.directory(directorio);
		// Se ejecuta el proceso
		Process p = pb.start();

		// escritura
		Scanner sc = new Scanner(System.in);
		String cadena = sc.next() + "\n";
		OutputStream os = p.getOutputStream();
		os.write(cadena.getBytes());
		os.flush();

		InputStream is = p.getInputStream();
		int c;
		while ((c = is.read()) != -1)
			System.out.print((char) c);
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
