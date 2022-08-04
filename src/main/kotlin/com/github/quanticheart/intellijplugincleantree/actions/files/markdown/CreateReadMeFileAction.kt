package com.github.quanticheart.intellijplugincleantree.actions.files.markdown

import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.fileEditor.impl.LoadTextUtil
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFileFactory
import com.intellij.util.ResourceUtil


class CreateReadMeFileAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
//        val url = ResourceUtil.getResource(javaClass, "templates", "query.php") // it contains an empty PHP class
//
//        val virtualFile = VfsUtil.findFileByURL(url)
//
        val file = PsiFileFactory.getInstance(event.project)

//        val directory: PsiDirectory = LangDataKeys.IDE_VIEW.getData(event.dataContext).getOrChooseDirectory()
//        event.project?.projectFilePath
//        directory.copyFileFrom("query.php", file)

        event.getData(CommonDataKeys.PSI_FILE)?.run {
            createFile("README", "ReadME", parent)
        }
        val psiFile = event.dataContext.getData(CommonDataKeys.PSI_FILE)

        println(event.actionManager)
        println(psiFile)
    }

    private fun createFile(name: String?, templateName: String?, dir: PsiDirectory?) {
        val template = FileTemplateManager
            .getInstance(dir!!.project)
            .getInternalTemplate(templateName!!)
        CreateFileFromTemplateAction.createFileFromTemplate(name, template, dir, null, true)
        println("Create query 2")
    }
}