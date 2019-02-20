package cn.pattern.strategy;

public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("这个鸭子不会飞。。。");
    }
}
