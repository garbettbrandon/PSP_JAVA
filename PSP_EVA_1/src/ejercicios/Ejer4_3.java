package ejercicios;

public class Ejer4_3 {

	public static void main(String[] args) {
		/*
		 * Prueba los programas desde el entorno Eclipse. A continuación. crea una
		 * carpeta en el disco duro y almacena los dos programas. Realiza los cambios
		 * necesarios para compilarlos y ejecutarlos desde el cmd.
		 */
		if (args.length < 1) {
			System.out.println("1");
			System.exit(1);
		}
		try {
			int num = Integer.valueOf(args[0]);
			if (num < 0) {
				System.exit(3);
			}
		} catch (Exception e) {
			System.exit(2);
		}
		System.out.println(0);
		System.exit(0);
	}
}