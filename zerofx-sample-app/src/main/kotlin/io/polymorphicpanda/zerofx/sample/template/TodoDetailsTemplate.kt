package io.polymorphicpanda.zerofx.sample.template

import io.polymorphicpanda.zerofx.sample.component.TodoDetailsComponent
import io.polymorphicpanda.zerofx.sample.domain.Todo
import io.polymorphicpanda.zerofx.template.Template
import io.polymorphicpanda.zerofx.template.helpers.*
import javafx.beans.property.SimpleStringProperty
import javafx.beans.value.ChangeListener
import javafx.geometry.Pos

/**
 * @author Ranie Jade Ramiso
 */
class TodoDetailsTemplate(component: TodoDetailsComponent,
                          bindings: TodoDetailsComponent.Bindings)
    : Template<TodoDetailsComponent, TodoDetailsComponent.Bindings>(component, bindings) {
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
                visibleProperty().bind(bindings.todoProperty().isNotNull)
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
                visibleProperty().bind(bindings.todoProperty().isNull)
                managedProperty().bind(visibleProperty())

                label {
                    text = "Nothing to show"
                }
            }
        }
    }

    override fun init() {
        super.init()
        bindings.todoProperty().addListener(listener)
    }

    override fun destroy() {
        super.destroy()
        bindings.todoProperty().removeListener(listener)
    }
}
