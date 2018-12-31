package cn.pattern.future.myfuture.myfuture;

import java.util.List;

/**
 * FutureData是Future模式的关键，它实际上是真实数据RealData的代理，封装了获取RealData的等待过程
 */
public class FutureData implements Data {
    private RealData realData = null;//真实数据
    boolean isReady = false;  //是否已经准备好

    public synchronized void setRealData(RealData realData){
        if(isReady){//ready
            return;
        }
        this.realData = realData;
        this.isReady = true;
        notifyAll();//唤醒等待的线程
    }


    public synchronized List<Student> getResult() throws InterruptedException {
        if(!isReady){//没有准备好
            wait();
        }
        return this.realData.getResult();
    }
}
