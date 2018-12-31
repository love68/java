package cn.activemq.base;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class Listener1 implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("订阅者一收到的消息：" + ((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}


