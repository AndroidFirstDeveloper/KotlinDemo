package com.example.czl.test;

import android.os.Build;
import android.util.Log;

import com.example.czl.TypeUtils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;

/**
 * czl20210305
 * 测试泛型的参数类型获取方式
 */
public class TestParameterType {
    private final String TAG = "TestParameterType";

    public void test() {
        try {
            Method method = MyData.class.getDeclaredMethod("resolveData", Boolean.class, Result.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                int parameterCount = method.getParameterCount();
                Log.e(TAG, "parameterCount: " + parameterCount);
            }
            Type[] parameterTypes = method.getGenericParameterTypes();//Boolean、Result<String,? super ConfigData,? extends User>
            //函数参数
            ParameterizedType parameterizedType = (ParameterizedType) parameterTypes[parameterTypes.length - 1];//Result<String,? super ConfigData,? extends User>
            Type parameterLowerBound1 = TypeUtils.getParameterLowerBound(1, parameterizedType);//下界操作符 ConfigData
//            Type parameterUpperBound1 = TypeUtils.getParameterUpperBound(1, parameterizedType);
//            Type parameterLowerBound2 = TypeUtils.getParameterLowerBound(2, parameterizedType);
            Type parameterUpperBound2 = TypeUtils.getParameterUpperBound(2, parameterizedType);//上界操作符 User
            Type[] types = parameterizedType.getActualTypeArguments();//String、? super ConfigData、? extends User
            Type rawType = parameterizedType.getRawType();//Result
            Type ownerType = parameterizedType.getOwnerType();//TestParameterType
            //返回参数
            ParameterizedType genericReturnType = (ParameterizedType) method.getGenericReturnType();//List<String>
            Type parameterLowerBound3 = TypeUtils.getParameterLowerBound(0, genericReturnType);//String
            Type parameterUpperBound3 = TypeUtils.getParameterUpperBound(0, genericReturnType);//String
            Type[] types2 = genericReturnType.getActualTypeArguments();//String
            Type rawType2 = genericReturnType.getRawType();//List
            Type ownerType2 = genericReturnType.getOwnerType();//null
            Log.e(TAG, "ownerType2: " + ownerType2);
        } catch (Exception e) {
            Log.e(TAG, "test: ", e);
        }
    }

    public static class MyData {
        public List<String> resolveData(Boolean isUser, Result<String, ? super ConfigData, ? extends Animal> result) {
            return null;
        }
    }

    public static class Result<T, E, M> {

    }

    public static class Animal {
        private String name;
        private int weight;
        private String country;
    }
}
