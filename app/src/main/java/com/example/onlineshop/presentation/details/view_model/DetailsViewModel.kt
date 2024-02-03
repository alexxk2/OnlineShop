package com.example.onlineshop.presentation.details.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.domain.details.DetailsInteractor
import com.example.onlineshop.domain.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsInteractor: DetailsInteractor
): ViewModel() {

    private lateinit var product: Product

    fun addToFavorite(){
        viewModelScope.launch(Dispatchers.IO) {
            detailsInteractor.addToFavorite(product)
            product = product.copy(isFavorite = true)
        }
    }

    fun removeFromFavorite(){
        viewModelScope.launch(Dispatchers.IO) {
            detailsInteractor.removeFromFavorite(product)
            product = product.copy(isFavorite = false)
        }
    }

    fun getProduct(): Product = product

    fun setProduct(inputProduct: Product){
        product = inputProduct
    }
}