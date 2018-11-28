package com.baojk.we.redis;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/18
 */
@Component
public class RedisManager {

    @Resource
    protected RedisUtil cacheClient;

    public String addToken(String token, Integer id) {

        //todo 优化 目前是保存一个id->token,再保存一个token->id
        Jedis jedis = cacheClient.getJedis();
        if (!StringUtils.isEmpty(jedis.get(id + ""))) {
            String tmp = jedis.get(id + "");
            jedis.del(tmp);
            jedis.del(id + "");
        }

        jedis.set(token, id + "");
        jedis.expire(token, 60 * 60);

        jedis.set(id + "", token);
        jedis.expire(id + "", 60 * 60);

        return jedis.get(token);
    }

    public Integer getValue(String token) {

        Jedis jedis = cacheClient.getJedis();
        return StringUtils.isEmpty(jedis.get(token)) ? null : Integer.parseInt(jedis.get(token));
    }

    public boolean isTokenExist(String token) {

        Jedis jedis = cacheClient.getJedis();
        return !(jedis.get(token) == null || jedis.get(token).isEmpty());
    }

    public boolean delToken(String token) {

        Jedis jedis = cacheClient.getJedis();

        String tmp = jedis.get(token);
        jedis.del(tmp);
        return jedis.del(token) > 0;
    }

    public boolean updateToken(String token) {

        Jedis jedis = cacheClient.getJedis();

        String tmp = jedis.get(token);

        jedis.expire(token, 60 * 60);
        jedis.expire(tmp, 60 * 60);

        return true;
    }
}
