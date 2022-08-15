package com.github.quanticheart.intellijplugincleantree.actions.github

import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.DialogWrapper
import net.miginfocom.swing.MigLayout
import org.jetbrains.annotations.Nullable
import javax.swing.*


class DialogActionSelectWrapper(private val callback: OnGithubActionSelected) : DialogWrapper(true) {

    private val list = arrayListOf(
        "Web FTP Deploy",
        "Telegram Alert"
    )

    init {
        init()
        title = "Github Actions"
    }

    @Nullable
    override fun createCenterPanel(): JComponent {
        var selected = ""
        val artifactTypeBox = ComboBox<String>()
        for (type in list) {
            artifactTypeBox.addItem(type)
        }
        artifactTypeBox.selectedItem = list[0]
        artifactTypeBox.addActionListener {
            selected = artifactTypeBox.selectedItem as String
        }

        val panel = JPanel(MigLayout("fill"))
        panel.add(JLabel("Type: "))
        panel.add(artifactTypeBox)

        val selectBtn = JButton()
        selectBtn.addActionListener {
            handle(callback, selected = selected)
        }
        panel.add(selectBtn)
        return panel
    }

    private fun handle(callback: OnGithubActionSelected, selected: String) {
        when (selected) {
            "Web FTP Deploy" -> callback.action(TypeGithubActions.WEB_FTP_DEPLOY)
            "Telegram Alert" -> callback.action(TypeGithubActions.TELEGRAM_ALERT)
        }
    }
}

enum class TypeGithubActions {
    WEB_FTP_DEPLOY, TELEGRAM_ALERT
}
