package com.example.czl2.test

import android.os.Build
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
/**测试kotlin GSON反序列化泛型类型的对象时的BUG*/
class TestGson {

    private fun <T> Gson.fromJson2List(json: String): List<T> {
        val type = object : TypeToken<List<T>>() {}.type
        return Gson().fromJson(json, type)
    }

    private inline fun <reified T> Gson.fromDefineJson(json: String): T? {
        val type = object : TypeToken<T>() {}.type
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
            println("------------------${type.typeName}")
        return Gson().fromJson(json, type)
    }

    private inline fun <reified T> Gson.fromDefineJson2List(json: String): List<T>? {
        val type = object : TypeToken<List<T>>() {}.type
        return Gson().fromJson(json, type)
    }

    private inline fun <reified T> Gson.fromDefineJson2List2(json: String): List<T>? {
        val type = object : TypeLiteral<List<T>>() {}.type
        return Gson().fromJson(json, type)
    }

    fun test1() {
        val json1 = "[{\"name\":\"张三\",\"age\":\"10\"},{\"name\":\"李四\",\"age\":\"20\"}]"
        val json2 = "{\"name\":\"张三\",\"age\":\"10\"}"
        val people = Gson().fromJson<People>(json2, People::class.java)
        println("${people.name},${people.age}")
//        val peopleList = Gson().fromJson<List<People>>(json1, People::class.java)//类型不匹配，抛出异常
        val type = object : TypeToken<List<People>>() {}.type
        val peopleList = Gson().fromJson<List<People>>(json1, type)
        println(peopleList.toString())//返回的集合中保存的是People对象
        val peopleList2 = Gson().fromJson2List<People>(json1)
        println(peopleList2)//返回的集合中对象是LinkedTreeMap
        val peopleList3 = Gson().fromJson(json1, List::class.java)
        println(peopleList3)//返回的集合中对象是LinkedTreeMap
        val peopleList4 = Gson().fromDefineJson<List<People>>(json1)
        println(peopleList4.toString())//返回的结合中的额对象是People
        val people2 = Gson().fromDefineJson<People>(json2)
        println(people2.toString())
        val peopleList5 = Gson().fromDefineJson2List<People>(json1)
        println(peopleList5.toString())
        val peopleList6 = Gson().fromDefineJson2List2<People>(json1)
        println(peopleList6.toString())
    }

    class People(val name: String, val age: Int) {
        override fun toString(): String {
            return "People(name='$name', age=$age)"
        }
    }
}