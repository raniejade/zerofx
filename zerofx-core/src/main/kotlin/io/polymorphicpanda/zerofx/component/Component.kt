package io.polymorphicpanda.zerofx.component

import io.polymorphicpanda.zerofx.ZeroApp
import io.polymorphicpanda.zerofx.view.View
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.primaryConstructor

/**
 * @author Ranie Jade Ramiso
 */
abstract class Component(val app: ZeroApp) {
    abstract val view: View<*>

    internal val components = LinkedList<Component>()

    open fun init() {
        components.forEach {
            it.init()
        }
    }


    open fun destroy() {
        components.forEach {
            it.destroy()
        }
    }

    internal fun <T: Component> create(kClass: KClass<T>): T {
        return kClass.primaryConstructor!!.call(app).apply {
            components.add(this)
        }
    }
}
