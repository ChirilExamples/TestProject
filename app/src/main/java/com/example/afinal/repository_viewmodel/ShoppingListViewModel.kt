package com.example.afinal.repository_viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(repository: ShoppingRepository) : ViewModel() {

    val repository = repository.getShoppings()

    //  val repositorySortPrice = repository.getShoppingPriceSort(price)
    val p10 = repository.getShoppingPriceSort(10.0)
    val p25 = repository.getShoppingPriceSort(25.0)
    val p50 = repository.getShoppingPriceSort(50.0)
    val p100 = repository.getShoppingPriceSort(100.0)

    //  val repositorySortCat = repository.getShoppingCategorySort(cat)
    val jewelery = repository.getShoppingCategorySort("jewelery")
    val men = repository.getShoppingCategorySort("men's clothing")
    val women = repository.getShoppingCategorySort("women's clothing")
    val electronics = repository.getShoppingCategorySort("electronics")
}
