package io.polymorphicpanda.zerofx.template.helpers

import javafx.scene.Node

/**
 * @author Ranie Jade Ramiso
 */
open class Builder<T: Node>(val node: T) {
    fun styleClass() = node.styleClass
    fun styleClass(vararg classes: String) {
        styleClass().addAll(classes)
    }

    fun focusedProperty() = node.focusedProperty()
    val focused = focusedProperty().get()

    fun hoveredProperty() = node.hoverProperty()
    val hovered = hoveredProperty().get()

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

    fun Node.styleClass(vararg styleClass: String) {
        this.styleClass.addAll(*styleClass)
    }

    fun Node.addStyleClass(styleClass: String) {
        this.styleClass.apply {
            if (!contains(styleClass)) {
                add(styleClass)
            }
        }
    }

    fun Node.hasStyleClass(vararg styleClass: String): Boolean {
        return this.styleClass.containsAll(listOf(*styleClass))
    }

    fun Node.removeStyleClass(styleClass: String) {
        this.styleClass.removeAll {
            it == styleClass
        }
    }

    fun Node.toggleStyleClass(styleClass: String) {
        if (hasStyleClass(styleClass)) {
            removeStyleClass(styleClass)
        } else {
            addStyleClass(styleClass)
        }
    }
}
