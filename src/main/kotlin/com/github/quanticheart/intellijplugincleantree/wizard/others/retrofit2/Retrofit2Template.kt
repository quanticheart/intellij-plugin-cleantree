package com.github.quanticheart.intellijplugincleantree.wizard.others.retrofit2

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API

val retrofit2TemplateV1
    get() = template {

        name = "Retrofit2"
        description = "Simple Retrofit2 Template"
        minApi = MIN_API

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.ActivityGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        val packageName = stringParameter {
            name = "Package name"
            visible = { !isNewModule }
            default = "com.quanticheart.feature"
            constraints = listOf(Constraint.PACKAGE)
            suggest = {
                packageName
            }
        }

        widgets(
            PackageNameWidget(packageName),
        )

        recipe = { data: TemplateData ->
            retrofit2Recipe(
                data as ModuleTemplateData,
                packageName.value
            )
        }
    }
