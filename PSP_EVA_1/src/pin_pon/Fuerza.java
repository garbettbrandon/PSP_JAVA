package pin_pon;

import java.util.ArrayList;

public class Fuerza {
	int fuerza = 0;
	int turno;
	ArrayList<Number> marcadorJugador = new ArrayList<Number>();

	public Fuerza(int fuerza, int turno) {
		this.fuerza = fuerza;
		this.turno = turno;
	}

	public int getFuerza() {
		return fuerza;
	}

	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public void cambiaTurno() {
		if (this.turno == 1) {
			this.turno = 2;
		} else {
			this.turno = 1;
		}
	}

	public void anadirPunto(int id) {
		this.marcadorJugador.add(id);
	}

	public int puntuacion() {
		int ptos1 = 0;
		int ptos2 = 0;

		for (Number j : marcadorJugador) {
			if (j == (Number) 1) {
				ptos1++;
			}

			if (j == (Number) 2) {
				ptos2++;
			}
		}

		System.out.println("Jugador 1: " + ptos1 + " || Jugador 2: " + ptos2);
		return ptos1 > ptos2 ? ptos1 : ptos2;
	}
}