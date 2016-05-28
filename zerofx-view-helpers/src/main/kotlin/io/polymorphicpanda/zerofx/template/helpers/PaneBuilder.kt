package io.polymorphicpanda.zerofx.template.helpers

import io.polymorphicpanda.zerofx.component.Component
import io.polymorphicpanda.zerofx.template.Template
import javafx.scene.Node
import javafx.scene.layout.Pane
import kotlin.reflect.KClass

/**
 * @author Ranie Jade Ramiso
 */
open class PaneBuilder<T: Pane>(pane: T): Builder<T>(pane) {
    open fun add(child: Node) {
        node.children.add(child)
    }

    fun <T: Component<*>> Template.component(component: KClass<T>, block: ComponentBuilder<T>.() -> Unit): T {
        return app.create(component, this) {
            val builder = ComponentBuilder(this)
            block.invoke(builder)
            add(builder.node)
        }
    }
}
