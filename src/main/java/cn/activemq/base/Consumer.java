package cn.activemq.base;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer {
    //用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //url
    private static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    private static Connection connection = null;
    private static Session session = null;

    public static void main(String[] args) throws Exception {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,URL);
        connection = factory.createConnection();
        connection.start();

        session = connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);

        Destination queue = session.createQueue("queue");

        MessageConsumer consumer = session.createConsumer(queue);

        while(true){
            Thread.sleep(2000);
            TextMessage message = (TextMessage)consumer.receive();
            if(null==message) continue;
            System.out.println(message.getText());
            message.acknowledge();
        }

    }

}
