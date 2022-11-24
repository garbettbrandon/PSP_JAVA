package psp_argumentos;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Repaso8 {

	public static void main(String[] args) {
		/*
		 * Realiza un programa Java que lea una cadena desde la entrada estándar y
		 * visualice en pantalla si la cadena es o no palíndromo o si la cadena esta
		 * vacia
		 */
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(in);
		String texto;
		String texto2 = "";
		String palindromo = "";
		String vacio = "";

		try {
			texto = br.readLine();
			texto2 = texto.replace(" ", "");
			for (int i = texto2.length() - 1; i >= 0; i--) {
				palindromo += texto2.charAt(i);
			}
			if (texto2.equals(palindromo)) {
				System.out.println("Es un palíndromo");
			} else if (texto.equals(vacio)) {
				System.out.println("La cadena esta vacia");
			} else {
				System.out.println("No es un palindromo");
			}

		} catch (Exception e) {
			System.out.println("ERROR");
		}
	}

}