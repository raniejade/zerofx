package io.polymorphicpanda.zerofx.sample.component

import io.polymorphicpanda.zerofx.ZeroApp
import io.polymorphicpanda.zerofx.component.Component
import io.polymorphicpanda.zerofx.sample.domain.Todo
import io.polymorphicpanda.zerofx.sample.view.TodoDetailsView
import javafx.beans.property.SimpleObjectProperty

/**
 * @author Ranie Jade Ramiso
 */
class TodoDetailsComponent(app: ZeroApp): Component(app) {
    override val view = TodoDetailsView(this)

    val todo by lazy(LazyThreadSafetyMode.NONE) {
        SimpleObjectProperty<Todo>(this, "todo")
    }
}
