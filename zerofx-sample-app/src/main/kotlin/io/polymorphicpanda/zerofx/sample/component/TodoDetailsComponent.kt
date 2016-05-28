package io.polymorphicpanda.zerofx.sample.component

import io.polymorphicpanda.zerofx.ZeroApp
import io.polymorphicpanda.zerofx.component.Component
import io.polymorphicpanda.zerofx.sample.domain.Todo
import io.polymorphicpanda.zerofx.sample.template.TodoDetailsTemplate
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty

/**
 * @author Ranie Jade Ramiso
 */
class TodoDetailsComponent(app: ZeroApp): Component<TodoDetailsTemplate>(app) {
    private val _todo = SimpleObjectProperty<Todo>(this, "todo")
    fun todoProperty(): ObjectProperty<Todo> = _todo

    override fun init() {
        super.init()
        template.todoProperty().bind(_todo)
    }

    override fun createTemplate(app: ZeroApp) = TodoDetailsTemplate(app)
}
