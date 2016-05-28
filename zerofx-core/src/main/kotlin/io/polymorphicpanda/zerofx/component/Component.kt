package io.polymorphicpanda.zerofx.component

import io.polymorphicpanda.zerofx.ZeroApp
import io.polymorphicpanda.zerofx.template.Template

/**
 * @author Ranie Jade Ramiso
 */
abstract class Component<out T: Template>(val app: ZeroApp) {
    val template: T by lazy {
        createTemplate(app)
    }

    open fun init() { }

    open fun destroy() {
        template.destroy()
    }

    protected abstract fun createTemplate(app: ZeroApp): T
}
