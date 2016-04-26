package io.polymorphicpanda.zerofx.template.helpers

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority

/**
 * @author Ranie Jade Ramiso
 */
class HBoxBuilder(pane: HBox = HBox()): PaneBuilder<HBox>(pane) {
    fun alignmentProperty() = node.alignmentProperty()
    var alignment: Pos
        get() = alignmentProperty().get()
        set(value) {
            alignmentProperty().set(value)
        }

    fun spacingProperty() = node.spacingProperty()
    var spacing: Double
        get() = spacingProperty().get()
        set(value) {
            spacingProperty().set(value)
        }

    fun fillHeightProperty() = node.fillHeightProperty()
    var fillHeight: Boolean
        get() = fillHeightProperty().get()
        set(value) {
            fillHeightProperty().set(value)
        }

    var <T: Node> Builder<T>.hgrow: Priority
        get() = HBox.getHgrow(this@hgrow.node)
        set(value) {
            HBox.setHgrow(this@hgrow.node, value)
        }

    var <T: Node> Builder<T>.hboxMargin: Insets
        get() = HBox.getMargin(this@hboxMargin.node)
        set(value) {
            HBox.setMargin(this@hboxMargin.node, value)
        }
}
