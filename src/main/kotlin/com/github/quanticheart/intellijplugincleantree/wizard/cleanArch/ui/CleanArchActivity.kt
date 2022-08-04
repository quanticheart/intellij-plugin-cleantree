package com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.ui

fun cleanArchActivity(
    applicationPackage: String?,
    featurePackage: String,
    activityName: String,
    viewBindingName: String,
    viewModelName: String,
) = """
package $featurePackage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ${applicationPackage}.databinding.${viewBindingName}

class $activityName : AppCompatActivity() {
    private val binding by lazy {
        ${viewBindingName}.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[${viewModelName}::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpBinding()
        setUpViewModel()
    }

    private fun setUpBinding() = binding.run {
        // setup clicks, watchers and others here
    }

    private fun setUpViewModel() = viewModel.run {
        status.observe(this@$activityName) { status ->
            binding.statusLabel.text = "Status Is " + status
        }
        getStatus(1)
    }
}
"""
