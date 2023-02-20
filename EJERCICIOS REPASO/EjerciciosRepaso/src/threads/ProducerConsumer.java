package threads;

import java.util.Random;

public class ProducerConsumer {
    private static final int BUFFER_SIZE = 10;

    private static int[] buffer = new int[BUFFER_SIZE];
    private static int numItemsInBuffer = 0;
    private static int nextIndexToProduce = 0;
    private static int nextIndexToConsume = 0;

    public static void main(String[] args) {
        Thread producerThread = new Thread(() -> {
            Random rand = new Random();
            while (true) {
                // Wait for buffer to have an empty slot
                while (numItemsInBuffer == BUFFER_SIZE) {
                    Thread.yield(); // Let other threads run
                }

                // Produce item
                int item = rand.nextInt(100);
                buffer[nextIndexToProduce] = item;
                numItemsInBuffer++;
                nextIndexToProduce = (nextIndexToProduce + 1) % BUFFER_SIZE;
            }
        });

        Thread consumerThread = new Thread(() -> {
            while (true) {
                // Wait for buffer to have an item
                while (numItemsInBuffer == 0) {
                    Thread.yield(); // Let other threads run
                }

                // Consume item
                int item = buffer[nextIndexToConsume];
                numItemsInBuffer--;
                nextIndexToConsume = (nextIndexToConsume + 1) % BUFFER_SIZE;
                System.out.println(item);
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}