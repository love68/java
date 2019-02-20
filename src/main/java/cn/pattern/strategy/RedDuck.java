package cn.pattern.strategy;

public class RedDuck extends Duck {
    @Override
    public void display() {
        System.out.println("红色的鸭子。。。");
    }

    @Override
    public void perform() {
        flyBehavior = new FlyWithWings();
        flyBehavior.fly();

        quackBehavior = new Quack();
        quackBehavior.quack();

    }
}
