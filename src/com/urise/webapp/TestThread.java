package com.urise.webapp;

public class TestThread {
    private static final Object LOCK_1 = new Object();
    private static final Object LOCK_2 = new Object();

    public static void main(String[] args) {
        MyThread t1 = new MyThread(1, LOCK_1, LOCK_2);
        MyThread t2 = new MyThread(2, LOCK_2, LOCK_1);

        t1.start();
        t2.start();
    }


    public static class MyThread extends Thread {
        private final int numThread;
        private final Object lock1;
        private final Object lock2;

        public MyThread(int numThread, Object lock1, Object lock2) {
            this.numThread = numThread;
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println("Поток " + numThread + ": захватил блокировку 1");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                System.out.println("Поток " + numThread + ": ожидает блокировку 2");
                synchronized (lock2) {
                    System.out.println("Поток " + numThread + ": удерживает блокировки 1 и 2");
                }
            }
        }
    }
}
