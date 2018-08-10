package cn.e3mall.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;

import javax.jms.*;

/**
 * @author 王兴毅
 * @date 2018.08.09 11:52
 */
public class ActivemqTest {

    @Test
    public void testQueueProducer() throws Exception{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://39.108.111.94:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //第一个参数：事务，默认不开启
        //第二个：应答模式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test-queue");
        MessageProducer producer = session.createProducer(queue);
        //TextMessage textMessage = new ActiveMQTextMessage();
        //textMessage.setText("hello-activemq");
        TextMessage textMessage = session.createTextMessage("hello activemq");
        producer.send(textMessage);
        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testQueueConsumer() throws Exception{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://39.108.111.94:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("spring-queue");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                String text;
                try {
                    text = textMessage.getText();
                    System.out.println(text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testTopicProducer() throws Exception{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://39.108.111.94:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //第一个参数：事务，默认不开启
        //第二个：应答模式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("test-topic");
        MessageProducer producer = session.createProducer(topic);
        //TextMessage textMessage = new ActiveMQTextMessage();
        //textMessage.setText("hello-activemq");
        TextMessage textMessage = session.createTextMessage("hello activemq topic");
        producer.send(textMessage);
        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testTopicConsumer() throws Exception{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://39.108.111.94:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("test-topic");
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                String text;
                try {
                    text = textMessage.getText();
                    System.out.println(text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("topic消费者2启动...");
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }
}
