package com.example.demo.redis.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private Class<T> clazz;

    public FastJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T o) throws SerializationException {
        if(o == null ) {
            return new byte[0];
        }
        return JSON.toJSONString(o, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if(bytes == null || bytes.length == 0){
            return null;
        }
        String str = new String(bytes , DEFAULT_CHARSET);
        return (T)JSON.parseObject(str,clazz);
    }
}
