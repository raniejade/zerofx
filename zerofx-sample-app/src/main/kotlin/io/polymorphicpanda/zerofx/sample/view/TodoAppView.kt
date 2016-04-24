package io.polymorphicpanda.zerofx.sample.view

import io.polymorphicpanda.zerofx.sample.component.TodoAppComponent
import io.polymorphicpanda.zerofx.sample.component.TodoDetailsComponent
import io.polymorphicpanda.zerofx.sample.domain.Todo
import io.polymorphicpanda.zerofx.view.View
import io.polymorphicpanda.zerofx.view.helpers.children
import io.polymorphicpanda.zerofx.view.helpers.styleClass
import javafx.beans.binding.Bindings
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.util.Callback
import java.util.concurrent.Callable

/**
 * @author Ranie Jade Ramiso
 */
class TodoAppView(component: TodoAppComponent): View<TodoAppComponent>(component) {
    override val root by lazy(LazyThreadSafetyMode.NONE) {
        BorderPane().apply {
            styleClass("main-component")

            left = VBox().apply {
                BorderPane.setMargin(this, Insets(5.0))
                alignment = Pos.CENTER_LEFT
                spacing = 5.0
                isFillWidth = true
                children {
                    + HBox().apply {
                        isFillHeight = true
                        spacing = 5.0
                        children {
                            + TextField().apply {

                                textProperty().bindBidirectional(component.newTodoDescription)
                                promptText = "Create new todo"
                                HBox.setHgrow(this, Priority.ALWAYS)
                            }


                            + Button("+").apply {
                                HBox.setHgrow(this, Priority.ALWAYS)
                                val blank = Bindings.createBooleanBinding(Callable {
                                    component.newTodoDescription.get().isNullOrBlank()
                                }, component.newTodoDescription)
                                disableProperty().bind(blank)
                                onAction = EventHandler {
                                    component.addTodo()
                                }
                            }
                        }
                    }
                    + Label("Todo List").apply {
                        styleClass("todo-list-label")
                    }
                    + createListView().apply {
                        VBox.setVgrow(this, Priority.ALWAYS)
                    }
                }
            }

            val details = create(TodoDetailsComponent::class).apply {
                todo.bind(component.selected)
            }

            details.view.root.apply {
                BorderPane.setMargin(this, Insets(5.0))
                center = this
            }
        }
    }

    private fun createListView(): ListView<Todo> {
        return ListView<Todo>().apply {
            styleClass("todos")
            cellFactory = Callback {
                object: ListCell<Todo>() {
                    override fun updateItem(item: Todo?, empty: Boolean) {
                        super.updateItem(item, empty)

                        if (!empty && item != null) {
                            text = item.description.get()
                        }
                    }
                }
            }
            selectionModel.apply {
                component.selected.bind(this.selectedItemProperty())
            }

            itemsProperty().bind(component.todos)
        }
    }
}
