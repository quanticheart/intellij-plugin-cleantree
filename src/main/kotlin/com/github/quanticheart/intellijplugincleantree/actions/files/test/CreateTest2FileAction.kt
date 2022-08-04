package com.github.quanticheart.intellijplugincleantree.actions.files.test

import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFile
import com.intellij.util.PlatformIcons

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

    override fun hashCode(): Int = 0

    override fun equals(other: Any?): Boolean = other is CreateTest2FileAction

    override fun startInWriteAction() = false

    override fun createFile(name: String?, templateName: String?, dir: PsiDirectory?): PsiFile? {
        val template = FileTemplateManager
            .getInstance(dir!!.project)
            .getInternalTemplate(templateName!!)
        return createFileFromTemplate(name + "ViewModel", template, dir)
    }
}