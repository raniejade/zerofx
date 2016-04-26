package io.polymorphicpanda.zerofx.sample.component

import io.polymorphicpanda.zerofx.ZeroApp
import io.polymorphicpanda.zerofx.component.Component
import io.polymorphicpanda.zerofx.property.property
import io.polymorphicpanda.zerofx.sample.domain.Todo
import io.polymorphicpanda.zerofx.sample.template.TodoDetailsTemplate
import io.polymorphicpanda.zerofx.template.Template
import javafx.beans.property.ReadOnlyObjectProperty
import javafx.beans.property.ReadOnlyObjectWrapper

/**
 * @author Ranie Jade Ramiso
 */
class TodoDetailsComponent(app: ZeroApp): Component(app) {
    interface Bindings: Template.Binder<TodoDetailsComponent> {
        fun todoProperty(): ReadOnlyObjectProperty<Todo>
        val todo: Todo
    }

    private val _todo = property({
        ReadOnlyObjectWrapper<Todo>(this, "todo")
    })

    fun todoProperty() = _todo()
    var todo by _todo

    override fun createTemplate() = TodoDetailsTemplate(
            this,
            object: Bindings {
                override fun todoProperty() = this@TodoDetailsComponent._todo()
                override val todo by this@TodoDetailsComponent._todo
            }
    )
}
