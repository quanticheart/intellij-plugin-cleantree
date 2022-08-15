package com.github.quanticheart.intellijplugincleantree.actions.test

import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFile
import com.intellij.ui.CheckBoxList
import com.intellij.ui.CheckBoxListListener
import com.intellij.ui.components.JBScrollPane
import com.intellij.util.PlatformIcons
import net.miginfocom.swing.MigLayout
import java.awt.Dimension
import java.awt.Font
import javax.swing.BorderFactory
import javax.swing.JPanel
import javax.swing.border.Border
import javax.swing.border.CompoundBorder
import javax.swing.border.TitledBorder

class CreateTest2FileAction : CreateFileFromTemplateAction(
    "requirements.txt",
    "Create a new requirements.txt file",
    PlatformIcons.CLASS_ICON
), DumbAware {

    override fun buildDialog(project: Project, directory: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
        builder.setTitle("Create a new requirements.txt file. Enter prefix")
            .addKind(
                "ViewModel",
                PlatformIcons.CLASS_ICON,
                "ViewModel"
            )
    }

    override fun getActionName(
        directory: PsiDirectory,
        newName: String, templateName: String
    ): String {
        return "Create A New Requirements File"
    }


    override fun createFile(name: String?, templateName: String?, dir: PsiDirectory?): PsiFile? {
        val template = FileTemplateManager
            .getInstance(dir!!.project)
            .getInternalTemplate(templateName!!)
        return createFileFromTemplate(name + "ViewModel", template, dir)
    }

}