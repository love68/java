package cn.j2se.chapter5;

public class ArrayDemo1 {
    public static void main(String[] args) {
        int[] a1={1};
        System.out.println(a1[0]);//1
        int[] a2=a1;
        System.out.println(a2[0]);//1
        a2[0] = 2;
        System.out.println(a1[0]);//2

    }
}
