package io.polymorphicpanda.zerofx.sample.view

import io.polymorphicpanda.zerofx.sample.component.TodoAppComponent
import io.polymorphicpanda.zerofx.sample.component.TodoDetailsComponent
import io.polymorphicpanda.zerofx.sample.domain.Todo
import io.polymorphicpanda.zerofx.view.View
import io.polymorphicpanda.zerofx.view.helpers.*
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.layout.*
import javafx.util.Callback

/**
 * @author Ranie Jade Ramiso
 */
class TodoAppView(component: TodoAppComponent): View<TodoAppComponent>(component) {
    override val root by lazy(LazyThreadSafetyMode.NONE) {
        BorderPane().apply {
            styleClass("main-component")
            left = VBox().apply {
                margin(this, Insets(5.0))
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
                                hgrow(this, Priority.ALWAYS)
                            }


                            + Button("Add"). apply {
                                hgrow(this, Priority.ALWAYS)
                                disableProperty().bind(component.newTodoDescription.isEmpty)
                                onAction = EventHandler {
                                    component.addTodo()
                                }
                            }
                        }
                    }
                    + Label("Todo List")
                    + createListView().apply {
                        vgrow(this, Priority.ALWAYS)
                    }
                }
            }

            center = StackPane().apply {
                children {
                    + create(TodoDetailsComponent::class).apply {
                        todo.bind(component.selected)

                        // we need to do this?
                        var once = false
                        component.selected.addListener({ obs, old, selected ->
                            if (!once) {
                                todo
                                once = true
                            }
                        })
                    }
                }
            }
        }
    }

    private fun createListView(): ListView<Todo> {
        return ListView<Todo>().apply {
            styleClass("todos")
            isFocusTraversable = false
            cellFactory = Callback {
                object: ListCell<Todo>() {
                    override fun updateItem(item: Todo?, empty: Boolean) {
                        super.updateItem(item, empty)

                        if (!empty && item != null) {
                            text = item.description
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
