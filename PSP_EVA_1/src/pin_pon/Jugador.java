package pin_pon;

public class Jugador implements Runnable {
	private Fuerza f;
	private int id;
	private boolean continuar = true;
	int interno;

	public Jugador(int id, Fuerza f) {
		this.id = id;
		this.f = f;
	}

	@Override
	public void run() {

		while (continuar && interno < 600000) {
			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (f) {
				if (f.getTurno() == id) {
					System.out.println(id + " Es mi turno");
					System.out.println(id + "Golpeo a la bola");
					int fuerzaBola = f.getFuerza();
					f.setFuerza(golpear());
					System.out.println("compruebo si doy a la bola");
					if (fuerzaBola < f.getFuerza()) {
						System.out.println("LA HE DADO");
						f.cambiaTurno();
					} else {
						System.out.println("HE FALLADO");
						if (f.getTurno() == 1) {
							f.anadirPunto(2);
						} else {
							f.anadirPunto(1);
						}
						f.setFuerza(0);

						if (f.puntuacion() == 5) {
							this.continuar = false;
						}

						System.out.println("\n*****NUEVO JUEGO****");
					}
					f.notify();
				}

			}
			interno++;
		}
		Thread.currentThread().interrupt();
	}

	public int golpear() {
		return (int) Math.round(Math.random() * (100 - 1) + 1);
	}
}