package com.hy.onlinemarket;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;



public class JsonUtils {

    // Java、Android都可使用
    //使用前，需要添加Gson.jar 或 compile 'com.google.code.gson:gson:2.8.2'

    /**
     * JavaBean或List转化为Json字符串
     *
     * @param obj JavaBean或List对象
     * @return json字符串。返回值 "{...}":obj为bean时；"[...]":obj为List时；"":obj为空时。
     */
    public static String toJson(Object obj) {
        return new Gson().toJson(obj);
    }

    /**
     * Json转为JavaBean对象
     *
     * @param json json字符串（需符合json格式）
     * @param cls  需转化的JavaBean的类对象
     * @param <E>  JavaBean的类型
     * @return 转出的JavaBean对象。返回值null:当json为空字符串或null时。
     */
    @Nullable
    public static <E> E toBean(String json, @NonNull Class<E> cls) {
        return new Gson().fromJson(json, cls);
    }

    /**
     * Json转为集合List对象
     *
     * @param json json字符串（需符合数组型json的格式，以[开头，以]结尾）
     * @param cls  集合中元素的数组的类对象，如需List<String> 则填写String[]
     * @param <E>  集合中元素的类型
     * @return 转出的集合对象。返回值null:当json为空字符串或null时。
     */
    @Nullable
    public static <E> List<E> toArr(String json, @NonNull Class<E[]> cls) {
        E[] es = new Gson().fromJson(json, cls);
        return es == null ? null : Arrays.asList(es);

    }

}
