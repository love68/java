package cn.j2se.chapter8;

public class Student extends Person {
    public void say(String s) {
        System.out.println("Student");
    }


    public void say() {
        System.out.println("Student");
    }

    public static void main(String[] args) {
        Person p = new Student();
//        p.say("ss");
        p.say();
    }
}
