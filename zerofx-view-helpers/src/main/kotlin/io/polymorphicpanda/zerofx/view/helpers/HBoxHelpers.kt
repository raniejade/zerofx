package io.polymorphicpanda.zerofx.view.helpers

import javafx.collections.ObservableList
import javafx.scene.Node
import javafx.scene.layout.HBox

fun HBox.children(block: ViewHelper.() -> Unit) {
    val _this = this
    block.invoke(object: ViewHelper() {
        override val children: ObservableList<Node>
            get() = _this.children
    })
}