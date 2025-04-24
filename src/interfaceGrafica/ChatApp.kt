package interfaceGrafica

import javafx.application.Application
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.scene.text.Text
import javafx.stage.Stage

class ChatApp : Application(){
    override fun start(stage: Stage) {
        val root = BorderPane()

        // Sidebar - Lista de contatos
        val contactsList = VBox(10.0).apply {
            padding = Insets(10.0)
            style = "-fx-background-color: #30a532;"
            prefWidth = 250.0
        }

        val contacts = listOf("João", "Maria", "Pedro", "Ana", "Lucas")
        for (contact in contacts) {
            val label = Label(contact).apply {
                style = "-fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;"
                maxWidth = Double.MAX_VALUE
            }
            contactsList.children.add(label)
        }

        // Chat area
        val chatVBox = VBox(10.0).apply {
            padding = Insets(10.0)
            style = "-fx-background-color: #30a532;"
            Priority.ALWAYS
        }

        val chatScroll = ScrollPane(chatVBox).apply {
            isFitToWidth = true
            style = "-fx-background: transparent;"
        }

        val messageBox = HBox(10.0).apply {
            padding = Insets(10.0)
            alignment = Pos.CENTER_LEFT
            style = "-fx-background-color: #000100;"
        }

        val messageField = TextField().apply {
            promptText = "Digite uma mensagem"
            style = "-fx-background-color: #ffffff; -fx-text-fill: black;"
            Priority.ALWAYS
        }

        val sendButton = Button("Enviar").apply {
            style = "-fx-background-color: #000362; -fx-text-fill: white;"
            setOnAction {
                val msg = messageField.text
                if (msg.isNotEmpty()) {
                    val msgLabel = Text(msg).apply {
                        style = "-fx-fill: white;"
                    }
                    val msgContainer = HBox(msgLabel).apply {
                        alignment = Pos.CENTER_RIGHT
                        padding = Insets(5.0)
                    }
                    chatVBox.children.add(msgContainer)
                    messageField.clear()
                }
            }
        }

        messageBox.children.addAll(messageField, sendButton)

        val rightPanel = BorderPane().apply {
            center = chatScroll
            bottom = messageBox
        }

        root.left = contactsList
        root.center = rightPanel

        val scene = Scene(root, 900.0, 600.0)
        stage.scene = scene
        stage.title = "WhatsApp Clone (Kotlin + JavaFX)"
        stage.show()
    }
}