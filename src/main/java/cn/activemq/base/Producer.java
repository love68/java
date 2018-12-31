package cn.activemq.base;

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

    private static ConnectionFactory factory = null;
    private static Connection connection = null;
    private static Session session = null;


    public static void main(String[] args) throws JMSException {
        factory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, URL);
        connection = factory.createConnection();
        connection.start();
        /*
         *第一个参数：是否打开事务,如果打开了事务，必须commit
         * 第二个参数：消息确认模式，一般设置为自动签收
         */
        session = connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);
        //通过Session创建Destination对象，指的是一个客户端用来指定生产消息目标和消费消息来源的对象
        // 在PTP模式中，Destination被称作Queue即队列；
        // 在Pub/Sub模式，Destination被称作Topic即主题。在程序中可以使用多个Queue和Topic。
        Destination queue = session.createQueue("queue");
        //创建一个消息生产者
        MessageProducer producer = session.createProducer(queue);
        //创建一个消息
        TextMessage message = session.createTextMessage("activemq");
        //发送消息
        producer.send(message);
        //session.commit();
        producer.close();
        connection.close();
        session.close();

    }
}
