package com.github.quanticheart.intellijplugincleantree.utils

import com.esotericsoftware.minlog.Log.debug
import com.google.wireless.android.sdk.stats.IntellijProjectSizeStats
import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.ui.Messages
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.PsiManager
import com.intellij.psi.impl.file.PsiDirectoryFactory
import org.jetbrains.kotlin.idea.KotlinFileType
import org.jetbrains.kotlin.idea.caches.project.NotUnderContentRootModuleInfo.project


fun PsiDirectory.createFileFromText(name: String, templateName: String) {
        PsiFileFactory.getInstance(project)
            .createFileFromText(name, KotlinFileType.INSTANCE, templateName, System.currentTimeMillis(), false)
}

fun PsiDirectory.createFile(name: String, templateName: String) {
    val template = FileTemplateManager
        .getInstance(project)
        .getInternalTemplate(templateName)
    CreateFileFromTemplateAction.createFileFromTemplate(name, template, this, null, true)
}

fun AnActionEvent.getDirectory(): PsiDirectory? {
    val currentFile = getData(PlatformDataKeys.VIRTUAL_FILE)!!
    return PsiManager.getInstance(project!!).findDirectory(currentFile)
}

fun PsiDirectory.createSubDirectory(pathName: String): PsiDirectory {
    return try {
        findSubdirectory(pathName)
            ?: createSubdirectory(pathName)
    } catch (e: Exception) {
        print(e)
        createSubdirectory(pathName)
    }
}

fun PsiDirectory.verifyFileExits(fileName: String, createFileAction: () -> Unit) {
    val exists = findFile(fileName) != null
    if (exists) {
        val ans = Messages.showOkCancelDialog(
            "File already exists. Do you want to override?",
            "File already exist",
            "Override",
            "Cancel",
            null
        )
        if (ans == Messages.YES) {
            findFile(fileName)?.delete()
            createFileAction()
        }
    } else {
        createFileAction()
    }
}

fun getOrCreateDrawableDir(moduleName: String?, dirName: String?): PsiDirectory? {
    val baseDir: PsiDirectory? = project?.baseDir
        ?.let { PsiDirectoryFactory.getInstance(project).createDirectory(it) }
    val moduleDir = baseDir?.findSubdirectory(moduleName!!)
    if (moduleDir != null) {
        var srcDir = moduleDir.findSubdirectory("src")
        if (srcDir == null) {
            srcDir = moduleDir.createSubdirectory("src")
            debug("Creating dir :" + srcDir.name)
        }
        var mainDir = srcDir.findSubdirectory("main")
        if (mainDir == null) {
            mainDir = srcDir.createSubdirectory("main")
            debug("Creating dir :" + mainDir.name)
        }
        var resDir = mainDir.findSubdirectory("res")
        if (resDir == null) {
            resDir = mainDir.createSubdirectory("res")
            debug("Creating dir :" + resDir.name)
        }
        var drawableDir = resDir.findSubdirectory(dirName!!)
        if (drawableDir == null) {
            drawableDir = resDir.createSubdirectory(dirName)
            debug("Creating dir :" + drawableDir.name)
        }
        return drawableDir
    }
    return null
}

//fun findOrCreateSubdirectory(parent: PsiDirectory, subdirName: String?): PsiDirectory? {
//    val sub = parent.findSubdirectory(subdirName!!)
//    return sub ?: WriteAction.compute { parent.createSubdirectory(subdirName) }
//}

fun AnActionEvent.getFileType() = with(getData(CommonDataKeys.PSI_FILE)) {
    this?.run {
        "[${language.displayName.toLowerCase()}]"
    }
} ?: ""