package psp_argumentos;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Repaso7 {

	public static void main(String[] args) {
		/*
		 * Crea un programa Java que lea cadenas desde la entrada estándar hasta
		 * escribir * Crea otro programa que ejecute el anterior.
		 */
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(in);
		String texto;
		String salida = "*";
		String fin = "";
		try {
			do {
				texto = br.readLine();
				if (texto.equals(salida)) {
					System.out.println("Asterisco");
					System.exit(1);
				}
				System.out.println(texto);
			} while (!texto.equals(fin));
		} catch (Exception e) {
			System.out.println("ERROR");
		}

	}

}