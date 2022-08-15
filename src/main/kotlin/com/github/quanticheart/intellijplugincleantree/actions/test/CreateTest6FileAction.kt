package com.github.quanticheart.intellijplugincleantree.actions.test


import com.github.quanticheart.intellijplugincleantree.dialog.SampleDialog2Wrapper
import com.github.quanticheart.intellijplugincleantree.dialog.SampleDialogWrapper
import com.intellij.ide.actions.CreateFileFromTemplateAction.createFileFromTemplate
import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DataKeys
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.PsiManager
import com.intellij.ui.CheckBoxList
import com.intellij.ui.components.JBScrollPane
import net.miginfocom.swing.MigLayout
import org.jetbrains.kotlin.idea.KotlinLanguage
import java.awt.Dimension
import java.awt.Font
import javax.swing.BorderFactory
import javax.swing.JPanel
import javax.swing.border.Border
import javax.swing.border.CompoundBorder
import javax.swing.border.TitledBorder

class CreateTest6FileAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
       val d =  SampleDialog2Wrapper()
        d.showAndGet()
    }
}