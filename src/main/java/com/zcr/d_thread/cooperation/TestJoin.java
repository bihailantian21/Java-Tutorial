package com.zcr.d_thread.cooperation;

public class TestJoin {

    private class A extends Thread {
        @Override
        public void run() {
            System.out.println("A");
        }
    }

    private class B extends Thread {

        private A a;

        B(A a) {
            this.a = a;
        }

        @Override
        public void run() {
            try {
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B");
        }
    }

    public void test() {
        A a = new A();
        B b = new B(a);
        b.start();
        a.start();
    }

    /**
     * A
     * B
     * @param args
     */
    public static void main(String[] args) {
        TestJoin example = new TestJoin();
        example.test();
    }

}