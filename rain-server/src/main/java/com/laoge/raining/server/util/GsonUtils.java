package com.laoge.raining.server.util;

import com.google.gson.*;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by yuhou on 2017/9/12.
 */

public final class GsonUtils {

    private static final Gson GSON = new GsonBuilder().create();

    public static Gson getInstance() {
        return GSON;
    }

    /**
     * 返回一个yyyy-MM-dd HH:mm:ss格式的Gson实例
     *
     * @return Gson实例
     */
    public static Gson getInstanceWithDateFormat() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    /**
     * 返回一个给定日期格式的Gson实例
     *
     * @param DATE_PATTERN 日期格式
     * @return Gson实例
     * @see
     */
    public static Gson getInstanceWithDateFormat(String DATE_PATTERN) {
        return new GsonBuilder().setDateFormat(DATE_PATTERN).create();
    }

    /**
     * 返回一个long类型日期格式(毫秒)的Gson实例
     *
     * @return Gson实例
     */
    public static Gson getInstanceWithLongDateFormat() {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(java.util.Date.class, (JsonSerializer<Date>) (src, typeOfSrc, context) -> new JsonPrimitive(src.getTime())).setDateFormat(DateFormat.LONG);
        gb.registerTypeAdapter(java.util.Date.class, (JsonDeserializer<Date>) (json, typeOfT, context) -> new Date(json.getAsJsonPrimitive().getAsLong())).setDateFormat(DateFormat.LONG);
        return gb.create();
    }

    /**
     * 对象转化为json串
     *
     * @param obj 要转换的对象
     * @return 转换后的json
     */
    public static String object2Json(Object obj) {
        return object2Json(obj, false);
    }

    /**
     * 对象转化为json串
     *
     * @param obj               要转换的对象
     * @param supportAnnotation 是否识别Gson注解
     * @return 转换后的json
     */
    public static String object2Json(Object obj, Boolean supportAnnotation) {
        if (Boolean.TRUE.equals(supportAnnotation)) {
            Gson gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation().create();
            return gson.toJson(obj);
        } else {
            return GSON.toJson(obj);
        }
    }

}