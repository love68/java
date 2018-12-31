package cn.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MyCountDownLatch {
    private static CountDownLatch latch = new CountDownLatch(1);
    private static List<String> list = new ArrayList<String>();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    if(list.size() == 5){
                        System.out.println("容器已经5个了");
                        latch.countDown();//实时通知线程2
                    }
                    list.add(i+"");
                    System.out.println("t1加入了"+i);
                }
            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                if(list.size()!=5){
                    try {
                        System.out.println("t2进入");
                        latch.await();//t2线程等待
                        System.out.println("t2退出");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"t2");
        t2.start();
        t1.start();
    }
}
