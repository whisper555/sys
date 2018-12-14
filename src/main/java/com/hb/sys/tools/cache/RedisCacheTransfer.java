package com.hb.sys.tools.cache;

/**
 * 描述： redisfactory静态注入中间类
 * 作者： hanbin
 * 时间： 2018-10-18 14:50
 **/
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;


public class RedisCacheTransfer{

    //@Autowired
    public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        System.out.println("========jedisConnectionFactory注入成功！jedisConnectionFactory="+jedisConnectionFactory+"========");
        RedisCache.setJedisConnectionFactory(jedisConnectionFactory);

    }

}