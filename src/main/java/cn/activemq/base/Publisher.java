package cn.activemq.base;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Publisher {
    //默认连接用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //默认连接密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //默认的连接地址
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    //发送的消息数量
    private static final int SENNUM = 10;

    public static void main(String[] args){
        ConnectionFactory factory ; //连接工厂
        Connection connection = null ; //连接
        Session session ; //会话，接收或者发送消息的线程
        Destination destination; //消息的目的地
        MessageProducer messageProducer; //消息生产者
        //实例化连接工厂
        factory = new ActiveMQConnectionFactory(Publisher.USERNAME, Publisher.PASSWORD, Publisher.BROKEURL);
        //通过连接工厂获取connection
        try {
            connection = factory.createConnection();
            connection.start(); //启动连接
            //创建session
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            //创建消息队列
//			destination = session.createQueue("FirstQueue");

            //创建主题
            destination = session.createTopic("topic1");
            //创建消息发布者
            messageProducer = session.createProducer(destination);
            //发送消息
            sendMessage(session, messageProducer);
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally{
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 发送消息
     * @param session
     * @param mp
     * @throws JMSException
     */
    public static void sendMessage(Session session, MessageProducer mp) throws JMSException{
        for(int i = 0;i<Publisher.SENNUM;i++){
            TextMessage message = session.createTextMessage("ActiveMq 发布的消息" + i);
            System.out.println("发布消息：" + "ActiveMq 发布的消息" + i);
            mp.send(message);
        }
    }

}
