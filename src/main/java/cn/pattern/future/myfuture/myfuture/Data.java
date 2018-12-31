package cn.pattern.future.myfuture.myfuture;


import java.util.List;

public interface Data {
    List<Student> getResult() throws InterruptedException;
}
