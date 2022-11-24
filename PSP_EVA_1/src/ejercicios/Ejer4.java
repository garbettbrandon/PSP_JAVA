package ejercicios;

public class Ejer4 {

	public static void main(String[] args) {
		/*
		 * Realiza un programa Java que admita argumentos desde main() y devuelva con
		 * System.exit() los siguientes valores: Si el número de argumentos es 1 debe
		 * devolver 1. Si el argumento es una cadena debe devolver 2. Si el argumento es
		 * un número entero menor que 0 debe devolver 3. En cualquier otra situación
		 * debe devolver 0.
		 * 
		 * Prueba los programas desde el entorno Eclipse. A continuación. crea una
		 * carpeta en el disco duro y almacena los dos programas. Realiza los cambios
		 * necesarios para compilarlos y ejecutarlos desde el cmd.
		 */
		if (args.length == 1) {
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