package io.polymorphicpanda.zerofx.sample.view

import io.polymorphicpanda.zerofx.sample.component.TodoDetailsComponent
import io.polymorphicpanda.zerofx.sample.domain.Todo
import io.polymorphicpanda.zerofx.view.View
import io.polymorphicpanda.zerofx.view.helpers.children
import io.polymorphicpanda.zerofx.view.helpers.styleClass
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.control.Label
import javafx.scene.layout.VBox

/**
 * @author Ranie Jade Ramiso
 */
class TodoDetailsView(component: TodoDetailsComponent): View<TodoDetailsComponent>(component) {
    val todo: SimpleObjectProperty<Todo> by lazy {
        object: SimpleObjectProperty<Todo>(this, "todo") {
            override fun invalidated() {
                super.invalidated()

                if (get() != null) {
                    title.text = get().description
                }
            }
        }
    }

    val title by lazy(LazyThreadSafetyMode.NONE) {
        Label()
    }

    override val root by lazy(LazyThreadSafetyMode.NONE) {
        VBox().apply {
            styleClass("todo-details")
            todo.bind(component.todo)
            children {
                + title
            }
        }
    }
}
