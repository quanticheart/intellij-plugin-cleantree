package com.github.quanticheart.intellijplugincleantree.actions.stackOverflow

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.ui.Messages
import java.net.URLEncoder

class SearchOnStackOverflowAction(
    private val handler: ((String, String) -> Unit)? = null
) : AnAction() {
    override fun update(event: AnActionEvent) {
        with(event.getData(CommonDataKeys.EDITOR)) {
            val condition = this?.caretModel?.currentCaret?.hasSelection()
            event.presentation.isEnabledAndVisible = condition == true
        }
    }

    override fun actionPerformed(event: AnActionEvent) {
        val langTag: String = with(event.getData(CommonDataKeys.PSI_FILE)) {
            this?.run {
                "+[${language.displayName.toLowerCase()}]"
            }
        } ?: ""

        val selectedText: String =
            with(event.getData(CommonDataKeys.EDITOR)) {
                this?.caretModel?.currentCaret?.selectedText
            } ?: ""

        val myHandler = handler ?: { _, _ ->
            if (selectedText.isEmpty()) {
                Messages.showMessageDialog(
                    event.project,
                    "Please select something before running this action",
                    "Search on Stack Overflow",
                    Messages.getWarningIcon()
                )
            } else {
                val query = URLEncoder.encode(selectedText, "UTF-8") + langTag
                BrowserUtil.browse("https://stackoverflow.com/search?q=$query")
            }
        }
        myHandler.invoke(selectedText, langTag)
    }
}