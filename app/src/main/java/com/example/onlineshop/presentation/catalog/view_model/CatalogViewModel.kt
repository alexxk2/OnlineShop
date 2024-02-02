package com.example.onlineshop.presentation.catalog.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.domain.catalog.CatalogInteractor
import com.example.onlineshop.presentation.catalog.models.CatalogScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val catalogInteractor: CatalogInteractor
) : ViewModel() {

    private val _catalogScreenState = MutableLiveData<CatalogScreenState>()
    val catalogScreenState: LiveData<CatalogScreenState> = _catalogScreenState

    fun getAllProducts() {
        _catalogScreenState.postValue(CatalogScreenState.Loading)
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val response = catalogInteractor.getAllProducts()
                _catalogScreenState.postValue(
                    if (response.isEmpty()) {
                        CatalogScreenState.Error
                    } else {
                        CatalogScreenState.Content(response)
                    }
                )

            } catch (e: Exception) {
                _catalogScreenState.postValue(CatalogScreenState.Error)
            }
        }
    }


}