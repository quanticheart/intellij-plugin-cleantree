package com.github.quanticheart.intellijplugincleantree.wizard

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.cleanArchTemplateV1

class WizardTemplateProviderImpl : WizardTemplateProvider() {
    override fun getTemplates(): List<Template> = listOf(
        cleanArchTemplateV1
    )
}
