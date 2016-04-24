package io.polymorphicpanda.zerofx.sample.view

import io.polymorphicpanda.zerofx.sample.component.TodoAppComponent
import io.polymorphicpanda.zerofx.sample.component.TodoDetailsComponent
import io.polymorphicpanda.zerofx.sample.domain.Todo
import io.polymorphicpanda.zerofx.view.View
import io.polymorphicpanda.zerofx.view.helpers.*
import javafx.beans.binding.Bindings
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.ListCell
import javafx.scene.layout.Priority
import javafx.util.Callback
import java.util.concurrent.Callable

/**
 * @author Ranie Jade Ramiso
 */
class TodoAppView(component: TodoAppComponent): View<TodoAppComponent>(component) {
    override val root by builder {
        borderPane {
            styleClass("main-component")
            left {
                vbox {
                    borderPaneMargin = Insets(5.0)
                    alignment = Pos.CENTER_LEFT
                    spacing = 5.0
                    fillWidth = true

                    hbox {
                        fillHeight = true
                        spacing = 5.0

                        textField {
                            promptText = "Create new todo"
                            hgrow = Priority.ALWAYS
                            textProperty().bindBidirectional(component.newTodoDescription)
                        }

                        button {
                            text = "+"
                            hgrow = Priority.ALWAYS

                            val blank = Bindings.createBooleanBinding(Callable {
                                component.newTodoDescription.get().isNullOrBlank()
                            }, component.newTodoDescription)

                            disableProperty().bind(blank)

                            onAction {
                                component.addTodo()
                            }
                        }
                    }

                    label {
                        text = "Todo List"
                    }

                    listView<Todo> {
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

                        component.selected.bind(selectedItemProperty())
                        itemsProperty().bind(component.todos)
                    }
                }
            }

            center {
                component(create(TodoDetailsComponent::class)) {
                    borderPaneMargin = Insets(5.0)
                    this.component.todo.bind(component.selected)
                }
            }
        }
    }
}
