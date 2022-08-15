package com.github.quanticheart.intellijplugincleantree.actions.test

import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFile
import com.intellij.util.PlatformIcons

class CreateTestFileAction :
    CreateFileFromTemplateAction("Test File", "Creates new test File", PlatformIcons.CLASS_ICON), DumbAware {
    override fun buildDialog(project: Project, directory: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
        builder.setTitle("New File")
    }

    override fun getActionName(
        directory: PsiDirectory, newName: String,
        templateName: String
    ): String {
        return "Test File"
    }

    override fun createFile(name: String, templateName: String, dir: PsiDirectory): PsiFile {
        val template = FileTemplateManager
            .getInstance(dir.project)
            .getTemplate("Test Class.kt") // the template file "test.kt.ft" is stored under resources/fileTemplates/
        return createFileFromTemplate(name, template, dir).originalFile
    }
}