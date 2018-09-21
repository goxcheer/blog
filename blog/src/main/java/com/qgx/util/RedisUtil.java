package com.qgx.util;

import com.sun.org.apache.xml.internal.utils.SerializableLocatorImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 *@Author: goxcheer
 *@Date:19:51 2018/9/8
 *@email:604721660@qq.com
 *@decription:redis工具类
 */
@Component
public final class RedisUtil {

    @Resource
    private RedisTemplate<Serializable, Object> redisTemplate;

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("182.61.52.221", 6379);  //创建客户端，1p和端口号
        jedis.auth("123456");
        jedis.set("name", "齐高相");
        String value = jedis.get("name");
        System.out.println(value);
        jedis.close(); //释放连接资源

    }
}
