package io.polymorphicpanda.zerofx.sample.template

import io.polymorphicpanda.zerofx.ZeroApp
import io.polymorphicpanda.zerofx.sample.domain.Todo
import io.polymorphicpanda.zerofx.template.Template
import io.polymorphicpanda.zerofx.template.helpers.*
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.value.ChangeListener
import javafx.geometry.Pos

/**
 * @author Ranie Jade Ramiso
 */
class TodoDetailsTemplate(app: ZeroApp): Template(app) {
    val listener = ChangeListener<Todo> { obs, old, todo ->
        if (todo != null) {
            _description.set(todo.description.get())
            _details.set(todo.details.get())
        }
    }

    private val _todo = SimpleObjectProperty<Todo>(this, "todo")
    fun todoProperty(): ObjectProperty<Todo> = _todo

    private val _description = SimpleStringProperty(this, "description")
    private val _details = SimpleStringProperty(this, "details")

    override val root by builder {
        stackPane {
            styleClass("todo-details")

            vbox {
                styleClass("content")
                visibleProperty().bind(_todo.isNotNull)
                managedProperty().bind(visibleProperty())
                spacing = 10.0

                label {
                    styleClass("title")
                    textProperty().bind(_description)
                }

                label {
                    text = "Details"
                }

                textArea {
                    styleClass("details")
                    focusTraversable = false
                    promptText = "Add some details"
                    prefRowCount = 5
                }
            }

            vbox {
                styleClass("empty")
                alignment = Pos.CENTER
                visibleProperty().bind(_todo.isNull)
                managedProperty().bind(visibleProperty())

                label {
                    text = "Nothing to show"
                }
            }
        }
    }

    override fun init() {
        super.init()
        _todo.addListener(listener)
    }

    override fun destroy() {
        super.destroy()
        _todo.removeListener(listener)
    }
}
