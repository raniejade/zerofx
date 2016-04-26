package io.polymorphicpanda.zerofx.template.helpers

import javafx.scene.Node
import javafx.scene.layout.Pane
import kotlin.reflect.KProperty

/**
 * @author Ranie Jade Ramiso
 */
class BuilderDelegate(val block: PaneBuilder<*>.() -> Unit) {
    var node: Node? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Node {
        if (node == null) {
            block.invoke(object: PaneBuilder<Pane>(Pane()) {
                override fun add(child: Node) {
                    this@BuilderDelegate.node = child
                }
            })
        }
        return node!!
    }
}
