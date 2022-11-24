package ejercicios;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ejer3_main {

	public static void main(String[] args) throws IOException {
		Process p = new ProcessBuilder("CMD", "/C", "ipconfig").start();
		try {
			InputStream is = p.getInputStream();
			BufferedReader bf = new BufferedReader(new InputStreamReader(is));
			String linea = null;

			while ((linea = bf.readLine()) != null) {
				Pattern pat = Pattern.compile(".*Adaptador.*");
				Matcher mat = pat.matcher(linea);
				Pattern pat2 = Pattern.compile(".*IPv4.*");
				Matcher mat2 = pat2.matcher(linea);
				if (mat.matches()) {
					for (int i = 0; i < linea.length() - 1; i++) {
						if (i >= 22) {
							System.out.print(linea.charAt(i));
						}
					}
				}
				if (mat2.matches()) {
					for (int i = 0; i < linea.length(); i++) {
						if (i >= 45) {
							System.out.print(linea.charAt(i));
							if (i == linea.length() - 1) {
								System.out.println("\n ");
							}
						}

					}
				}
			}
			is.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

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