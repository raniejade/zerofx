package io.polymorphicpanda.zerofx.template.helpers

import javafx.scene.control.TextArea

/**
 * @author Ranie Jade Ramiso
 */
class TextAreaBuilder(textArea: TextArea = TextArea()): TextInputControlBuilder<TextArea>(textArea) {
    fun prefRowCountProperty() = node.prefRowCountProperty()
    var prefRowCount: Int
        get() = prefRowCountProperty().get()
        set(value) {
            prefRowCountProperty().set(value)
        }
}
