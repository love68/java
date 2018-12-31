package cn.pattern.future.myfuture.myfuture;

import java.util.ArrayList;
import java.util.List;

public class RealData implements Data {

    private List<Student> list = new ArrayList<>();
    //protected String data;//真实的数据，获取速度很慢

    public RealData(Integer index){
        try {
            System.out.println("正常查询数据库");
            Thread.sleep(5000);
            Student s1 = new Student("张1","1");
            Student s2 = new Student("张2","2");
            Student s3 = new Student("张3","3");
            Student s4 = new Student("张4","4");
            Student s5 = new Student("张5","5");
            Student s6 = new Student("张6","6");
            Student s7 = new Student("张7","7");
            Student s8 = new Student("张8","8");
            Student s9 = new Student("张9","9");
            Student s10 = new Student("张10","10");
            List<Student> students = new ArrayList<>();
            students.add(s1);
            students.add(s2);
            students.add(s3);
            students.add(s4);
            students.add(s5);
            students.add(s6);
            students.add(s7);
            students.add(s8);
            students.add(s9);
            students.add(s10);
            /*
            for(int i =0;i<10;i++){
                System.out.println(i);
                if(i==index){
                    System.out.println(students.get(index));
                    list.add(students.get(index));
                }
            }*/
            list.add(students.get(index));
            System.out.println("查询结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getResult() throws InterruptedException {
        return list;
    }
}
