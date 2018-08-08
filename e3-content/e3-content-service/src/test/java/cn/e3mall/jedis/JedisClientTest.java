package cn.e3mall.jedis;

import cn.e3mall.common.jedis.JedisClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 王兴毅
 * @date 2018.08.07 13:08
 */
public class JedisClientTest {
    @Test
    public void testJedisClient() throws Exception{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
        JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
        jedisClient.set("test", "my second test jedis");
        String s = jedisClient.get("test");
        System.out.println(s);
    }
}
