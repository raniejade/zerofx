package io.polymorphicpanda.zerofx.template

import io.polymorphicpanda.zerofx.component.Component
import javafx.scene.Node
import kotlin.reflect.KClass

/**
 * @author Ranie Jade Ramiso
 */
abstract class Template<T: Component, K: Template.Binder<T>>(val component: T, val bindings: K) {
    abstract val root: Node

    interface Binder<T: Component> {
    }


    open fun init() { }
    open fun destroy() { }

    fun <K: Component> create(klass: KClass<K>): K {
        return component.create(klass)
    }
}
