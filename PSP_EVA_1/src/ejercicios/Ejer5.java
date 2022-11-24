package ejercicios;

public class Ejer5 {

	public static void main(String[] args) {
		/*
		 * Crea un programa de Java que visualice 5 veces la cadena que se le envía
		 * desde los argumentos de main(). Si no se le envía ninguna cadena que muestre
		 * un mensaje indicándolo y que finalice el programa con System.exit(1)
		 */

		try {
			for (int i = 0; i < 5; i++) {
				System.out.println(args[0]);
			}
		} catch (Exception e) {
			System.out.println("No se ha introducido ningun argumento");
			System.exit(1);
		}

	}
}