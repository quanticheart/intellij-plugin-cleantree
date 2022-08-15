package com.github.quanticheart.intellijplugincleantree.wizard

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.recipes.activity.activityTemplateV1
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.recipes.clean.cleanArchTemplateV1
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.recipes.data.dataTemplateV1
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.recipes.domain.domainTemplateV1
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.recipes.fragment.activityForFragmentTemplateV1
import com.github.quanticheart.intellijplugincleantree.wizard.others.mapper.mapperGenericTemplateV1
import com.github.quanticheart.intellijplugincleantree.wizard.others.mapper.mapperTemplateV1
import com.github.quanticheart.intellijplugincleantree.wizard.others.retrofit2.retrofit2TemplateV1

class WizardTemplateProviderImpl : WizardTemplateProvider() {
    override fun getTemplates(): List<Template> = listOf(
        cleanArchTemplateV1,
        activityTemplateV1,
        activityForFragmentTemplateV1,
        dataTemplateV1,
        domainTemplateV1,
        retrofit2TemplateV1,
        mapperTemplateV1,
        mapperGenericTemplateV1
    )
}
