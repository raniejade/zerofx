package io.polymorphicpanda.zerofx.template.helpers

import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane

/**
 * @author Ranie Jade Ramiso
 */
class BorderPaneBuilder(pane: BorderPane = BorderPane()): PaneBuilder<BorderPane>(pane) {
    class BorderPaneContentBuilder(val block: (Node) -> Unit): PaneBuilder<Pane>(Pane()) {
        override fun add(child: Node) {
            block.invoke(child)
        }
    }

    fun leftProperty() = node.leftProperty()
    fun left(block: BorderPaneContentBuilder.() -> Unit) {
        block.invoke(BorderPaneContentBuilder { left = it })
    }
    var left: Node
        get() = leftProperty().get()
        set(value) {
            leftProperty().set(value)
        }

    fun rightProperty() = node.rightProperty()
    fun right(block: BorderPaneContentBuilder.() -> Unit) {
        block.invoke(BorderPaneContentBuilder { right = it })
    }
    var right: Node
        get() = rightProperty().get()
        set(value) {
            rightProperty().set(value)
        }

    fun topProperty() = node.topProperty()
    fun top(block: BorderPaneContentBuilder.() -> Unit) {
        block.invoke(BorderPaneContentBuilder { top = it })
    }
    var top: Node
        get() = topProperty().get()
        set(value) {
            topProperty().set(value)
        }

    fun bottomProperty() = node.bottomProperty()
    fun bottom(block: BorderPaneContentBuilder.() -> Unit) {
        block.invoke(BorderPaneContentBuilder { bottom = it })
    }
    var bottom: Node
        get() = bottomProperty().get()
        set(value) {
            bottomProperty().set(value)
        }

    fun centerProperty() = node.centerProperty()
    fun center(block: BorderPaneContentBuilder.() -> Unit) {
        block.invoke(BorderPaneContentBuilder { center = it })
    }
    var center: Node
        get() = centerProperty().get()
        set(value) {
            centerProperty().set(value)
        }

    var <T: Node> Builder<T>.borderPaneMargin: Insets
        get() = BorderPane.getMargin(this@borderPaneMargin.node)
        set(value) {
            BorderPane.setMargin(this@borderPaneMargin.node, value)
        }
}
