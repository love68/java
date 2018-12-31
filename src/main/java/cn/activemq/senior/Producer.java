package cn.activemq.senior;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Random;

public class Producer implements Runnable {
    //用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //url
    private static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private MessageProducer producer1 = null;
    private MessageProducer producer2 = null;
    private MessageProducer producer3 = null;
    private MessageProducer producer4 = null;
    private MessageProducer producer5 = null;
    private int count = 0;

    @Override
    public void run() {
        try {
            factory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, URL);
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination queue1 = session.createQueue("queue1");
            Destination queue2 = session.createQueue("queue2");
            Destination queue3 = session.createQueue("queue3");
            Destination queue4 = session.createQueue("queue4");
            Destination queue5 = session.createQueue("queue5");
            producer1 = session.createProducer(queue1);
            producer2 = session.createProducer(queue2);
            producer3 = session.createProducer(queue3);
            producer4 = session.createProducer(queue4);
            producer5 = session.createProducer(queue5);

            while (count < 100) {
                count++;
                //Thread.sleep(1000);
                int r = this.generateRandom();
                TextMessage message = session.createTextMessage("activemq消息" + r);
                switch (r) {
                    case 1:
                        //默认是持久化的
                        producer1.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                        producer1.send(message);
                        break;
                    case 2:
                        producer2.send(message);
                        break;
                    case 3:
                        producer3.send(message);
                        break;
                    case 4:
                        producer4.send(message);
                        break;
                    case 5:
                        producer5.send(message);
                        break;
                    default:
                        System.out.println("错误");
                        break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                producer1.close();
                producer2.close();
                producer3.close();
                producer4.close();
                producer5.close();
                connection.close();
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public int generateRandom() {
        Random r = new Random();
        int a = r.nextInt(5) + 1;
        return a;
    }

}
