package io.polymorphicpanda.zerofx.template.helpers

import javafx.geometry.Pos
import javafx.scene.layout.StackPane

class StackPaneBuilder(pane: StackPane = StackPane()): PaneBuilder<StackPane>(pane) {
    fun alignmentProperty() = node.alignmentProperty()

    var alignment: Pos
        get() = alignmentProperty().get()
        set(value) {
            alignmentProperty().set(value)
        }
}
