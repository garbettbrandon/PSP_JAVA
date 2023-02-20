package threads.control_acceso;

public class Controlador {
    public static void main(String[] args) {
        Impresora impresora = new Impresora();
        Thread hilo1 = new ThreadImpresora(impresora, "Currriculum");
        Thread hilo2 = new ThreadImpresora(impresora, "Trabajo Clase");
        Thread hilo3 = new ThreadImpresora(impresora, "Libro Castellano");
        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}