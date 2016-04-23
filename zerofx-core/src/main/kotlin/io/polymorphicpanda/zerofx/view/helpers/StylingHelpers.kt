package io.polymorphicpanda.zerofx.view.helpers

import javafx.scene.Node

fun Node.styleClass(vararg styleClass: String) {
    this.styleClass.setAll(*styleClass)
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
