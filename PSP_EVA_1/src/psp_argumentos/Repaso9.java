package psp_argumentos;

import java.io.*;

public class Repaso9 {

	public static void main(String[] args) throws IOException {
		/*
		 * Modifica el Repaso7 para que al ejecutar el programa la entrada al proceso se
		 * obtenga a partir de un fichero de texto
		 */
		// File directorio=new File(".\\bin");
		File variables = new File("E:\\cadena.txt");
		FileReader fr = new FileReader(variables);
		String texto = "";
		String salida = "*";
		String fin = "";
		// Se ejecuta el proceso
		try {
			BufferedReader bf = new BufferedReader(fr);
			do {
				texto = bf.readLine();
				if (texto.equals(salida)) {
					System.exit(2);
				}
				System.out.println(texto);
			} while (!texto.equals(fin));
		} catch (Exception e) {
			System.out.println("ERROR");
		}
	}
}