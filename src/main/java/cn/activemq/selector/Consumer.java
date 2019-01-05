package cn.activemq.selector;

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

    private static String SELECTOR_MAN = "gender = '男'";
    private static String SELECTOR_AGE = "age > 30";

    private static String SELECTOR_MAN_AGE = "age > 30 and gender = '男'";

    private static Connection connection = null;
    private static Session session = null;

    public static void main(String[] args) throws Exception {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,URL);
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);

        Queue selector1 = session.createQueue("selector1");
        MessageConsumer consumer = session.createConsumer(selector1,SELECTOR_MAN_AGE);

        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                System.out.println("收到消息");
                if(message instanceof MapMessage){
                    MapMessage m = (MapMessage) message;
                    System.out.println(m.toString());
                    try {
                        System.out.println(m.getString("name"));
                        //System.out.println(m.getString("age"));
                        //System.out.println(m.getString("gender"));
                        m.acknowledge();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }
}
