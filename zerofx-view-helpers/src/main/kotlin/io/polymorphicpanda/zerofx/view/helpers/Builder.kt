package io.polymorphicpanda.zerofx.view.helpers

import javafx.scene.Node

/**
 * @author Ranie Jade Ramiso
 */
open class Builder<T: Node>(val node: T) {
    fun styleClass() = node.styleClass
    fun styleClass(vararg classes: String) {
        styleClass().addAll(classes)
    }


    fun focusTraversableProperty() = node.focusTraversableProperty()
    var focusTraversable: Boolean
        get() = focusTraversableProperty().get()
        set(value) {
            focusTraversableProperty().set(value)
        }

    fun disableProperty() = node.disableProperty()
    var disable: Boolean
        get() = disableProperty().get()
        set(value) {
            disableProperty().set(value)
        }

    fun visibleProperty() = node.visibleProperty()
    var visible: Boolean
        get() = visibleProperty().get()
        set(value) {
            visibleProperty().set(value)
        }

    fun managedProperty() = node.managedProperty()
    var managed: Boolean
        get() = managedProperty().get()
        set(value) {
            managedProperty().set(value)
        }

    fun idProperty() = node.idProperty()
    var id: String
        get() = idProperty().get()
        set(value) {
            idProperty().set(value)
        }
}
