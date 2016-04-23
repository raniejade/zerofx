package io.polymorphicpanda.zerofx.view.helpers

import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.layout.BorderPane

inline fun <T: Node> BorderPane.left(block: () -> T): T {
    return block().apply {
        this@left.left = this
    }
}

fun <T: Node> BorderPane.top(block: () -> T): T {
    return block().apply {
        this@top.top = this
    }
}


fun <T: Node> BorderPane.center(block: () -> T): T {
    return block().apply {
        this@center.center = this
    }
}


fun <T: Node> BorderPane.right(block: () -> T): T {
    return block().apply {
        this@right.right = this
    }
}

fun <T: Node> BorderPane.bottom(block: () -> T): T {
    return block().apply {
        this@bottom.bottom = this
    }
}


fun BorderPane.margin(node: Node, insets: Insets) {
    BorderPane.setMargin(node, insets)
}

fun BorderPane.margin(node: Node) = BorderPane.getMargin(node)
