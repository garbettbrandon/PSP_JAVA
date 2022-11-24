package ejercicios;

import java.io.*;

public class Ejer1_6_Controlador {

	public static void main(String[] args) throws IOException {

		File directorio = new File(".\\bin");
		// crea el proceso y lo ejectuta
		ProcessBuilder pb = new ProcessBuilder("JAVA", "psp_argumentos.Ejer1_6");

		// Establece el directorio del programa a ejecutar
		pb.directory(directorio);

		// Se ejecuta el proceso
		Process p = pb.start();

		System.out.println("La suma de los numeros es :");

		// envio de datos
		OutputStream os = p.getOutputStream();
		os.write("6\n".getBytes());
		os.write("2\n".getBytes());
		os.flush();

		// lectura del proceso anterior
		InputStream is = p.getInputStream();
		int c;
		while ((c = is.read()) != -1) {
			System.out.print((char) c);
		}
		is.close();
	}
}
