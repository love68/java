package cn.pattern.future.myfuture.myfuture;

public class Client {
    public Data request(Integer index) throws InterruptedException {
        FutureData futureData = new FutureData();
        Thread t = new Thread(new Runnable() {
            public void run() {
                RealData realData = new RealData(index);
                futureData.setRealData(realData);
            }
        });
        t.start();
        return futureData;
    }

}
