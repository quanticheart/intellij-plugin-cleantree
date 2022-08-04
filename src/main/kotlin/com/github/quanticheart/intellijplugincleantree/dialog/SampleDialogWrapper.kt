package com.github.quanticheart.intellijplugincleantree.dialog

import com.intellij.openapi.ui.DialogWrapper
import org.jetbrains.annotations.Nullable
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.ActionEvent
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel


class SampleDialogWrapper : DialogWrapper(true) {
    init {
        init()
        title = "Test DialogWrapper"
    }

    @Nullable
    override fun createCenterPanel(): JComponent {
        val dialogPanel = JPanel(BorderLayout())
        val label = JLabel("testing")
//        label.preferredSize = Dimension(100, 100)
        dialogPanel.add(label, BorderLayout.CENTER)

//        val testButton = JButton()
//        testButton.addActionListener {
//            if (SampleDialogWrapper().showAndGet()) {
//                // user pressed ok
//            }
//        }
        return dialogPanel
    }
}