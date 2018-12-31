package cn.activemq.senior;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        Thread p1 = new Thread(new Producer());
        p1.start();

        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 5; i++) {
            pool.submit(new Consumer("queue" + i));
        }
    }
}
