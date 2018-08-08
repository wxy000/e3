package cn.e3mall.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author 王兴毅
 * @date 2018.08.07 11:59
 */
public class JedisTest {
    @Test
    public void testJedis() throws Exception{
        Jedis jedis = new Jedis("39.108.111.94", 6379);
        jedis.set("test", "my first test jedis");
        String s = jedis.get("test");
        System.out.println(s);
        jedis.close();
    }

    @Test
    public void testJedisPool() throws Exception{
        JedisPool jedisPool = new JedisPool("39.108.111.94", 6379);
        Jedis jedis = jedisPool.getResource();
        String s = jedis.get("test");
        System.out.println(s);
        jedis.close();
        jedisPool.close();
    }
}
