package NewRunnable;

public class Main {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
                     public void run() {
                         for (int i = 0; i < 10; i++) {
                             System.out.println("MultiThreading with 'new runnable 1: ' " + i);
                             try {
                                 Thread.sleep(100);
                             } catch (InterruptedException e) {
                                 e.printStackTrace();
                             }
                         }
            }
        });

        t1.start();
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("MultiThreading with 'new runnable 2: ' " + i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t2.start();
    }
}
