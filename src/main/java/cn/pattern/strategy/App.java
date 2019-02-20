package cn.pattern.strategy;

public class App {
    public static void main(String[] args) {
        Duck duck = new RubberDuck();
        duck.perform();
        duck.display();

        Duck duck1 = new RedDuck();
        duck1.display();
        duck1.perform();

    }
}
