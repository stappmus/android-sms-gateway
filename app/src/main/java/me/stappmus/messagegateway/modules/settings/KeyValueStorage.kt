package me.stappmus.messagegateway.modules.settings

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

interface KeyValueStorage {
    fun <T>set(key: String, value: T)
    fun <T>get(key: String, typeOfT: Type): T?
    fun remove(key: String)
}

inline fun <reified T> KeyValueStorage.get(key: String): T? {
    val typeOfT = try {
        object : TypeToken<T>() {}.type
    } catch (_: IllegalStateException) {
        TypeToken.get(T::class.java).type
    }

    return get(key, typeOfT)
}
