package com.github.quanticheart.intellijplugincleantree.wizard

import com.android.tools.idea.wizard.template.Constraint
import com.android.tools.idea.wizard.template.stringParameter
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFileFactory
import org.jetbrains.kotlin.idea.KotlinLanguage
import java.lang.StringBuilder

val defaultPackageNameParameter
    get() = stringParameter {
        name = "Package name"
        visible = { !isNewModule }
        default = ""
        constraints = listOf(Constraint.PACKAGE)
        suggest = { packageName }
    }

fun String.save(
    srcDir: PsiDirectory,
    subDirPath: String,
    fileName: String
) {
    try {
        val destDir = subDirPath.split(".").toDir(srcDir)
        val psiFile = PsiFileFactory
            .getInstance(srcDir.project)
            .createFileFromText(fileName, KotlinLanguage.INSTANCE, this)
        destDir.add(psiFile)
    } catch (exc: Exception) {
        exc.printStackTrace()
    }
}

fun List<String>.toDir(srcDir: PsiDirectory): PsiDirectory {
    var result = srcDir
    forEach {
        result = result.findSubdirectory(it) ?: result.createSubdirectory(it)
    }
    return result
}

fun createLayoutName(className: String): String {
    val array = className.toCharArray()
    val string = StringBuilder()
    array.forEach {
        if (it.isUpperCase()) {
            if (string.isNotEmpty()) {
                string.append("_")
            }
            string.append(it.toLowerCase())
        } else {
            string.append(it)
        }
    }
    return string.toString()
}
