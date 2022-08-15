package com.github.quanticheart.intellijplugincleantree.dialog

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import org.jetbrains.annotations.Nullable
import java.awt.BorderLayout
import java.awt.event.ActionEvent
import javax.swing.*


class DialogNameActionWrapper(
    titleDialog: String,
    private val callback: (name: String) -> Unit
) : DialogWrapper(true) {
    private var field : JTextField = JTextField("",1)

    init {
        init()
        title = titleDialog

    }
    @Nullable
    override fun createCenterPanel(): JComponent {
        val dialogPanel = JPanel(BorderLayout())
        val label = JLabel("Type file name")
        dialogPanel.add(label, BorderLayout.CENTER)
        field.isEditable = true
        field.isEnabled = true
        field.requestFocus()
        dialogPanel.add(field)
        return dialogPanel
    }

    override fun doOKAction() {
        callback.invoke(field.text)
        super.doOKAction()
    }
}
