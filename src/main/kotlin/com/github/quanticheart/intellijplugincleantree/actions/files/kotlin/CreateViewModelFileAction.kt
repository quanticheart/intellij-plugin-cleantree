package com.github.quanticheart.intellijplugincleantree.actions.files.kotlin

import com.github.quanticheart.intellijplugincleantree.actions.files.test.CreateTest2FileAction
import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.psi.*
import com.intellij.util.PlatformIcons

class CreateViewModelFileAction : CreateFileFromTemplateAction(
    "ViewModel",
    "Create a new view model file",
    PlatformIcons.CLASS_ICON
), DumbAware {
    override fun buildDialog(project: Project, directory: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
        builder.setTitle("Enter prefix, Exp .: Login or SignUp")
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
        return "Create A New ViewModel File"
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