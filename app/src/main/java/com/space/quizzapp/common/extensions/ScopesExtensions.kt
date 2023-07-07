package com.space.quizzapp.common.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


fun ViewModel.viewModelScope(
    coroutineContext: CoroutineContext = Dispatchers.IO,
    block: suspend CoroutineScope.() -> Unit
) {
    viewModelScope.launch(coroutineContext) {
        block()
    }
}

fun <T : Any?> Fragment.collectAsync(
    flow: Flow<T>,
    lifecycleState: Lifecycle.State = Lifecycle.State.RESUMED,
    block: (T) -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        flow.flowWithLifecycle(this@collectAsync.lifecycle, lifecycleState).collect {
            block(it)
        }
    }
}

fun Fragment.lifecycleScope(
    coroutineContext: CoroutineContext = Dispatchers.Main,
    lifecycleState: Lifecycle.State = Lifecycle.State.RESUMED,
    block: suspend CoroutineScope.() -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch(coroutineContext) {
        repeatOnLifecycle(lifecycleState) {
            this.block()
        }
    }
}
