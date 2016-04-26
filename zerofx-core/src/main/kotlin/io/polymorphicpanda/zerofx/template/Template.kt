package io.polymorphicpanda.zerofx.template

import io.polymorphicpanda.zerofx.component.Component
import io.polymorphicpanda.zerofx.component.ViewBindings
import javafx.scene.Node
import kotlin.reflect.KClass

/**
 * @author Ranie Jade Ramiso
 */
abstract class Template<T: Component, K: ViewBindings<T>>(val component: T, val bindings: K) {
    abstract val root: Node

    open fun init() { }
    open fun destroy() { }

    fun <K: Component> create(klass: KClass<K>): K {
        return component.create(klass)
    }
}
