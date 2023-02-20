package threads.control_acceso;

import java.util.Random;

public class Impresora {

    private boolean ocupado;

    public synchronized void imprimir(String documento) {
        while (ocupado) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        ocupado = true;
        System.out.println(Thread.currentThread().getName() + " está imprimiendo " + documento);
        try {
            Thread.sleep(new Random().nextInt(5000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        ocupado = false;
        notifyAll();
    }
}