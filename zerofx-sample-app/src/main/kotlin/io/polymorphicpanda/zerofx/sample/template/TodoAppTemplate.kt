package io.polymorphicpanda.zerofx.sample.template

import io.polymorphicpanda.zerofx.sample.component.TodoAppComponent
import io.polymorphicpanda.zerofx.sample.component.TodoDetailsComponent
import io.polymorphicpanda.zerofx.sample.domain.Todo
import io.polymorphicpanda.zerofx.template.Template
import io.polymorphicpanda.zerofx.template.helpers.*
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
class TodoAppTemplate(component: TodoAppComponent,
                      bindings: TodoAppComponent.Bindings)
    : Template<TodoAppComponent, TodoAppComponent.Bindings>(component, bindings) {
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
                            textProperty().bindBidirectional(bindings.descriptionProperty())
                        }

                        button {
                            text = "+"
                            hgrow = Priority.ALWAYS

                            val blank = Bindings.createBooleanBinding(Callable {
                                bindings.description.isNullOrBlank()
                            }, bindings.descriptionProperty())

                            disableProperty().bind(blank)

                            action {
                                bindings.addTodo()
                            }
                        }
                    }

                    label("Todo List")

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

                        bindings.selectedProperty().bind(selectedItemProperty())
                        itemsProperty().bind(bindings.todosProperty())
                    }
                }
            }

            center {
                component(TodoDetailsComponent::class) {
                    borderPaneMargin = Insets(5.0)
                    this.component.todoProperty().bind(bindings.selectedProperty())
                }
            }
        }
    }
}
