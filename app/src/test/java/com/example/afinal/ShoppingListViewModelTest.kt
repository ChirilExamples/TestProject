package com.example.afinal

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.afinal.data.data_structure.ClothesItem
import com.example.afinal.repository_viewmodel.ShoppingListViewModel
import com.example.afinal.repository_viewmodel.ShoppingRepository
import com.example.afinal.utils.Resource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ShoppingListViewModelTest {
    @get:Rule
    val instantTaskExecutionRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ShoppingListViewModel
    private val shoppingList = listOf(
        ClothesItem(1, "unknown", "Thing", "", 15.0, "Human"),
        ClothesItem(2, "male", "some", "", 10.0, "Alien")
    )

    private val repository: ShoppingRepository = mockk(relaxed = true) {
        every { getShoppings() } returns MutableLiveData(Resource.success(shoppingList))
    }

    private val shoppingObserver: Observer<Resource<List<ClothesItem>>> = mockk(relaxed = true)

    @Before
    fun setUp() {
        viewModel = ShoppingListViewModel(repository)
        viewModel.repository.observeForever(shoppingObserver)
    }

    @Test
    fun `get shoppings should return emit list of things`() {
        verify { shoppingObserver.onChanged(Resource.success(shoppingList)) }

        assert(viewModel.repository.value == Resource.success(shoppingList))
    }
}
