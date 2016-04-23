package io.polymorphicpanda.zerofx.view

import io.polymorphicpanda.zerofx.component.Component
import javafx.scene.Parent
import kotlin.reflect.KClass

/**
 * @author Ranie Jade Ramiso
 */
abstract class View<T: Component>(val component: T) {
    abstract val root: Parent

    protected fun<K: Component> create(klass: KClass<K>): K {
        return component.create(klass)
    }
}
