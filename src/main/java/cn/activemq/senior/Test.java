package cn.activemq.senior;

import java.util.Random;

public class Test {
    public static void main(String[] args) {

        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            int num = (int) (Math.random() * 5 + 1);
            int a = r.nextInt(5) + 1;
            System.out.println("a:" + a);
            System.out.println("num:" + num);
        }

    }
}
