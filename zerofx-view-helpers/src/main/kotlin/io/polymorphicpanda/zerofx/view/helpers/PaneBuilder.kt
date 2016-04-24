package io.polymorphicpanda.zerofx.view.helpers

import javafx.scene.Node
import javafx.scene.layout.Pane

/**
 * @author Ranie Jade Ramiso
 */
open class PaneBuilder<T: Pane>(pane: T): Builder<T>(pane) {
    open fun add(child: Node) {
        node.children.add(child)
    }
}
