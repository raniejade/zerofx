package io.polymorphicpanda.zerofx.view.helpers

import javafx.collections.ObservableList
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.scene.control.MultipleSelectionModel
import javafx.util.Callback

/**
 * @author Ranie Jade Ramiso
 */
class ListViewBuilder<T>(listView: ListView<T> = ListView()): Builder<ListView<T>>(listView) {
    fun selectionModelProperty() = node.selectionModelProperty()
    var selectionModel: MultipleSelectionModel<T>
        get() = selectionModelProperty().get()
        set(value) {
            selectionModelProperty().set(value)
        }

    fun selectedItemProperty() = selectionModel.selectedItemProperty()
    val selectedItem = selectedItemProperty().get()

    fun cellFactoryProperty() = node.cellFactoryProperty()
    var cellFactory: Callback<ListView<T>, ListCell<T>>
        get() = cellFactoryProperty().get()
        set(value) {
            cellFactoryProperty().set(value)
        }

    fun itemsProperty() = node.itemsProperty()
    var items: ObservableList<T>
        get() =  itemsProperty().get()
        set(value) {
            itemsProperty().set(value)
        }
}
