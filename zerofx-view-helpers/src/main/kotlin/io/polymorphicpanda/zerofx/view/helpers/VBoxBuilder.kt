package io.polymorphicpanda.zerofx.view.helpers

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox

/**
 * @author Ranie Jade Ramiso
 */
class VBoxBuilder(pane: VBox = VBox()): PaneBuilder<VBox>(pane) {
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

    fun fillWidthProperty() = node.fillWidthProperty()
    var fillWidth: Boolean
        get() = fillWidthProperty().get()
        set(value) {
            fillWidthProperty().set(value)
        }

    var <T: Node> Builder<T>.vgrow: Priority
        get() = VBox.getVgrow(this@vgrow.node)
        set(value) {
            VBox.setVgrow(this@vgrow.node, value)
        }

    var <T: Node> Builder<T>.vboxMargin: Insets
        get() = VBox.getMargin(this@vboxMargin.node)
        set(value) {
            VBox.setMargin(this@vboxMargin.node, value)
        }
}
