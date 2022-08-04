package com.github.quanticheart.intellijplugincleantree.templates

import com.intellij.codeInsight.template.TemplateActionContext
import com.intellij.codeInsight.template.TemplateContextType

open class MarkdownContext protected constructor() : TemplateContextType("MARKDOWN", "Markdown") {
    override fun isInContext(templateActionContext: TemplateActionContext): Boolean {
        return templateActionContext.file.name.endsWith(".md")
    }
}