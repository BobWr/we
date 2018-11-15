//package com.baojk.we.redis;
//
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.Jedis;
//
//import javax.annotation.Resource;
//
///**
// * @author baojikui (bjklwr@outlook.com)
// * @date 2018/10/18
// */
//@Component
//public class RedisManager {
//
//    @Resource
//    protected RedisUtil cacheClient;
//
//    public String addToken(String token, Integer id) {
//
//        Jedis jedis = cacheClient.getJedis();
//        jedis.set(token, id + "");
//        jedis.expire(token, 60 * 60);
//        return jedis.get(token);
//    }
//
//    public Integer getValue(String token) {
//
//        Jedis jedis = cacheClient.getJedis();
//        return Integer.parseInt(jedis.get(token));
//    }
//
//    public boolean isTokenExist(String token) {
//
//        Jedis jedis = cacheClient.getJedis();
//        return !(jedis.get(token) == null || jedis.get(token).isEmpty());
//    }
//
//    public boolean delToken(String token) {
//
//        Jedis jedis = cacheClient.getJedis();
//        return jedis.del(token) > 0;
//    }
//}
