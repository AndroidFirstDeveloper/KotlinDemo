package com.example.czl2.test

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**czl20210223
使用注解的方式定义序列化类
 相比传统方式确实减少大量手写代码
 */
@Parcelize
data class Girl(var name: String, var age: Int, var weight: Int) : Parcelable {
}