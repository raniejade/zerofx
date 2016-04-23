package io.polymorphicpanda.zerofx.view.helpers

import javafx.collections.ObservableList
import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority

fun HBox.children(block: ViewHelper.() -> Unit) {
    val _this = this
    block.invoke(object: ViewHelper() {
        override val children: ObservableList<Node>
            get() = _this.children
    })
}

fun HBox.margin(node: Node, insets: Insets) {
    HBox.setMargin(node, insets)
}

fun HBox.margin(node: Node) = HBox.getMargin(node)

fun HBox.hgrow(node: Node, priority: Priority) {
    HBox.setHgrow(node, priority)
}

fun HBox.hgrow(node: Node) = HBox.getHgrow(node)
