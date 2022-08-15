package com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.ui

fun cleanArchActivityForActivity(
    applicationPackage: String?,
    featurePackage: String,
    activityName: String,
    viewBindingName: String,
    fragmentName:String
) = """
package $featurePackage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ${applicationPackage}.R
import ${applicationPackage}.databinding.${viewBindingName}
import ${applicationPackage}.${fragmentName}

class $activityName : AppCompatActivity() {
    private val binding by lazy {
        ${viewBindingName}.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpBinding()
    }

    private fun setUpBinding() = binding.run {
         supportFragmentManager.beginTransaction()
                .replace(binding.container, ${fragmentName}.newInstance())
                .commitNow()
    }
}
"""
