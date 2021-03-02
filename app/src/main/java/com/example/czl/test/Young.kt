package com.example.czl.test

import android.os.Parcel
import android.os.Parcelable
import android.util.Log

/**czl20210223
普通类实现序列化*/
class Young : Parcelable {
    var age: Int = 0
    var name: String? = ""
    var phone: String? = ""


    /**次构造器
     * 通过实践验证，实现序列化的时候，不一定在主构造器中定义类成员，次构造器中定义也能正常使类进行序列化
     * */
    constructor(age: Int, name: String?, phone: String?) {
        this.age = age
        this.name = name
        this.phone = phone
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeInt(age)
        p0.writeString(name)
        p0.writeString(phone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        //通过 Kotlin 提供的 @JvmField 注解，我们可以让 Kotlin 编译器把它作为一个字段进行处理，那我们可以在 companion object 里定义一个 CREATOR ，然后给它加上 @JvmField 注解
        @JvmField
        val CREATOR = object : Parcelable.Creator<Young> {
            //因为必须要提供CREATOR静态属性，而kotlin中不支持static关键字，所以必须通过伴生对象来实现。
            override fun newArray(p0: Int): Array<Young?> {//这里手动修改返回数组中元素可为空，因为arrayOfNulls方法返回的数据中元素可为空。如果不这样做编译就无法通过
                return arrayOfNulls(p0)//知识点数组（创建、初始化
            }

            override fun createFromParcel(p0: Parcel): Young {
                return Young(p0)
            }
        }
    }

    constructor(p0: Parcel) : this(
        p0.readInt(),
        p0.readString(),
        p0.readString()
    )

    override fun toString(): String {//普通类默认的方法是输出地址，不是每一个属性拼接后的值。这里是手动重写后的方法
        Log.e("Young", "----------------------------------toString: ")
        return "Young(age=$age, name=$name, phone=$phone)"
    }
}