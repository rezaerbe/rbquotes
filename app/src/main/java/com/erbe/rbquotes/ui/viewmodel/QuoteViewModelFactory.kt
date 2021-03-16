@file:Suppress("UNCHECKED_CAST")

package com.erbe.rbquotes.ui.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.erbe.rbquotes.data.QuotesRepository
import kotlinx.coroutines.CoroutineDispatcher

@SuppressLint("UNCHECKED_CAST")
class QuoteViewModelFactory(
    private val repository: QuotesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(repository) as T
    }
}