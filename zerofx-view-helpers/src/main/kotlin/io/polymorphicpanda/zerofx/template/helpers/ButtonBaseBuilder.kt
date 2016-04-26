package io.polymorphicpanda.zerofx.template.helpers

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.ButtonBase

/**
 * @author Ranie Jade Ramiso
 */
open class ButtonBaseBuilder<T: ButtonBase>(button: T): LabeledBuilder<T>(button) {
    fun actionProperty() = node.onActionProperty()
    fun action(block: (ActionEvent) -> Unit) {
        actionProperty().set(EventHandler { block.invoke(it) })
    }
    var action: EventHandler<ActionEvent>
        get() = actionProperty().get()
        set(value) {
            actionProperty().set(value)
        }
}
