package io.polymorphicpanda.zerofx.component

import io.polymorphicpanda.zerofx.ZeroApp
import io.polymorphicpanda.zerofx.template.Template
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.primaryConstructor

/**
 * @author Ranie Jade Ramiso
 */
abstract class Component(val app: ZeroApp) {
    val template: Template<*, *> by lazy {
        createTemplate()
    }

    internal val components = LinkedList<Component>()

    open fun init() {
        template.init()
    }

    open fun contentInit() {
        components.forEach {
            it.init()
        }
    }


    open fun destroy() {
        template.destroy()
        components.forEach {
            it.destroy()
        }
    }

    protected abstract fun createTemplate(): Template<*, *>

    internal fun <T: Component> create(kClass: KClass<T>): T {
        return kClass.primaryConstructor!!.call(app).apply {
            this@Component.components.add(this)
        }
    }
}
