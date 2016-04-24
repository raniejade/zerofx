package io.polymorphicpanda.zerofx.view.helpers

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.ButtonBase

/**
 * @author Ranie Jade Ramiso
 */
open class ButtonBaseBuilder<T: ButtonBase>(button: T): LabeledBuilder<T>(button) {
    fun onActionProperty() = node.onActionProperty()
    fun onAction(block: (ActionEvent) -> Unit) {
        onActionProperty().set(EventHandler { block.invoke(it) })
    }
    var onAction: EventHandler<ActionEvent>
        get() = onActionProperty().get()
        set(value) {
            onActionProperty().set(value)
        }
}
