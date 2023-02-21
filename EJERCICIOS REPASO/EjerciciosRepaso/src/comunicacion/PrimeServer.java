package comunicacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PrimeServer {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java PrimeServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server is listening on port " + portNumber);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                Thread thread = new PrimeThread(clientSocket);
                thread.start();
            }
        } catch (IOException e) {
            System.err.println("Error occurred while listening for connections: " + e.getMessage());
            System.exit(1);
        }
    }

    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    private static class PrimeThread extends Thread {
        private Socket clientSocket;

        public PrimeThread(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String inputLine = input.readLine();
                int number = Integer.parseInt(inputLine);

                if (isPrime(number)) {
                    output.println(number + " is prime");
                } else {
                    output.println(number + " is not prime");
                }
            } catch (IOException e) {
                System.err.println("Error occurred while processing client request: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("Error occurred while closing client socket: " + e.getMessage());
                }
            }
        }
    }
}
