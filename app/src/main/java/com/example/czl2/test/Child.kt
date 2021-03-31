package com.example.czl2.test

import android.os.Parcel
import android.os.Parcelable

/**czl20210223
数据类实现序列化*/
data class Child(var age: Int, var name: String?, var tel: Int, var grade: Int) : Parcelable {

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeInt(age)
        p0.writeString(name)
        p0.writeInt(tel)
        p0.writeInt(grade)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Child> {
            override fun createFromParcel(p0: Parcel): Child {
                return Child(p0)
            }

            override fun newArray(p0: Int): Array<Child?> {
                return arrayOfNulls<Child>(p0)
            }
        }
    }

    constructor(p0: Parcel) : this(
        p0.readInt(),
        p0.readString(),
        p0.readInt(),
        p0.readInt()
    )
}