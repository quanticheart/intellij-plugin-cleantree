package com.github.quanticheart.intellijplugincleantree.actions.google

import com.github.quanticheart.intellijplugincleantree.utils.getFileType
import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.editor.CaretModel
import com.intellij.openapi.editor.Editor

class OpenAskGoogleAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {

        val langTag = e.getFileType()

        val editor: Editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val caretModel: CaretModel = editor.caretModel
        val selectedText = caretModel.currentCaret.selectedText

        BrowserUtil.browse("https://www.google.com/search?q=$selectedText $langTag")
    }

    override fun update(e: AnActionEvent) {
        val editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val caretModel = editor.caretModel
        e.presentation.isEnabledAndVisible = caretModel.currentCaret.hasSelection()
    }
}