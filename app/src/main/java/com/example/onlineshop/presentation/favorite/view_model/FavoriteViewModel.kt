package com.example.onlineshop.presentation.favorite.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.domain.favorite.FavoriteInteractor
import com.example.onlineshop.domain.models.Product
import com.example.onlineshop.presentation.favorite.models.FavoriteScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteInteractor: FavoriteInteractor
) : ViewModel() {

    private val _favoriteScreenState = MutableLiveData<FavoriteScreenState>()
    val favoriteScreenState: LiveData<FavoriteScreenState> = _favoriteScreenState

    fun removeFromFavorite(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteInteractor.removeFromFavorite(product)
            getAllFavorites()
        }
    }

    fun getAllFavorites() {
        _favoriteScreenState.postValue(FavoriteScreenState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = favoriteInteractor.getAllFavorite()
                _favoriteScreenState.postValue(
                    if (result.isEmpty()) {
                        FavoriteScreenState.Empty
                    } else {
                        FavoriteScreenState.Content(result)
                    }
                )

            } catch (e: Exception) {
                _favoriteScreenState.postValue(FavoriteScreenState.Error)
            }

        }
    }
}