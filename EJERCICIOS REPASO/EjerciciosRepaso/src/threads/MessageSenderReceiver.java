package threads;

import java.util.Scanner;

public class MessageSenderReceiver {
    private static final int BUFFER_SIZE = 10;
    
    private static String[] buffer = new String[BUFFER_SIZE];
    private static int numItemsInBuffer = 0;
    private static int nextIndexToProduce = 0;
    private static int nextIndexToConsume = 0;
    
    public static void main(String[] args) {
        Thread senderThread = new Thread(() -> {
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    // Wait for buffer to have an empty slot
                    while (numItemsInBuffer == BUFFER_SIZE) {
                        Thread.yield(); // Let other threads run
                    }
                    
                    // Get message from user
                    System.out.println("Enter a message to send: ");
                    String message = scanner.nextLine();
                    
                    // Send message
                    buffer[nextIndexToProduce] = message;
                    numItemsInBuffer++;
                    nextIndexToProduce = (nextIndexToProduce + 1) % BUFFER_SIZE;
                }
            }
        });
        
        Thread receiverThread = new Thread(() -> {
            while (true) {
                // Wait for buffer to have an item
                while (numItemsInBuffer == 0) {
                    Thread.yield(); // Let other threads run
                }
                
                // Receive message
                String message = buffer[nextIndexToConsume];
                numItemsInBuffer--;
                nextIndexToConsume = (nextIndexToConsume + 1) % BUFFER_SIZE;
                System.out.println("Received message: " + message);
            }
        });
        
        senderThread.start();
        receiverThread.start();
    }
}
