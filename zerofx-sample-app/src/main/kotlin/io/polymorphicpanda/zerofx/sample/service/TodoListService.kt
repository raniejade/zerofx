package io.polymorphicpanda.zerofx.sample.service

import io.polymorphicpanda.zerofx.sample.domain.Todo
import java.util.*

/**
 * @author Ranie Jade Ramiso
 */
object TodoListService {
    private var ids = 0;

    private val todos = LinkedList<Todo>()

    fun create(description: String): Todo {
        return Todo(++ids, description).apply {
            todos.add(this)
        }
    }

    fun delete(id: Int) {
        todos.removeAll { it.id == id }
    }

    fun get(id: Int): Todo? {
        return todos.find { it.id == id }
    }

    fun all(): List<Todo> = todos
}
