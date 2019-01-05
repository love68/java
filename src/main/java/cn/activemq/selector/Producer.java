package cn.activemq.selector;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {
    //用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //url
    private static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;


    private static Connection connection = null;
    private static Session session = null;

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,URL);
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);

        Queue selector1 = session.createQueue("selector1");
        MessageProducer producer = session.createProducer(selector1);

        MapMessage message1 = session.createMapMessage();
        message1.setString("name","张1");
        message1.setIntProperty("age",23);//selector只能过滤property中的字段，但是这些字段消费的时候是没有的
        message1.setStringProperty("gender","男");
        MapMessage message2 = session.createMapMessage();
        message2.setString("name","张2");
        message2.setIntProperty("age",33);
        message2.setStringProperty("gender","女");
        MapMessage message3 = session.createMapMessage();
        message3.setString("name","张3");
        message3.setIntProperty("age",43);
        message3.setStringProperty("gender","男");
        MapMessage message4 = session.createMapMessage();
        message4.setString("name","张4");
        message4.setIntProperty("age",53);
        message4.setStringProperty("gender","女");

        producer.send(message1);
        producer.send(message2);
        producer.send(message3);
        producer.send(message4);

        connection.close();

    }

}
