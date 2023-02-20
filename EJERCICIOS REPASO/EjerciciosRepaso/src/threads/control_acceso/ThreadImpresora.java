package threads.control_acceso;

public class ThreadImpresora extends Thread {

    private Impresora impresora;
    private String documento;

    public ThreadImpresora(Impresora impresora, String documento) {
        this.impresora = impresora;
        this.documento = documento;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " está intentando imprimir " + documento);
        impresora.imprimir(documento);
        System.out.println(Thread.currentThread().getName() + " ha terminado de imprimir " + documento);
    }
}