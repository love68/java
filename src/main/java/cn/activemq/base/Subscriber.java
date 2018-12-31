package cn.activemq.base;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.xml.soap.Text;

public class Subscriber {
    //用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //url
    private static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    private static ConnectionFactory factory = null;
    private static Connection connection = null;
    private static Session session = null;

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,URL);
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        Destination topic = session.createTopic("topic1");

        MessageConsumer subscriber1 = session.createConsumer(topic);
        MessageConsumer subscriber2 = session.createConsumer(topic);

        subscriber1.setMessageListener(new Listener1());
        subscriber2.setMessageListener(new Listener1());


    }
}
