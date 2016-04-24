package io.polymorphicpanda.zerofx.view.helpers

import io.polymorphicpanda.zerofx.component.Component
import javafx.beans.property.BooleanProperty
import javafx.scene.Node

fun builder(block: PaneBuilder<*>.() -> Unit) = BuilderDelegate(block)

internal fun <K: Node, T: Builder<K>> builder(parent: Builder<*>?, builder: T, block: T.() -> Unit): K {
    return builder.run {
        block.invoke(this)

        if (parent != null && parent is PaneBuilder) {
            parent.add(this.node)
        }

        node
    }
}

fun Builder<*>.stackPane(block: StackPaneBuilder.() -> Unit) = builder(this, StackPaneBuilder(), block)

fun Builder<*>.borderPane(block: BorderPaneBuilder.() -> Unit) = builder(this, BorderPaneBuilder(), block)

fun Builder<*>.vbox(block: VBoxBuilder.() -> Unit) = builder(this, VBoxBuilder(), block)

fun Builder<*>.hbox(block: HBoxBuilder.() -> Unit) = builder(this, HBoxBuilder(), block)

fun Builder<*>.label(block: LabelBuilder.() -> Unit) = builder(this, LabelBuilder(), block)

fun Builder<*>.button(block: ButtonBuilder.() -> Unit) = builder(this, ButtonBuilder(), block)

fun <T> Builder<*>.listView(block: ListViewBuilder<T>.() -> Unit) =  builder(this, ListViewBuilder(), block)

fun Builder<*>.textField(block: TextFieldBuilder.() -> Unit) = builder(this, TextFieldBuilder(), block)
fun Builder<*>.textArea(block: TextAreaBuilder.() -> Unit) = builder(this, TextAreaBuilder(), block)

fun <T: Component> Builder<*>.component(component: T,
                                        block: ComponentBuilder<T>.() -> Unit)
        = builder(this, ComponentBuilder(component), block)

fun switch(condition: BooleanProperty) {

}
