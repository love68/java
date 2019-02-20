package cn.pattern.strategy;

public class Duck {
    protected FlyBehavior flyBehavior;
    protected QuackBehavior quackBehavior;

    public void swim(){
        System.out.println("游泳了。。。");
    }

    public void display(){

    }

    public void perform(){

    }

}
