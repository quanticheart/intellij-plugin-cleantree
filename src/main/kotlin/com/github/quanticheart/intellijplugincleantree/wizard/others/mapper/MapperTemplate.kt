package com.github.quanticheart.intellijplugincleantree.wizard.others.mapper

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API

val mapperTemplateV1
    get() = template {

        name = "Class Mapper"
        description = "Simple Mapper Template"
        minApi = MIN_API

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.ActivityGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        val class1 = stringParameter {
            name = "Class input name"
            default = "MainEntity"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val class2 = stringParameter {
            name = "Class output name"
            default = "MainModel"
            constraints = listOf(Constraint.NONEMPTY)
        }

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
            TextFieldWidget(class1),
            TextFieldWidget(class2),
            PackageNameWidget(packageName),
        )

        recipe = { data: TemplateData ->
            mapperRecipe(
                data as ModuleTemplateData,
                class1.value,
                class2.value,
                packageName.value
            )
        }
    }

