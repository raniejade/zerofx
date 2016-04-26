package io.polymorphicpanda.zerofx.template.helpers

import javafx.scene.control.Labeled

/**
 * @author Ranie Jade Ramiso
 */
open class LabeledBuilder<T: Labeled>(labeled: T): Builder<T>(labeled) {
    fun textProperty() = node.textProperty()
    var text: String
        get() = textProperty().get()
        set(value) {
            textProperty().set(value)
        }
}
