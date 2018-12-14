package com.hb.sys.tools.cache;


import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.*;

/**
 * 描述：两级缓存，一级:ehcache,二级为redisCache
 * 作者： hanbin
 * 时间： 2018-10-29 16:39
 **/



public class EhRedisCache implements Cache {

    private static final Logger logger = LoggerFactory.getLogger(EhRedisCache.class);

    private String name;

    /*** 一定容量的LRU队列  ehcache*/
    private net.sf.ehcache.Cache ehCache;

    /*** 无容量限制key带时效性 redis*/
    private RedisTemplate<String, Object> redisTemplate;

    private long liveTime = 100; //默认1h=1*60*60

    private int activeCount = 10;


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this;
    }


    @Override
    public ValueWrapper get(Object key) {
        Element value = ehCache.get(key);
        logger.info("---------ehcache缓存--------Cache Level1 (ehcache) :{}={}",key,value);
        System.out.println("");
        if (value!=null) {
            //TODO 访问10次EhCache 强制访问一次redis 使得数据不失效
            if(value.getHitCount() < activeCount){
                return (value != null ? new SimpleValueWrapper(value.getObjectValue()) : null);
            }else{
                value.resetAccessStatistics();
            }
        }
        final String keyStr = key.toString();
        Object objectValue = redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                byte[] key = keyStr.getBytes();
                byte[] value = connection.get(key);
                if (value == null) {
                    //
                    return null;
                }
                //每次获得，重置缓存过期时间
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return toObject(value);
            }
        },true);
        ehCache.put(new Element(key, objectValue));//取出来之后缓存到本地
        logger.info("---------redis缓存----------Cache Level2 (redis) :{}={}",key,objectValue);
        System.out.println("");
        return  (objectValue != null ? new SimpleValueWrapper(objectValue) : null);

    }

    @Override
    public <T> T get(Object o, Class<T> aClass) {
        return null;
    }


    @Override
    public void put(Object key, Object value) {
        ehCache.put(new Element(key, value));
        final String keyStr =  key.toString();
        final Object valueStr = value;
        redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection)
                    throws DataAccessException {
                byte[] keyb = keyStr.getBytes();
                byte[] valueb = toByteArray(valueStr);
                connection.set(keyb, valueb);
                if (liveTime > 0) {
                    connection.expire(keyb, liveTime);
                }
                return 1L;
            }
        },true);

    }

    @Override
    public void evict(Object key) {
        ehCache.remove(key);
        final String keyStr =  key.toString();
        redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection)
                    throws DataAccessException {
                return connection.del(keyStr.getBytes());
            }
        },true);
    }

    @Override
    public void clear() {
        ehCache.removeAll();
        redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.flushDb();
                return "clear done.";
            }
        },true);
    }

    public net.sf.ehcache.Cache getEhCache() {
        return ehCache;
    }

    public void setEhCache(net.sf.ehcache.Cache ehCache) {
        this.ehCache = ehCache;
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public long getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(long liveTime) {
        this.liveTime = liveTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(int activeCount) {
        this.activeCount = activeCount;
    }

    private byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }


    private Object toObject(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }

    @Override
    public String toString() {
        return "EhRedisCache{" +
                "name='" + name + '\'' +
                ", ehCache=" + ehCache +
                ", redisTemplate=" + redisTemplate +
                ", liveTime=" + liveTime +
                ", activeCount=" + activeCount +
                '}';
    }
}
