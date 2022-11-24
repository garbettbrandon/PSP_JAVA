package ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejer1_6 {

	public static void main(String[] args) throws IOException {
		/*
		 * Escribe un programa de Java que lea dos números desde la entrada estándar y
		 * visualice su suma. Controlar que lo introducido por teclado sean dos numeros.
		 * Haz otro programa JAVA para ejecutar el anterior.
		 */

		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(in);
		int numeros[] = new int[2];
		boolean ok = true;

		do {
			ok = true;
			try {
				System.out.println("Introduce un numero");
				numeros[0] = Integer.parseInt(br.readLine());
				numeros[1] = Integer.parseInt(br.readLine());
			} catch (Exception e) {
				System.out.println("NO ES UN NÚMERO");
				ok = false;
			}
		} while (!ok);
		in.close();
		System.out.println("La suma de " + numeros[0] + " y " + numeros[1] + " es " + (numeros[0] + numeros[1]));
	}
}