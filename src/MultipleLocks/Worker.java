package MultipleLocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Worker {

    private Random random = new Random();

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<Integer>();
    private List<Integer> list2 = new ArrayList<Integer>();

    private void stageOne(){
        synchronized (lock1) { //syncronizing codeblocks not methods
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt(100));
        }
    }

    private void stageTwo(){
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(random.nextInt(100));
        }
    }

    private void process(){
        for (int i = 0; i < 1000; i++){
            stageOne();
            stageTwo();
        }
    }

    void main() {
        System.out.println("Starting...");

        long start = System.currentTimeMillis();

        Thread t1 = new Thread((new Runnable() {
            public void run() {
                process();
            }
        }));



        Thread t2 = new Thread((new Runnable() {
            public void run() {
                process();
            }
        }));

        t1.start();
        t2.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("Time take: " + (end - start));
        System.out.println("List: " + list1.size() + "; List2: "  + list2.size());


    }
}
