package com.alokomkar.wikisearch.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
        private val cryptocurrenciesViewModel: SearchViewModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return cryptocurrenciesViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}