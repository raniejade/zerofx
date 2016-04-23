package io.polymorphicpanda.zerofx.view.helpers

import io.polymorphicpanda.zerofx.component.Component
import javafx.collections.ObservableList
import javafx.scene.Node

abstract class ViewHelper {
    abstract val children: ObservableList<Node>

    operator fun <T: Component> T.unaryPlus(): T {
        children.add(this.view.root)
        return this
    }

    operator fun <T: Node> T.unaryPlus(): T {
        children.add(this)
        return this
    }
}





