package io.polymorphicpanda.zerofx

import io.polymorphicpanda.zerofx.component.Component
import io.polymorphicpanda.zerofx.template.Template
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
    abstract val main: KClass<out Component<*>>
    abstract protected fun createScene(root: Parent): Scene
    internal lateinit var instance: Component<*>

    fun init() {
        // should we allow users to override this?
        stage.onCloseRequest = EventHandler {
            onClose(it)
        }

        instance = create(main, null) {
            val root = when (template.root) {
                is Parent -> template.root as Parent
                else -> Group(template.root)
            }
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

    fun <T: Component<*>> create(klass: KClass<out T>, parent: Template?, attach: T.() -> Unit): T {
        return klass.primaryConstructor!!.call(this).apply {
            if (parent != null) {
                parent.components.add(this)
            }
            attach(this)
            template.init()
            init()
        }
    }
}
