package com.urise.webapp;

public class TestThread {
    private static final Object LOCK_1 = new Object();
    private static final Object LOCK_2 = new Object();

    public static void main(String[] args) {
        MyThread1 t1 = new MyThread1();
        MyThread2 t2 = new MyThread2();

        t1.start();
        t2.start();

    }

    public static class MyThread1 extends Thread {
        @Override
        public void run() {
            synchronized (LOCK_1) {
                System.out.println("Поток 1: захватил блокировку 1");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {}

                System.out.println("Поток 1: ожидает блокировку 2");
                synchronized (LOCK_2) {
                    System.out.println("Поток 1: удерживает блокировки 1 и 2");
                }
            }
        }
    }

    public static class MyThread2 extends Thread {
        @Override
        public void run() {
            synchronized (LOCK_2) {
                System.out.println("Поток 2: захватил блокировку 2");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {}

                System.out.println("Поток 2: ожидает блокировку 1");
                synchronized (LOCK_1) {
                    System.out.println("Поток 2: удерживает блокировки 1 и 2");
                }
            }
        }
    }

}
