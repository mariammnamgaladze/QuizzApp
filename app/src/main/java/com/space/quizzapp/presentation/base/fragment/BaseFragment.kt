package com.space.quizzapp.presentation.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.space.quizzapp.common.extensions.lifecycleScope
import com.space.quizzapp.common.navigation.NavigationCommand
import com.space.quizzapp.presentation.base.viewmodel.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModelForClass
import kotlin.reflect.KClass


abstract class BaseFragment< ViewModel : BaseViewModel>() : Fragment() {

    protected  val viewModel: ViewModel by viewModelForClass(clazz = viewModelClass)

    abstract val viewModelClass: KClass<ViewModel>

    protected abstract val layout: Int

    abstract fun onBind()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBind()
        observeNavigation()
    }

    private fun observeNavigation() {
        lifecycleScope {
            viewModel.navigation.collect { navigationCommand ->
                handleNavigation(navigationCommand)
            }
        }
    }

    private fun handleNavigation(navCommand: NavigationCommand) {
        when (navCommand) {
            is NavigationCommand.ToDirection -> findNavController().navigate(navCommand.directions)
            is NavigationCommand.Back -> findNavController().navigateUp()
        }
    }

    fun navigateTo(destinationId: Int) {
        val navController = findNavController()
        navController.navigate(destinationId)
    }
}