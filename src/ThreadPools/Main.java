package ThreadPools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {

    private int id;

    Processor(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println("Task nr.: " + id + " started");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Task nr.: " + id + " completed");

    }

}

public class Main {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2); //setting up executor to execute two threads
        System.out.println("Starting...");

        long start = System.currentTimeMillis();

        for (int i = 0; i < 5; i++){
            executor.submit(new Processor(i));
        }

        executor.shutdown();

        System.out.println("All tasks submitted.");

        try {
            executor.awaitTermination(1, TimeUnit.DAYS); //terminating execution after 1 day of idle time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        System.out.println("All tasks completed in " + (end - start) + " millisecond.");

    }
}
