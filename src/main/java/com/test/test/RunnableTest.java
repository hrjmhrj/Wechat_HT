package com.test.test;

/**
 * @author ZShua
 */

public class RunnableTest {
    public static void main(String[] args){
        MyThread mt1 = new MyThread("线程A ") ;
        MyThread mt2 = new MyThread("线程B ") ;
        Thread t1 = new Thread(mt1) ;
        Thread t2 = new Thread(mt2) ;
        t1.start() ;    // 启动多线程
        t2.start() ;    // 启动多线程
    }
};
/**
 实现Runnable接口，作为线程的实现类
 */
 class MyThread implements Runnable{
    private String name ;
    public MyThread(String name){
        // 通过构造方法配置name属性
        this.name = name ;
    }
    @Override
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println(name + "运行，i = " + i) ;
        }
    }
};




