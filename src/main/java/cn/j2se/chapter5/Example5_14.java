package cn.j2se.chapter5;

class Apple{
    static String name ="橘子";
    static String age;
    static {
        age = "18";
        System.out.println(name + age);
    }
}

public class Example5_14 {
    public static void main(String[] args) {
        new Apple();
    }
}
