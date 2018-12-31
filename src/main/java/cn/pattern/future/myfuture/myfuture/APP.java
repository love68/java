package cn.pattern.future.myfuture.myfuture;

public class APP {
    public static void main(String[] args) throws InterruptedException {
        Client c = new Client();
        long t1 = System.currentTimeMillis();
        //请求数据,这里会立即返回，因为获取的是FutureData，而非RealData
        Data data = c.request(4);

        System.out.println("开始处理主业务逻辑");
        //在处理这些业务逻辑过程中，RealData也正在创建，从而充分了利用等待时间
        Thread.sleep(2000);//模拟业务逻辑
        System.out.println("处理主业务逻辑结束");
        //使用真实数据
        System.out.println("数据="+data.getResult());
        System.out.println(System.currentTimeMillis()-t1);
        System.out.println("结束");
    }
}
