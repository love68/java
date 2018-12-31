package cn.cpmodel;

import java.util.List;

/**
 * 消费者
 */
public class Consumer implements Runnable{

    private List<Task> tasks;

    public Consumer(List<Task> tasks){
        this.tasks = tasks;
    }

    public void run() {

    }
}
