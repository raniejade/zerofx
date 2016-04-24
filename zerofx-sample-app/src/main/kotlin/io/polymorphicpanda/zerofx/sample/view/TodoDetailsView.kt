package io.polymorphicpanda.zerofx.sample.view

import io.polymorphicpanda.zerofx.sample.component.TodoDetailsComponent
import io.polymorphicpanda.zerofx.sample.domain.Todo
import io.polymorphicpanda.zerofx.view.View
import io.polymorphicpanda.zerofx.view.helpers.children
import io.polymorphicpanda.zerofx.view.helpers.styleClass
import javafx.beans.property.SimpleObjectProperty
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox

/**
 * @author Ranie Jade Ramiso
 */
class TodoDetailsView(component: TodoDetailsComponent): View<TodoDetailsComponent>(component) {
    val todo: SimpleObjectProperty<Todo> by lazy {
        object: SimpleObjectProperty<Todo>(this, "todo") {
            override fun invalidated() {
                super.invalidated()

                val todo = get()
                if (todo != null) {
                    title.text = todo.description.get()
                    details.text = todo.details.get()
                }
            }
        }
    }

    val title by lazy(LazyThreadSafetyMode.NONE) {
        Label("<Empty>")
    }

    val details by lazy(LazyThreadSafetyMode.NONE) {
        TextArea()
    }

    val content by lazy(LazyThreadSafetyMode.NONE) {
        VBox()
    }

    val empty by lazy(LazyThreadSafetyMode.NONE) {
        VBox()
    }

    override val root by lazy(LazyThreadSafetyMode.NONE) {
        StackPane().apply {
            styleClass("todo-details")
            todo.bind(component.todo)
            children {
                + content.apply {
                    styleClass("content")
                    visibleProperty().bind(todo.isNotNull)
                    managedProperty().bind(visibleProperty())
                    spacing = 10.0
                    children {
                        + title.apply {
                            styleClass("title")
                        }
                        + Label("Details").apply {
                            styleClass("details-label")
                        }
                        + details.apply {
                            styleClass("details")
                            isFocusTraversable = false
                            prefRowCount = 5
                            promptText = "Add some details"
                        }
                    }
                }

                + empty.apply {
                    styleClass("empty")
                    visibleProperty().bind(todo.isNull)
                    managedProperty().bind(visibleProperty())
                    alignment = Pos.CENTER
                    children {
                        + Label("Nothing to show")
                    }
                }
            }
        }
    }
}
