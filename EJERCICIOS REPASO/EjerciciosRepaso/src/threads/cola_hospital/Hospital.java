package threads.cola_hospital;

import java.util.ArrayList;
import java.util.Random;

class Hospital {
    private ArrayList<String> waitingRoom;
    private final Object lock;

    public Hospital() {
        waitingRoom = new ArrayList<>();
        lock = new Object();
    }

    public void addPatient(String patientName) {
        synchronized (lock) {
            waitingRoom.add(patientName);
            System.out.println(patientName + " ha llegado al hospital y está esperando en la sala de espera.");
            lock.notifyAll();
        }
    }

    public String getNextPatient() {
        synchronized (lock) {
            while (waitingRoom.isEmpty()) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String nextPatient = waitingRoom.remove(0);
            return nextPatient;
        }
    }
}

class Enfermero implements Runnable {
    private final Hospital hospital;
    private final Random rand;

    public Enfermero(Hospital hospital) {
        this.hospital = hospital;
        rand = new Random();
    }

    @Override
    public void run() {
        String[] pacientes = {"Juan", "Pedro", "María", "Luis", "Ana", "Sofía"};
        for (String paciente : pacientes) {
            hospital.addPatient(paciente);
            try {
                Thread.sleep(rand.nextInt(5000)); // espera aleatoria
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Doctor implements Runnable {
    private final int id;
    private final Hospital hospital;

    public Doctor(int id, Hospital hospital) {
        this.id = id;
        this.hospital = hospital;
    }

    @Override
    public void run() {
        while (true) {
            String patient = hospital.getNextPatient();
            System.out.println("Doctor " + id + " está atendiendo a " + patient);
            try {
                Thread.sleep(new Random().nextInt(5000)); // simulación de atención
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

