package com.example.onlineshop.presentation.catalog.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.domain.catalog.CatalogInteractor
import com.example.onlineshop.domain.models.Product
import com.example.onlineshop.domain.models.SortVariants
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

    private lateinit var defaultCatalogList: List<Product>
    private lateinit var currentCatalogList: List<Product>
    private var currentSorting = SortVariants.Popular

    fun getAllProducts() {
        _catalogScreenState.postValue(CatalogScreenState.Loading)
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val response = catalogInteractor.getAllProducts()

                if (response.isEmpty()) {
                    _catalogScreenState.postValue(CatalogScreenState.Error)
                } else {
                    defaultCatalogList = response
                    currentCatalogList = response
                    sortProducts(SortVariants.Popular)
                }

            } catch (e: Exception) {
                _catalogScreenState.postValue(CatalogScreenState.Error)
            }
        }
    }

    fun sortProducts(sortVariant: SortVariants){
        currentSorting = sortVariant
        viewModelScope.launch(Dispatchers.IO) {
            val sortingResult = catalogInteractor.sortProducts(currentCatalogList,sortVariant)
            currentCatalogList = sortingResult
            _catalogScreenState.postValue(CatalogScreenState.Content(sortingResult))
        }

    }

    fun filterProducts(filterWord: String){
        viewModelScope.launch(Dispatchers.IO) {
            val filteringResult = catalogInteractor.filterProducts(defaultCatalogList,filterWord)
            currentCatalogList = filteringResult
            _catalogScreenState.postValue(
                if (filteringResult.isEmpty()){
                    CatalogScreenState.Empty
                } else{
                    CatalogScreenState.Content(filteringResult)
                })

        }
    }

    fun makeFilterDefault(){
        currentCatalogList = defaultCatalogList
        sortProducts(currentSorting)
    }

    fun addToFavorite(product: Product){
        viewModelScope.launch(Dispatchers.IO) {
            catalogInteractor.addToFavorite(product)
            updateLocalLists(product,true)
        }
    }

    fun removeFromFavorite(product: Product){
        viewModelScope.launch(Dispatchers.IO) {
          catalogInteractor.removeFromFavorite(product)
            updateLocalLists(product,false)
        }
    }

     fun updateLocalLists(product: Product, isFavorite: Boolean){
        defaultCatalogList = defaultCatalogList.map {

            if (it.id == product.id) {
                it.copy(isFavorite = isFavorite)
            }
            else it
        }

        currentCatalogList = currentCatalogList.map {
            if (it.id == product.id) {
                it.copy(isFavorite = isFavorite)
            }
            else it
        }
        _catalogScreenState.postValue(CatalogScreenState.Content(currentCatalogList))
    }

}