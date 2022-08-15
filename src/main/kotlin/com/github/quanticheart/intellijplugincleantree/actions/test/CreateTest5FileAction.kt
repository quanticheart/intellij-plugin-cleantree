package com.github.quanticheart.intellijplugincleantree.actions.test


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
import org.jetbrains.kotlin.idea.KotlinLanguage

class CreateTest5FileAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {

        val currentFile = e.getData(PlatformDataKeys.VIRTUAL_FILE)!!
        val psiDirectory: PsiDirectory? =
            PsiManager.getInstance(e.project!!).findDirectory(currentFile)

        val subdirectory: PsiDirectory? =
            psiDirectory?.findSubdirectory("subDir") ?: psiDirectory?.createSubdirectory("newTest")
        print(psiDirectory)
        print(subdirectory)

        val language = KotlinLanguage.INSTANCE

        val template = FileTemplateManager
            .getInstance(e.project!!)
            .getInternalTemplate("ReadME")

//        val psiFile =
//            PsiFileFactory
//                .getInstance(project)
//                .createFileFromText("test", language, fileContent)
//
//        psiDirectory?.add(psiFile)


        createFileFromTemplate("README", template, psiDirectory!!, "", true)
    }
}