package io.polymorphicpanda.zerofx.sample.view

import io.polymorphicpanda.zerofx.sample.component.TodoDetailsComponent
import io.polymorphicpanda.zerofx.sample.domain.Todo
import io.polymorphicpanda.zerofx.view.View
import io.polymorphicpanda.zerofx.view.helpers.*
import javafx.beans.property.SimpleStringProperty
import javafx.beans.value.ChangeListener
import javafx.geometry.Pos

/**
 * @author Ranie Jade Ramiso
 */
class TodoDetailsView(component: TodoDetailsComponent): View<TodoDetailsComponent>(component) {
    val listener = ChangeListener<Todo> { obs, old, todo ->
        if (todo != null) {
            description.set(todo.description.get())
            details.set(todo.details.get())
        }
    }

    val description = SimpleStringProperty(this, "description")
    val details = SimpleStringProperty(this, "details")

    override val root by builder {
        stackPane {
            styleClass("todo-details")

            vbox {
                styleClass("content")
                visibleProperty().bind(component.todo.isNotNull)
                managedProperty().bind(visibleProperty())
                spacing = 10.0

                label {
                    styleClass("title")
                    textProperty().bind(description)
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
                visibleProperty().bind(component.todo.isNull)
                managedProperty().bind(visibleProperty())

                label {
                    text = "Nothing to show"
                }
            }
        }
    }

    override fun init() {
        super.init()
        component.todo.addListener(listener)
    }

    override fun destroy() {
        super.destroy()
        component.todo.removeListener(listener)
    }
}
