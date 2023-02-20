package threads.cola_hospital;

public class HospitalSimulator {
    public static void main(String[] args) {
        Hospital hospital = new Hospital();

        // Crear hilo de la enfermera
        Thread enfermeraThread = new Thread(new Enfermero(hospital));
        enfermeraThread.start();

        // Crear hilos de los doctores
        int numDoctores = 3;
        for (int i = 1; i <= numDoctores; i++) {
            Thread doctorThread = new Thread(new Doctor(i, hospital));
            doctorThread.start();
        }
    }
}
