package cn.e3mall.activemq;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 王兴毅
 * @date 2018.08.09 20:32
 */
public class msgConsumer {

    @Test
    public void msgcTest() throws Exception{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
        System.in.read();
    }
}
