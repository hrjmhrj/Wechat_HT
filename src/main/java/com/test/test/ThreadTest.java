package com.test.test;

/**
 * @author ZShua
 */
public class ThreadTest {

    public static void main(String[] args) {
        MyThread2 mt1 = new MyThread2("线程A ");
        MyThread2 mt2 = new MyThread2("线程B ");
        mt1.start();
        mt2.start();
    }

}


class MyThread2 extends Thread {
    private String name;

    public MyThread2(String name) {
        this.name = name;
    }

    @Override
    public void run() {  // 覆写run()方法，作为线程 的操作主体
        for (int i = 0; i < 10; i++) {
            System.out.println(name + "运行，i = " + i);
        }
    }
};
