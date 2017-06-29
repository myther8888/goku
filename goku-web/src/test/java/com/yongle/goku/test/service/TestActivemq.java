package com.yongle.goku.test.service;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

/**
 * Created by weinh on 2016/5/7.
 */
public class TestActivemq extends JUnitServiceBase {

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 可以实现，发送优先级消息，消息可以配置成持久化，MQ重启信息不会丢失
     * 持久化，优先级属性需要设置在jmsTemplate上，而不是消息对象上，否则属性会被覆盖而无效
     */
    @Test
    public void jmsTemplateSendQueueMQ() {
//        jmsTemplate.setPriority(3);
//        jmsTemplate.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//        jmsTemplate.send("test.queue", new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                Message message = session.createTextMessage(jmsTemplate.getPriority()
//                        + "-" + System.currentTimeMillis());
//                return message;
//            }
//        });

//        McMessage mcMessage = new McMessage();
//        mcMessage.setSubject("subject主题");
//        mcMessage.setContent("content内容");
//        mcMessage.setContact("yonglelaoren@163.com");
//        mcMessage.setType(SysDict.MessageType.email.getValue());
//        jmsTemplate.convertAndSend("message.send.queue", mcMessage);
    }

    /**
     * 接收消息不是按照优先级来，不知是配置有问题还是别的什么情况
     *
     * @throws JMSException
     * @throws InterruptedException
     */
    @Test
    public void jmsTemplateReceiveQueueMQ() throws JMSException, InterruptedException {
        while (true) {
            Thread.sleep(1500L);
            Object o = jmsTemplate.receiveAndConvert("test.queue");
            if (o != null) {
                if (o instanceof TextMessage) {
                    ((TextMessage) o).getText();
                } else {
                    System.out.println(o);
                }
            }
        }
    }

    /**
     * 主题消息，无法实现优先级和持久化，不知道是不是哪里配置有问题，消费者那边配置了clientid还是不生效
     *
     * @throws InterruptedException
     */
    @Test
    public void jmsTemplateSendTopicMQ() throws InterruptedException {
        Destination destination = new ActiveMQTopic("test.topic");
        jmsTemplate.setPriority(3);
        jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createTextMessage(jmsTemplate.getPriority()
                        + "-" + System.currentTimeMillis());
                return message;
            }
        });
    }

    /**
     * 接收主题消息
     *
     * @throws JMSException
     * @throws InterruptedException
     */
    @Test
    public void jmsTemplateReceiveTopicMQ() throws JMSException, InterruptedException {
        Destination destination = new ActiveMQTopic("test.topic");
        while (true) {
            Message message = jmsTemplate.receive(destination);
            System.out.println(message);
        }
    }

    /**
     * 通过activemq发送队列消息
     *
     * @throws JMSException
     */
    @Test
    public void activeMQSendQueueMQ() throws JMSException {
        Connection connection = jmsTemplate.getConnectionFactory().createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination dest = new ActiveMQQueue("test");
        MessageProducer producer = session.createProducer(dest);
//        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//        producer.setPriority(8);
        Message message = session.createTextMessage("带优先级消息，非持久化");
        producer.send(dest, message, DeliveryMode.PERSISTENT, 7, 0);
        connection.close();
    }

    /**
     * 通过activemq接收队列消息
     *
     * @throws JMSException
     * @throws InterruptedException
     */
    @Test
    public void activeMQReceiveQueueMQ() throws JMSException, InterruptedException {
        Connection connection = jmsTemplate.getConnectionFactory().createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination dest = new ActiveMQQueue("test");
        MessageConsumer consumer = session.createConsumer(dest);
        while (true) {
            Message o = consumer.receive();
            if (o != null) {
                TextMessage textMessage = (TextMessage) o;
                System.out.println(textMessage.getText());
            } else {
                break;
            }
        }
        connection.close();
    }


    public static void main(String[] args) throws InterruptedException {
//        System.out.println(TestA.a);
//        Thread.sleep(3000);
        new TestB();

    }

}
