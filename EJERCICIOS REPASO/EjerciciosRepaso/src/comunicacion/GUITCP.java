package comunicacion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUITCP extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField numberField;
    private JTextField resultField;

    public GUITCP() {
        super("GUITCP");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Número:"));
        numberField = new JTextField(10);
        inputPanel.add(numberField);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        JPanel outputPanel = new JPanel();
        outputPanel.add(new JLabel("Resultado:"));
        resultField = new JTextField(10);
        resultField.setEditable(false);
        outputPanel.add(resultField);
        mainPanel.add(outputPanel, BorderLayout.CENTER);

        JButton verifyButton = new JButton("Verificar");
        verifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numberStr = numberField.getText().trim();

                if (!numberStr.isEmpty()) {
                    try {
                        int number = Integer.parseInt(numberStr);
                        String result = verifyNumber(number);
                        resultField.setText(result);
                    } catch (NumberFormatException ex) {
                        resultField.setText("El número no es válido");
                    }
                }
            }
        });
        mainPanel.add(verifyButton, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private String verifyNumber(int number) {
        try (Socket socket = new Socket("localhost", 12345);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

            output.println(number);

            return input.readLine();
        } catch (IOException e) {
            return "No se pudo conectar al servidor";
        }
    }

    public static void main(String[] args) {
        new GUITCP();
    }
}
