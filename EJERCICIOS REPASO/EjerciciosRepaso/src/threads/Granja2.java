package threads;

import java.util.Random;

public class Granja2 {

    public static void main(String[] args) {
        Recipiente recipiente = new Recipiente();
        Thread cuidador = new Thread(new Cuidador(recipiente));
        cuidador.start();
        Animal[] animales = new Animal[5];
        for (int i = 0; i < animales.length; i++) {
            animales[i] = new Animal(recipiente);
            animales[i].start();
        }
    }
}

class Animal extends Thread {

    private Recipiente recipiente;
    private Random random;

    public Animal(Recipiente recipiente) {
        this.recipiente = recipiente;
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
                moverse();
                hacerSonido();
                if (comer()) {
                    System.out.println(getName() + " está comiendo.");
                    Thread.sleep(500);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void moverse() throws InterruptedException {
        System.out.println(getName() + " se está moviendo.");
        Thread.sleep(random.nextInt(1000));
    }

    private void hacerSonido() throws InterruptedException {
        System.out.println(getName() + " hace un sonido.");
        Thread.sleep(random.nextInt(1000));
    }

    private boolean comer() throws InterruptedException {
        synchronized (recipiente) {
            if (recipiente.estaVacio()) {
                System.out.println(getName() + " entra al recipiente y está vacío.");
                return false;
            } else {
                recipiente.vaciar();
                return true;
            }
        }
    }
}

class Cuidador implements Runnable {

    private static final int TIEMPO_ESPERA = 100;

    private Recipiente recipiente;

    public Cuidador(Recipiente recipiente) {
        this.recipiente = recipiente;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(TIEMPO_ESPERA);
                if (recipiente.estaVacio()) {
                    System.out.println("El recipiente estaba vacío. Se está rellenando...");
                    Thread.sleep(500);
                    recipiente.rellenar();
                    System.out.println("El recipiente ha sido rellenado.");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Recipiente {

    private static final int CAPACIDAD_MAX = 1;

    private int cantidad;

    public synchronized boolean estaVacio() {
        return cantidad == 0;
    }

    public synchronized void vaciar() {
        cantidad = 0;
    }

    public synchronized void rellenar() {
        cantidad = CAPACIDAD_MAX;
    }
}
