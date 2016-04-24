package io.polymorphicpanda.zerofx

import io.polymorphicpanda.zerofx.component.Component
import javafx.event.EventHandler
import javafx.scene.Group
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.stage.WindowEvent
import kotlin.reflect.KClass
import kotlin.reflect.primaryConstructor

/**
 * @author Ranie Jade Ramiso
 */
abstract class ZeroApp(val stage: Stage) {
    abstract val main: KClass<out Component>
    abstract protected fun createScene(root: Parent): Scene
    internal lateinit var instance: Component

    fun init() {
        // should we allow users to override this?
        stage.onCloseRequest = EventHandler {
            onClose(it)
        }

        instance = main.primaryConstructor!!.call(this).apply {
            init()
            val root = when (view.root) {
                is Parent -> view.root as Parent
                else -> Group(view.root)
            }
            contentInit()
            stage.scene = createScene(root)
        }
    }

    fun destroy() {
        instance.destroy()
    }

    open fun onClose(): Boolean = true

    private fun onClose(event: WindowEvent) {
        if (onClose()) {
            destroy()
        } else {
            event.consume()
        }
    }
}
