package io.polymorphicpanda.zerofx.sample

import io.polymorphicpanda.zerofx.ZeroApp
import io.polymorphicpanda.zerofx.sample.component.TodoAppComponent
import javafx.application.Application
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

/**
 * @author Ranie Jade Ramiso
 */
class Main: Application() {
    override fun start(primaryStage: Stage) {
        primaryStage.title = "Todo App"

        val zeroApp = object: ZeroApp(primaryStage) {
            override val main = TodoAppComponent::class

            override fun createScene(root: Parent): Scene {
                return Scene(root, 600.0, 300.0).apply {
                    stylesheets.add(Main::class.java.classLoader.getResource("app.css").toExternalForm())
                }
            }
        }

        zeroApp.init()
        primaryStage.show()
    }
}

fun main(vararg args: String) {
    Application.launch(Main::class.java)
}
