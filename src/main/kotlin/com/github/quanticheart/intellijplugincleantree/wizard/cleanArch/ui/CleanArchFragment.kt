package com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.ui

fun cleanArchFragment(
    applicationPackage: String?,
    featurePackage: String,
    fragmentName: String,
    fragmentViewBindingName: String,
    viewModelName: String,
) = """
package $featurePackage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ${applicationPackage}.databinding.${fragmentViewBindingName}

class $fragmentName : Fragment() {

    companion object {
        fun newInstance() = $fragmentName()
    }

   private val binding by lazy {
        ${fragmentViewBindingName}.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[${viewModelName}::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpBinding()
        setUpViewModel()
    }

    private fun setUpBinding() = binding.run {
        // setup clicks, watchers and others here
    }

    private fun setUpViewModel() = viewModel.run {
        status.observe(this@$fragmentName) { status ->
            binding.statusLabel.text = "Status Is " + status
        }
        getStatus(1)
    }
}
"""
