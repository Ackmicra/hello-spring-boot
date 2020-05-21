package com.dhcc.zpc.util.cache;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 对redis缓存数据进行统一的存储、查询，并对数据异常进行处理
 * @author yuanshaobo
 * @date: 2019/12/17 9:56
 * @since v1.1
 */
@Component
public class RedisDataManager {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private final static String prefix = "MFS:";

    /**
     * @return void
     * @Author 赵朋超
     * @Description //TODO 
     * @Date 21:43 2020/5/19
     * @Param [key, value, timeout, unit]
     **/
    public void put(String key, Object value, long timeout, TimeUnit unit){
        redisTemplate.opsForValue().set(prefix + key, value, timeout, unit);
    }

    /**
     * @return void
     * @Author 赵朋超
     * @Description //向redis存放值，默认过期时间为2小时
     * @Date 21:43 2020/5/19
     * @Param [key, value]
     **/
    public void put(String key, Object value){
        redisTemplate.opsForValue().set(prefix + key, value, 2, TimeUnit.HOURS);
    }

    /**
     * @return java.lang.Object
     * @Author 赵朋超
     * @Description //从redis获取值
     * @Date 21:44 2020/5/19
     * @Param [key]
     **/
    public Object get(String key){
        return redisTemplate.opsForValue().get(prefix + key);
    }

    public void deleteCache(String key){
        redisTemplate.delete(prefix + key);
    }

    /**
     * @return void
     * @Author 赵朋超
     * @Description //向redis存放sameKey值一样，但是uniqueKey和value不一样的值
     * @Date 21:49 2020/5/19
     * @Param [uniqueKey, sameKey, value, timeout, unit]
     **/
    public void putHash(String uniqueKey, String sameKey, Object value, long timeout, TimeUnit unit){
        redisTemplate.opsForHash().put(prefix + sameKey, uniqueKey, value);
        redisTemplate.expire(prefix + sameKey, timeout, unit);
    }

    /**
     * @return java.util.Map<java.lang.Object,java.lang.Object>
     * @Author 赵朋超
     * @Description //从redis中获取key值一样的值，返回一个map
     * @Date 21:52 2020/5/19
     * @Param [sameKey]
     **/
    public Map<Object,Object> getHsh(String sameKey){
        Map<Object,Object> map = redisTemplate.opsForHash().entries(prefix + sameKey);
        return map;
    }

    private final static String duplicateKey = "MFS:Duplicate:";
    /**
     * 必须与stopDuplicate配套使用
     * @param key
     * @return
     */
    public boolean startDuplicate(String key){
        boolean ifDuplicate = false;
        String redisKey = duplicateKey + key;
        long count = redisTemplate.opsForValue().increment(redisKey, 1);
        if (count == 1) {
            redisTemplate.expire(redisKey, 1, TimeUnit.HOURS);
        }
        if (count > 1) {
            ifDuplicate = true;
        }
        return ifDuplicate;
    }

    public void stopDuplicate(String key){
        String redisKey = duplicateKey + key;
        redisTemplate.delete(redisKey);
    }
}