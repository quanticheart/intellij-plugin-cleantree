package com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.recipes.data

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API

val dataTemplateV1
    get() = template {

        name = "Data Layer"
        description = "Simple Data Layer"
        minApi = MIN_API
        minApi = MIN_API

        category = Category.Activity
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.ActivityGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        val featureName = stringParameter {
            name = "Feature name"
            default = "Main"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val packageName = stringParameter {
            name = "Package name"
            visible = { !isNewModule }
            default = "com.quanticheart.feature"
            constraints = listOf(Constraint.PACKAGE)
            suggest = { packageName }
        }

        widgets(
            TextFieldWidget(featureName),
            PackageNameWidget(packageName),
        )
//        thumb { File("logo.png") }

        recipe = { data: TemplateData ->
            dataRecipe(
                data as ModuleTemplateData,
                featureName.value,
                packageName.value
            )
        }
    }

