package cn.activemq.senior;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer implements Runnable {
    //用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //url
    private static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private MessageProducer producer = null;


    private String name;
    public  Consumer(String name){
        this.name = name;
    }


    @Override
    public void run() {
       try{
           factory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,URL);
           connection=factory.createConnection();
           connection.start();
           session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
           Destination queue = session.createQueue(name);
           MessageConsumer consumer = session.createConsumer(queue);
           while (true){
               TextMessage message = (TextMessage) consumer.receive();
               if(message==null) return;
               System.out.println(name + "消费了" + message.getText());
               Thread.sleep(5);
           }
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           try {
               producer.close();
               connection.close();
               session.close();
           } catch (Exception e) {
               e.printStackTrace();
           }
       }

    }
}
