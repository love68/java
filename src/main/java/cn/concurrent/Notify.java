package cn.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * notify和wait不是实时的
 */
public class Notify {
    private static Object lock = new Object();
    private static List<String> list = new ArrayList<String>();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {

            public void run() {
                for(int i = 0;i<10;i++){
                    synchronized (lock){
                        if(list.size()==5){
                            lock.notify();//执行到这的时候，notify只是唤醒了线程，并没有释放锁
                        }
                        list.add(i+"");
                        System.out.println("list加入了"+i);
                    }
                }
            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    if(list.size()!=5) {
                        try {
                            System.out.println("t2进入");
                            lock.wait();
                            System.out.println("t2被唤醒了");
                            System.out.println("t2退出");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        },"t2");

        t2.start();
        t1.start();

    }


}
