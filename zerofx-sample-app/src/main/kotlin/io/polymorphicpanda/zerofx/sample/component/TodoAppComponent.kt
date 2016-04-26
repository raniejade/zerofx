package io.polymorphicpanda.zerofx.sample.component

import io.polymorphicpanda.zerofx.ZeroApp
import io.polymorphicpanda.zerofx.component.Component
import io.polymorphicpanda.zerofx.property.property
import io.polymorphicpanda.zerofx.sample.domain.Todo
import io.polymorphicpanda.zerofx.sample.service.TodoListService
import io.polymorphicpanda.zerofx.sample.template.TodoAppTemplate
import javafx.beans.property.*
import javafx.collections.FXCollections

/**
 * @author Ranie Jade Ramiso
 */
class TodoAppComponent(app: ZeroApp): Component(app) {
    interface Bindings: io.polymorphicpanda.zerofx.component.ViewBindings<TodoAppComponent> {
        fun descriptionProperty(): StringProperty
        var description: String

        fun selectedProperty(): ObjectProperty<Todo?>
        var selected: Todo?

        fun addTodo()

        fun todosProperty(): ReadOnlyListProperty<Todo>
    }

    private val todos = property({
        ReadOnlyListWrapper<Todo>(FXCollections.observableArrayList())
    })

    private val description = property({
        SimpleStringProperty(this, "description")
    })

    private val selected = property({
        SimpleObjectProperty<Todo>(this, "selected")
    })

    override fun init() {
        super.init()
        todos().addAll(TodoListService.all())
    }

    override fun createTemplate() = TodoAppTemplate(
            this,
            object: Bindings {
                override fun descriptionProperty() = this@TodoAppComponent.description()

                override var description by this@TodoAppComponent.description

                override fun selectedProperty() = this@TodoAppComponent.selected()

                override var selected by this@TodoAppComponent.selected

                override fun addTodo() {
                    TodoListService.create(description).apply {
                        this@TodoAppComponent.todos().add(this)
                        this@TodoAppComponent.description().set("")
                }
            }

        override fun todosProperty() = this@TodoAppComponent.todos().readOnlyProperty

    })
}
