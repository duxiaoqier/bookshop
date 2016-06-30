package com.train.bookshop.service;

import java.util.Map;

public interface CacheService {

    /**
     * 获取
     * 
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 获取
     * 
     * @param key
     * @param hashKey
     * @return
     */
    Map<String, String> getMap(String key);

    /**
     * 获取
     * 
     * @param key
     * @param hashKey
     * @return
     */
    String getMapValue(String key, String hashKey);

    /**
     * 设置
     * 
     * @param key
     * @param value
     * @param expires 单位：秒
     */
    void put(String key, String value, long expires);

    /**
     * 设置，默认7天有效期
     * 
     * @param key
     * @param value
     */
    void put(String key, String value);

    /**
     * 设置Map
     * 
     * @param key
     * @param map
     * @param expires 单位：秒
     */
    void putMap(String key, Map<String, String> map, long expires);

    /**
     * 设置Map，默认7天有效期
     * 
     * @param key
     * @param map
     */
    void putMap(String key, Map<String, String> map);

    /**
     * 设置Map
     * 
     * @param key
     * @param hashKey
     * @param value
     * @param expires 单位：秒
     */
    void putMap(String key, String hashKey, String value, long expires);

    /**
     * 设置Map，默认7天有效期
     * 
     * @param key
     * @param hashKey
     * @param value
     */
    void putMap(String key, String hashKey, String value);

    /**
     * 删除
     * 
     * @param key
     */
    void remove(String key);

    /**
     * 删除子key
     * 
     * @param key
     * @param hashKeys
     */
    void remove(String key, Object... hashKeys);

    /**
     * 根据key延长失效时间
     * 
     * @param key
     * @param timeout
     */
    void expire(String key, final long timeout);

    /**
     * 根据key延长失效时间
     * 
     * @param key
     * @param timeout
     */
    void expireMap(String key, final long timeout);
}
