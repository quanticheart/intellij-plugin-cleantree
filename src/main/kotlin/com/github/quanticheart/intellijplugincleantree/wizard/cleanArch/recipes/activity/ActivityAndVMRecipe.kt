package com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.recipes.activity

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.res.layout.mvvmXml
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.ui.cleanArchActivity
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.ui.cleanArchViewModelSimple
import java.util.*

fun RecipeExecutor.activityAndVMRecipe(
    moduleData: ModuleTemplateData,
    featureName: String,
    layoutName: String,
    packageName: String
) {

    val (projectData, srcOut, resOut) = moduleData

    /**
     * Packages
     */
    val applicationPackage = projectData.applicationPackage
    val featurePackage = "${packageName}.${featureName.toLowerCase()}"

    //ui
    val activityName = "${featureName}Activity"
    val activityViewBindingName = "Activity${featureName}Binding"
    val viewModelName = "${featureName}ViewModel"

    /**
     * Paths
     */
    val basePath = featureName.toLowerCase(Locale.getDefault())

    /**
     * Create Files
     *
     * Layout
     */
    val xml = mvvmXml()
    save(xml, resOut.resolve("layout/$layoutName.xml"))

    /**
     * UI
     */
    val activity = cleanArchActivity(
        applicationPackage = applicationPackage,
        featurePackage = featurePackage,
        activityName = activityName,
        viewBindingName = activityViewBindingName,
        viewModelName = viewModelName
    )
    save(activity, srcOut.resolve("$basePath/${activityName}.kt"))

    val viewModel = cleanArchViewModelSimple(
        featurePackage = featurePackage,
        viewModelName = viewModelName
    )
    save(viewModel, srcOut.resolve("$basePath/${viewModelName}.kt"))

}
