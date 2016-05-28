package io.polymorphicpanda.zerofx.template.helpers

import io.polymorphicpanda.zerofx.component.Component
import javafx.scene.Node

/**
 * @author Ranie Jade Ramiso
 */
class ComponentBuilder<T: Component<*>>(val component: T): Builder<Node>(component.template.root) {
}
