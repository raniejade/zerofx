package io.polymorphicpanda.zerofx.view

import io.polymorphicpanda.zerofx.component.Component
import javafx.scene.Node
import kotlin.reflect.KClass

/**
 * @author Ranie Jade Ramiso
 */
abstract class View<T: Component>(val component: T) {
    abstract val root: Node

    open fun init() { }
    open fun destroy() { }

    protected fun <K: Component> create(klass: KClass<K>): K {
        return component.create(klass)
    }
}
