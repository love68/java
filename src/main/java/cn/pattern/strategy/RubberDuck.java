package cn.pattern.strategy;

public class RubberDuck extends Duck {
    public void display(){
        System.out.println("展示为橡皮鸭");
    }

    public void perform(){
        flyBehavior = new FlyNoWay();
        flyBehavior.fly();

        quackBehavior = new Squack();

        quackBehavior.quack();

    }

}
