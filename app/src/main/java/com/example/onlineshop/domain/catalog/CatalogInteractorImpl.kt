package com.example.onlineshop.domain.catalog

import com.example.onlineshop.domain.models.Product
import com.example.onlineshop.domain.models.SortVariants
import com.example.onlineshop.domain.repositories.CatalogRepository
import com.example.onlineshop.domain.repositories.FavoriteRepository
import javax.inject.Inject

class CatalogInteractorImpl @Inject constructor(
    private val catalogRepository: CatalogRepository,
    private val favoriteRepository: FavoriteRepository
) : CatalogInteractor {

    override suspend fun getAllProducts(): List<Product> {
        return catalogRepository.getAllProducts()
    }

    override suspend fun sortProducts(
        list: List<Product>,
        sortVariant: SortVariants
    ): List<Product> {
        when (sortVariant) {
            SortVariants.Popular -> {
                return list.sortedByDescending {
                    it.feedback?.rating
                }
            }

            SortVariants.LowToHigh -> {
                return list.sortedBy {
                    it.price.priceWithDiscount.toIntOrNull() ?: 0
                }
            }

            SortVariants.HighToLow -> {
                return list.sortedByDescending {
                    it.price.priceWithDiscount.toIntOrNull() ?: 0
                }
            }
        }
    }

    override suspend fun filterProducts(
        list: List<Product>,
        filterWord: String
    ): List<Product> {
        return list.filter { product ->
            product.tags.contains(filterWord)
        }
    }

    override suspend fun addToFavorite(product: Product) {
        favoriteRepository.addToFavorite(product)
    }

    override suspend fun removeFromFavorite(product: Product) {
        favoriteRepository.removeFromFavorite(product)
    }
}