package threads;

import java.util.Random;

public class Granja {

    public static void main(String[] args) {
        final int NUM_ANIMALES = 5;
        Recipiente recipiente = new Recipiente();
        Thread[] animales = new Thread[NUM_ANIMALES];

        // Creamos los animales y los iniciamos en threads separados
        for (int i = 0; i < NUM_ANIMALES; i++) {
            Animal animal = new Animal("Animal " + (i + 1), recipiente);
            animales[i] = new Thread(animal);
            animales[i].start();
        }

        // Esperamos a que terminen todos los threads de los animales
        for (Thread animal : animales) {
            try {
                animal.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Clase que representa un recipiente de comida
    public static class Recipiente {

        private boolean ocupado = false;

        public synchronized boolean intentarComer() {
            if (!ocupado) {
                ocupado = true;
                return true;
            }
            return false;
        }

        public synchronized void liberar() {
            ocupado = false;
        }

    }

    // Clase que representa un animal
    public static class Animal implements Runnable {

        private String nombre;
        private Recipiente recipiente;
        private Random random = new Random();

        public Animal(String nombre, Recipiente recipiente) {
            this.nombre = nombre;
            this.recipiente = recipiente;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                moverse();
                hacerSonido();
            }
            if (recipiente.intentarComer()) {
                System.out.println(nombre + " está comiendo");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                recipiente.liberar();
            } else {
                System.out.println(nombre + " no puede comer porque el recipiente está ocupado");
            }
        }

        private void moverse() {
            System.out.println(nombre + " se está moviendo");
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void hacerSonido() {
            System.out.println(nombre + " está haciendo un sonido");
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
