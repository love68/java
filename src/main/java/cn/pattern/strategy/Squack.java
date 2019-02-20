package cn.pattern.strategy;

public class Squack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("这个鸭子不会叫、、、");
    }
}
