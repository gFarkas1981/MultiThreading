package Syncronized;

public class Main {

    private int count = 0;

    private synchronized void increment() { //at the same time only one thread can call a syncronized method
        count++; //1. get value, 2. increment value, 3. set value
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.doWork();
    }

    private void doWork(){

        Thread t1 = new Thread(new Runnable() {

            public void run() {

                for (int i = 0; i < 10000; i++){
                    increment();
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {

            public void run() {

                for (int i = 0; i < 10000; i++){
                    increment();
                }

            }
        });

        t1.start();
        t2.start();

        try {
            t1.join(); //waiting for the threads to finish
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        System.out.println("Count i: " + count);

    }
}
