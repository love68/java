package cn.j2se.chapter5;

public class Test1 {
    void f1(long i){
        System.out.println("f1(long)");
    }
    void f1(int i){
        System.out.println("f1(int)");
    }
    void f1(float i){
        f2(3);
        System.out.println("f1(float)");
    }
    void f2(long i){
        System.out.println("f1(long)");
    }

    void f1(double i){
        System.out.println("f1(double)");
    }

    public void test1(){
        f1(1);
        f2(1);
    }
}
