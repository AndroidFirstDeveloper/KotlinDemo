package com.example.czl2.test

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

open class TypeLiteral<T> {
    val type: Type
        get() = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
}