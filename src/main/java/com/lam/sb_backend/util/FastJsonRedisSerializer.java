//package com.lam.sb_backend.util;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.parser.ParserConfig;
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.fasterxml.jackson.databind.JavaType;
//import com.fasterxml.jackson.databind.type.TypeFactory;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.SerializationException;
//
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//
//public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {
//
//    public static final Charset CHARSET = StandardCharsets.UTF_8;
//
//    private Class<T> clazz;
//
//    static {
//        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
//    }
//
//    public FastJsonRedisSerializer(Class<T> clazz) {
//        super();
//        this.clazz = clazz;
//    }
//
//    protected JavaType getJavaType(Class<?> clazz){
//        return TypeFactory.defaultInstance().constructType(clazz);
//    }
//
//    @Override
//    public byte[] serialize(T value) throws SerializationException {
//        if(value == null){
//            return new byte[0];
//        }
//        return JSON.toJSONString(value, SerializerFeature.WriteClassName).getBytes(CHARSET);
//    }
//
//    @Override
//    public T deserialize(byte[] bytes) throws SerializationException {
//        if(bytes == null || bytes.length == 0){
//            return null;
//        }
//        String str = new String(bytes, CHARSET);
//        return JSON.parseObject(str, clazz);
//    }
//}
