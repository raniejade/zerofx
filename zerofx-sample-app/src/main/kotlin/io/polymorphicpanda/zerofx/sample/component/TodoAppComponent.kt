package io.polymorphicpanda.zerofx.sample.component

import io.polymorphicpanda.zerofx.ZeroApp
import io.polymorphicpanda.zerofx.component.Component
import io.polymorphicpanda.zerofx.sample.domain.Todo
import io.polymorphicpanda.zerofx.sample.service.TodoListService
import io.polymorphicpanda.zerofx.sample.template.TodoAppTemplate
import javafx.beans.property.ReadOnlyListWrapper
import javafx.collections.FXCollections

/**
 * @author Ranie Jade Ramiso
 */
class TodoAppComponent(app: ZeroApp): Component<TodoAppTemplate>(app) {
    private val _todos = ReadOnlyListWrapper<Todo>(FXCollections.observableArrayList())

    override fun init() {
        super.init()
        _todos.addAll(TodoListService.all())
        template.todosProperty().bind(_todos)

        template.onAddTodo().subscribe {
            val todo = TodoListService.create(template.description)
            template.description = ""
            _todos.add(todo)
        }
    }

    override fun createTemplate(app: ZeroApp) = TodoAppTemplate(app)
}
