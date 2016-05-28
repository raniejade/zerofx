package io.polymorphicpanda.zerofx.sample.template

import io.polymorphicpanda.zerofx.ZeroApp
import io.polymorphicpanda.zerofx.sample.component.TodoDetailsComponent
import io.polymorphicpanda.zerofx.sample.domain.Todo
import io.polymorphicpanda.zerofx.template.Template
import io.polymorphicpanda.zerofx.template.helpers.*
import javafx.beans.binding.Bindings
import javafx.beans.property.*
import javafx.event.ActionEvent
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.ListCell
import javafx.scene.layout.Priority
import javafx.util.Callback
import rx.subjects.BehaviorSubject
import java.util.concurrent.Callable

/**
 * @author Ranie Jade Ramiso
 */
class TodoAppTemplate(app: ZeroApp): Template(app) {
    private val _description: StringProperty = SimpleStringProperty(this, "description")
    fun descriptionProperty() = _description
    var description: String
        get() = _description.get()
        set(value) {
            _description.set(value)
        }

    private val onAddTodoSubject = BehaviorSubject.create<ActionEvent>()
    fun onAddTodo() = onAddTodoSubject.asObservable()

    private val _selectedTodo: ObjectProperty<Todo> = SimpleObjectProperty(this, "selectedTodo")
    fun selectedTodoProperty(): ReadOnlyObjectProperty<Todo> = _selectedTodo

    private val _todos: ListProperty<Todo> = SimpleListProperty<Todo>(this, "todos")
    fun todosProperty() = _todos

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
                            textProperty().bindBidirectional(_description)
                        }

                        button {
                            text = "+"
                            hgrow = Priority.ALWAYS

                            val blank = Bindings.createBooleanBinding(Callable {
                                _description.get().isNullOrBlank()
                            }, _description)

                            disableProperty().bind(blank)

                            action {
                                onAddTodoSubject.onNext(it)
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
                        _selectedTodo.bind(selectedItemProperty())
                        itemsProperty().bind(_todos)
                    }
                }
            }

            center {
                component(TodoDetailsComponent::class) {
                    borderPaneMargin = Insets(5.0)
                    component.todoProperty().bind(selectedTodoProperty())
                }
            }
        }
    }
}
