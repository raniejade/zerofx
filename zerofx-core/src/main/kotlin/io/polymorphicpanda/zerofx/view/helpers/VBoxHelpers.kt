package io.polymorphicpanda.zerofx.view.helpers

import javafx.collections.ObservableList
import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox

fun VBox.children(block: ViewHelper.() -> Unit) {
    val _this = this
    block.invoke(object: ViewHelper() {
        override val children: ObservableList<Node>
            get() = _this.children
    })
}

fun VBox.margin(node: Node, insets: Insets) {
    VBox.setMargin(node, insets)
}

fun VBox.margin(node: Node) = VBox.getMargin(node)

fun VBox.vgrow(node: Node, priority: Priority) {
    VBox.setVgrow(node, priority)
}

fun VBox.vgrow(node: Node) = VBox.getVgrow(node)
