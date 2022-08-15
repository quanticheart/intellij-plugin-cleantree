package com.github.quanticheart.intellijplugincleantree.dialog

import com.intellij.ide.lightEdit.LightEditorInfoImpl.getEditor
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBScrollPane
import net.miginfocom.swing.MigLayout
import org.jetbrains.annotations.Nullable
import java.awt.Dimension
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*


class SampleDialog2Wrapper : DialogWrapper(true) {
    init {
        init()
        title = "Test DialogWrapper"
    }

    @Nullable
    override fun createCenterPanel(): JComponent {
        val list = arrayListOf("test 1", "test 2")
        val artifactTypeBox = ComboBox<String>()
        for (type in list) {
            artifactTypeBox.addItem(type)
        }
        artifactTypeBox.selectedItem = "test 1"
        artifactTypeBox.addActionListener { ActionEvent ->
            val selected = artifactTypeBox.selectedItem as String
        }

        val panel = JPanel(MigLayout("fill"))
        panel.add(JLabel("Type: "))
        panel.add(artifactTypeBox)

        return panel
    }
}