package com.test.test;


 /**
  * @author ZShua
  */
 public class ThreadInterruptDemo{
     public static void main(String args[]){
                 MyThread3 mt = new MyThread3() ;
                 Thread t = new Thread(mt,"线程");
                 t.start() ; // 启动线程
                 try{
                         Thread.sleep(2000) ;
                     }catch(InterruptedException e){
                         System.out.println("3、休眠被终止") ;
                     }
                 t.interrupt() ; // 中断线程执行
             }
};


class MyThread3 implements Runnable{
    @Override
    public void run(){  // 覆写run()方法
        System.out.println("1、进入run()方法") ;
        try{
            Thread.sleep(10000) ;
            System.out.println("2、已经完成了休眠") ;
        }catch(InterruptedException e){
            System.out.println("3、休眠被终止======") ;
            return ; // 返回调用处
        }
        System.out.println("4、run()方法正常结束") ;
    }
};
