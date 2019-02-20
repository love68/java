package cn.pattern.strategy;

public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("鸭子飞起来了。。。");
    }
}
