package ejercicios;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import hilos.Contador;

public class Futbol extends JFrame {

	private static final long serialVersionUID = 4250627773234114346L;

	public Futbol() {
		super("Futbol");
		// Objeto de sincronización
		Object sync = new Object();
		// Obtener los milisegundos
		Instant instant = Instant.now();
		String seg = Integer.toString(instant.getNano());
		String mili = seg.substring(0, 1);
		JLabel l = new JLabel();
		l.setText("");
		setLayout(new FlowLayout());
		getContentPane().add(l);
		JButton b = new JButton("Pulsa");
		JButton bPara = new JButton("Para");
		JLabel g = new JLabel();
		g.setText("GOLES:");
		Contador c = new Contador(l);
		int goles = 0;
		JLabel gol = new JLabel();
		gol.setText(Integer.toString(goles));

		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread(c);
				t.start();
			}
		});
		getContentPane().add(b);
		getContentPane().add(bPara);
		getContentPane().add(g);
		getContentPane().add(gol);
		setSize(500, 400);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Futbol();

	}
}