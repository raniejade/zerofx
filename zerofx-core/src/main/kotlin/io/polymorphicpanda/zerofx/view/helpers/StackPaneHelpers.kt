package io.polymorphicpanda.zerofx.view.helpers

import javafx.collections.ObservableList
import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.layout.StackPane

fun StackPane.children(block: ViewHelper.() -> Unit) {
    val _this = this
    block.invoke(object: ViewHelper() {
        override val children: ObservableList<Node>
            get() = _this.children
    })
}

fun StackPane.margin(node: Node, insets: Insets) {
    StackPane.setMargin(node, insets)
}

fun StackPane.margin(node: Node) = StackPane.getMargin(node)
