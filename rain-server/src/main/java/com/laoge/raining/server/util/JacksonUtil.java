package com.laoge.raining.server.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by yuhou on 2017/9/13.
 */
public class JacksonUtil {

    private static ObjectMapper mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 过滤对象的null属性
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }


    public static ObjectMapper getMapper() {
        return mapper;
    }


    public static String bean2Json(Object obj) throws IOException {
        return mapper.writeValueAsString(obj);
    }


    public static <K, V> Map<K, V> json2Map(String jsonStr, Class<?> kClass, Class<?> vClass) throws IOException {
        MapType type = mapper.getTypeFactory().constructMapType(Map.class, kClass, vClass);
        return mapper.readValue(jsonStr, type);
    }

    public static <K, V> Map<K, V> json2Map(String jsonStr, JavaType kType, JavaType vType) throws IOException {
        MapType type = mapper.getTypeFactory().constructMapType(Map.class, kType, vType);
        return mapper.readValue(jsonStr, type);
    }

    public static Map json2Map(String jsonStr) throws IOException {
        MapType type = mapper.getTypeFactory().constructRawMapType(Map.class);
        return mapper.readValue(jsonStr, type);
    }


    public static <T> List<T> json2List(String jsonStr, Class<?> clazz) throws IOException {
        CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
        return mapper.readValue(jsonStr, type);
    }

    public static <T> List<T> json2List(String jsonStr, JavaType classType) throws IOException {
        CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, classType);
        return mapper.readValue(jsonStr, type);
    }

    public static List json2List(String jsonStr) throws IOException {
        CollectionType type = mapper.getTypeFactory().constructRawCollectionType(List.class);
        return mapper.readValue(jsonStr, type);
    }

    public static JavaType getParametricType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }


    public static JavaType getParametricType(Class<?> collectionClass, JavaType... elementTypes) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementTypes);
    }


    public static JavaType getConstructType(Type baseType) {
        return mapper.getTypeFactory().constructType(baseType);
    }


    public static <T> T json2Bean(String jsonStr, JavaType javaType) throws IOException {
        return mapper.readValue(jsonStr, javaType);
    }


    public static <T> T json2Bean(String jsonStr, TypeReference<?> typeOfT) throws IOException {
        return mapper.readValue(jsonStr, typeOfT);
    }


    public static <T> T json2Bean(String jsonStr, Class<T> objClass) throws IOException {
        return mapper.readValue(jsonStr, objClass);
    }


    public static <T> T json2Bean(String jsonStr, Class<?> collectionClass, Class<?>... elementClasses)
            throws IOException {
        return json2Bean(jsonStr, getParametricType(collectionClass, elementClasses));
    }


    public static <T> T json2Bean(String jsonStr, Class<?> collectionClass, JavaType... elementTypes)
            throws IOException {
        return json2Bean(jsonStr, getParametricType(collectionClass, elementTypes));
    }
}

