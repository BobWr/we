//package com.baojk.we.redis;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//
///**
// * @author baojikui (bjklwr@outlook.com)
// * @date 2018/10/18
// */
//@Component
//@Configuration
//public class RedisUtil {
//
//    @Value("${jedis.pool.host}")
//    private String host;
//
//    @Value("${jedis.pool.port}")
//    private int port;
//
//    private JedisPool pool;
//
//    private boolean redisEnable = true;
//
//    @PostConstruct
//    public void init() {
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxIdle(50);
//        config.setMaxTotal(300);
//        config.setMaxWaitMillis(5000);
//        config.setTestOnBorrow(true);
//        config.setTestOnReturn(true);
//        pool = new JedisPool(config, host, Integer.valueOf(port), 3000);
//        Jedis resource = pool.getResource();
//        resource.close();
//    }
//
//    @PreDestroy
//    public void destory() {
//        pool.close();
//    }
//
//    protected Jedis getJedis() {
//        return pool.getResource();
//    }
//}
