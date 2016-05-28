package io.polymorphicpanda.zerofx.template

import io.polymorphicpanda.zerofx.ZeroApp
import io.polymorphicpanda.zerofx.component.Component
import javafx.scene.Node
import java.util.*

/**
 * @author Ranie Jade Ramiso
 */
abstract class Template(val app: ZeroApp) {
    abstract val root: Node
    internal  val components = LinkedList<Component<*>>()

    open fun init() {
    }

    open fun destroy() {
        components.forEach { it.destroy() }
    }
}
