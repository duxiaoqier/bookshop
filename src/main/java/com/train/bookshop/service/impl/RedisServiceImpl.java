package com.train.bookshop.service.impl;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;

import com.train.bookshop.service.CacheService;

/**
 * 缓存服务
 * 
 * @Created by liuj-s on 2016-05-14.
 */
public class RedisServiceImpl implements CacheService {

    private static final String CACHE_PREFIX_BIMFACE  = "bimface:";
    private static final long   CACHE_DEFAULT_EXPIRES = 7 * 24 * 60 * 60;// 默认七天失效

    private RedisTemplate<String, String>              redisStringTemplate;
    private RedisTemplate<String, Map<String, String>> redisMapTemplate;

    @Override
    public String get(String key) {
        return redisStringTemplate.opsForValue().get(contactKey(key));
    }

    @Override
    public Map<String, String> getMap(String key) {
        return redisMapTemplate.opsForValue().get(contactKey(key));
    }

    @Override
    public String getMapValue(String key, String hashKey) {
        return String.valueOf(redisMapTemplate.opsForHash().get(contactKey(key), hashKey));
    }

    @Override
    public void put(String key, String value, long expires) {
        redisStringTemplate.opsForValue().set(contactKey(key), value, expires, TimeUnit.SECONDS);
    }

    @Override
    public void put(String key, String value) {
        put(key, value, CACHE_DEFAULT_EXPIRES);
    }

    @Override
    public void putMap(String key, String hashKey, String value, long expires) {
        redisMapTemplate.opsForHash().put(contactKey(key), hashKey, value);
        redisMapTemplate.expire(contactKey(key), expires, TimeUnit.SECONDS);
    }

    @Override
    public void putMap(String key, Map<String, String> map) {
        putMap(key, map, CACHE_DEFAULT_EXPIRES);
    }

    @Override
    public void putMap(String key, Map<String, String> map, long expires) {
        redisMapTemplate.opsForHash().putAll(contactKey(key), map);
        redisMapTemplate.expire(contactKey(key), expires, TimeUnit.SECONDS);
    }

    @Override
    public void putMap(String key, String hashKey, String value) {
        putMap(key, hashKey, value, CACHE_DEFAULT_EXPIRES);
    }

    @Override
    public void remove(String key) {
        redisStringTemplate.delete(contactKey(key));
    }

    @Override
    public void remove(String key, Object... hashKeys) {
        redisMapTemplate.opsForHash().delete(contactKey(key), hashKeys);
    }

    @Override
    public void expire(String key, long timeout) {
        redisStringTemplate.expire(key, timeout, TimeUnit.SECONDS);

    }

    @Override
    public void expireMap(String key, long timeout) {
        redisMapTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    private String contactKey(String key) {
        return CACHE_PREFIX_BIMFACE + key;
    }

    public void setRedisStringTemplate(RedisTemplate<String, String> redisStringTemplate) {
        this.redisStringTemplate = redisStringTemplate;
    }

    public void setRedisMapTemplate(RedisTemplate<String, Map<String, String>> redisMapTemplate) {
        this.redisMapTemplate = redisMapTemplate;
    }
}
