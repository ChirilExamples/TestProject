package com.example.afinal.repository_viewmodel

import com.example.afinal.data.local.ShoppingDAO
import com.example.afinal.data.remote.ShoppingRemoteDatasource
import com.example.afinal.utils.performGetOperation
import javax.inject.Inject

class ShoppingRepository @Inject constructor(
    private val remoteDatasource: ShoppingRemoteDatasource,
    private val localDatasource: ShoppingDAO
) {
    fun getShoppings() = performGetOperation(
        databaseQuery = { localDatasource.getAllShoppings() },
        networkCall = { remoteDatasource.getAllShoppings() },
        saveCallResult = { localDatasource.insertAll(it) }
    )

    fun getShoppingDetailsData(id: Int) = performGetOperation(
        databaseQuery = { localDatasource.getShopping(id) },
        networkCall = { remoteDatasource.getShoppingsDetails(id) },
        saveCallResult = { localDatasource.insertDetails(it) }
    )

    fun getShoppingCategorySort(category: String) = performGetOperation(
        databaseQuery = { localDatasource.getSortCategory(category) },
        networkCall = { remoteDatasource.getCategorySort(category) },
        saveCallResult = { localDatasource.insertAll(it) } // TODO: might have to change or add in dao
    )

    fun getShoppingPriceSort(price: Double) = performGetOperation(
        databaseQuery = { localDatasource.getSortPrice(price) },
        networkCall = { remoteDatasource.getPriceSort(price) },
        saveCallResult = { localDatasource.insertAll(it) } // TODO: might have to change or add in dao
    )
}
