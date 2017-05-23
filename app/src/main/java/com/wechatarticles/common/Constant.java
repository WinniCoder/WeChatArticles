package com.wechatarticles.common;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by wuhui on 2017/5/12.
 */

public class Constant {
    public static final String APP_KEY="your app key";

    public static final String baseUrl=" http://api.jisuapi.com/weixinarticle/";

    public static final int DEFAULT_TIMEOUT = 10;

    public static ExclusionStrategy exclusionStrategy = new ExclusionStrategy() {

        @Override
        public boolean shouldSkipField(FieldAttributes fieldAttributes) {
            return false;
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return clazz == Field.class || clazz == Method.class;
        }
    };

    public static Gson gson = new GsonBuilder()
            .addSerializationExclusionStrategy(exclusionStrategy)
            .addDeserializationExclusionStrategy(exclusionStrategy)
            .create();
}
