package com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.recipes.clean

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.recipes.clean.cleanArchRecipe
import com.github.quanticheart.intellijplugincleantree.wizard.createLayoutName

val cleanArchTemplateV1
    get() = template {

        name = "Clean Arch"
        description = "Create clean feature"
        minApi = MIN_API
        minApi = MIN_API

        category = Category.Other
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

        val layoutName = stringParameter {
            name = "Layout Name"
            default = "activity_main"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { activityToLayout(createLayoutName(featureName.value)) }
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
            TextFieldWidget(layoutName),
            PackageNameWidget(packageName),
        )
//        thumb { File("logo.png") }

        recipe = { data: TemplateData ->
            cleanArchRecipe(
                data as ModuleTemplateData,
                featureName.value,
                layoutName.value,
                packageName.value
            )
        }
    }

