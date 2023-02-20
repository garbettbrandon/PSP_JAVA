package threads;

import java.util.Random;

public class SumArrayInParallel {
    private static final int ARRAY_LENGTH = 1000;
    private static final int NUM_THREADS = 4;
    private static final int CHUNK_SIZE = ARRAY_LENGTH / NUM_THREADS;

    private static int[] array = new int[ARRAY_LENGTH];
    private static int[] partialSums = new int[NUM_THREADS];

    public static void main(String[] args) {
        // Generate random array
        Random rand = new Random();
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            array[i] = rand.nextInt(10); // Generate random number between 0 and 9
        }

        // Create and start threads
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadNum = i;
            threads[i] = new Thread(() -> {
                int startIndex = threadNum * CHUNK_SIZE;
                int endIndex = startIndex + CHUNK_SIZE;
                int sum = 0;
                for (int j = startIndex; j < endIndex; j++) {
                    sum += array[j];
                }
                partialSums[threadNum] = sum;
            });
            threads[i].start();
        }

        // Wait for all threads to finish
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Calculate total sum
        int totalSum = 0;
        for (int sum : partialSums) {
            totalSum += sum;
        }

        // Print results
        System.out.println("Array:");
        printArray(array);
        System.out.println("Partial sums:");
        printArray(partialSums);
        System.out.println("Total sum: " + totalSum);
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
            if (i % 20 == 19) { // Print 20 numbers per line
                System.out.println();
            }
        }
        System.out.println();
    }
}