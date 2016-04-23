package io.polymorphicpanda.zerofx.sample.component

import io.polymorphicpanda.zerofx.ZeroApp
import io.polymorphicpanda.zerofx.component.Component
import io.polymorphicpanda.zerofx.sample.domain.Todo
import io.polymorphicpanda.zerofx.sample.service.TodoListService
import io.polymorphicpanda.zerofx.sample.view.TodoAppView
import javafx.beans.property.ReadOnlyListWrapper
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections

/**
 * @author Ranie Jade Ramiso
 */
class TodoAppComponent(app: ZeroApp): Component(app) {
    override val view = TodoAppView(this)
    private val _todos = ReadOnlyListWrapper<Todo>(FXCollections.observableArrayList())

    val todos by lazy(LazyThreadSafetyMode.NONE) { _todos.readOnlyProperty }

    val newTodoDescription by lazy(LazyThreadSafetyMode.NONE) {
        SimpleStringProperty(this, "description")
    }

    val selected by lazy(LazyThreadSafetyMode.NONE) {
        SimpleObjectProperty<Todo>(this, "selected")
    }

    override fun init() {
        super.init()
        _todos.addAll(TodoListService.all())
    }

    fun addTodo() {
        TodoListService.create(newTodoDescription.get()).apply {
            _todos.add(this)
            newTodoDescription.set("")
        }
    }
}
