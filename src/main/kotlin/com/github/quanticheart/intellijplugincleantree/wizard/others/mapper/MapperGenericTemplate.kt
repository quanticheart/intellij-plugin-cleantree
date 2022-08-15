package com.github.quanticheart.intellijplugincleantree.wizard.others.mapper

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API

val mapperGenericTemplateV1
    get() = template {

        name = "Class Mapper Generic"
        description = "Simple Mapper Generic Template"
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
            mapperGenericRecipe(
                data as ModuleTemplateData,
                packageName.value
            )
        }
    }

