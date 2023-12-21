package com.example.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _categoriesState = mutableStateOf(RecipeState())

    val categoryState : State<RecipeState> = _categoriesState

    init {
        fetchCategories()
    }

        //view Model take care of data and communicate with the ui
    private fun fetchCategories(){
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                _categoriesState.value = _categoriesState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )

            }catch (e: Error){
                _categoriesState.value.copy(
                    loading = false,
                    error = "Error Fetching categories ${e.message}"
                )

            }
        }
    }

    data class RecipeState(
        val loading : Boolean = true,
        val list : List<Category> = emptyList(),
        val error : String? = null
    )

}

//link which I Used
//https://www.themealdb.com/api/json/v1/1/categories.php