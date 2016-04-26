package io.polymorphicpanda.zerofx.template.helpers

import javafx.scene.control.TextInputControl

/**
 * @author Ranie Jade Ramiso
 */
open class TextInputControlBuilder<T: TextInputControl>(textInput: T): Builder<T>(textInput) {
    fun promptTextProperty() = node.promptTextProperty()
    var promptText: String
        get() = promptTextProperty().get()
        set(value) {
            promptTextProperty().set(value)
        }

    fun textProperty() = node.textProperty()
    var text: String
        get() = textProperty().get()
        set(value) {
            textProperty().set(value)
        }
}
