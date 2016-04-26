package io.polymorphicpanda.zerofx.property

import javafx.beans.property.Property
import kotlin.reflect.KProperty

fun <T, K: Property<T>> property(supplier: () -> K, defaultValue: T? = null) = PropertyDelegate(supplier, defaultValue)

class PropertyDelegate<T, K: Property<T>>(val supplier: () -> K, val defaultValue: T?) {
    private var instance: K? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (instance == null && defaultValue != null) {
            return defaultValue
        }
        return this().value
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this().value = value
    }

    operator fun invoke(): K {
        if (instance == null) {
            instance = supplier.invoke()
        }
        return instance!!
    }
}
