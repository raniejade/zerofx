package io.polymorphicpanda.zerofx.sample.domain

import javafx.beans.property.SimpleStringProperty

/**
 * @author Ranie Jade Ramiso
 */
data class Todo(val id: Int) {
    constructor(id: Int, description: String): this(id) {
        this.description.set(description)
    }
    val description = SimpleStringProperty(this, "description")
    val details = SimpleStringProperty(this, "details")
}
