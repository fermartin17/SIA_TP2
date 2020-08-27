package TP;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class RedisServiceTest {

    Jedis jedis;

    @Before
    public void setup(){
        jedis = new Jedis("localhost");
    }

    @Test
    public void pingTest(){
        Assert.assertEquals("PONG", jedis.ping());
    }
}
