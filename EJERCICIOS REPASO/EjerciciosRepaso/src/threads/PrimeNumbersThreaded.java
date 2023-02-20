package threads;

import java.util.ArrayList;

public class PrimeNumbersThreaded {
    public static void main(String[] args) {
        int numThreads = 10;
        int maxNumber = 1000;
        int chunkSize = maxNumber / numThreads;

        ArrayList<Thread> threads = new ArrayList<>();
        ArrayList<Integer> primes = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize + 1;
            int end = (i + 1) * chunkSize;
            if (i == numThreads - 1) {
                end = maxNumber;
            }

            Thread t = new Thread(new PrimeNumbersRunnable(start, end, primes));
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Prime numbers between 1 and " + maxNumber + ":");
        System.out.println(primes);
    }

    static class PrimeNumbersRunnable implements Runnable {
        int start;
        int end;
        ArrayList<Integer> primes;

        public PrimeNumbersRunnable(int start, int end, ArrayList<Integer> primes) {
            this.start = start;
            this.end = end;
            this.primes = primes;
        }

        @Override
        public void run() {
            for (int i = start; i <= end; i++) {
                if (isPrime(i)) {
                    synchronized (primes) {
                        primes.add(i);
                    }
                }
            }
        }

        private boolean isPrime(int n) {
            if (n <= 1) {
                return false;
            }

            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    return false;
                }
            }

            return true;
        }
    }
}
