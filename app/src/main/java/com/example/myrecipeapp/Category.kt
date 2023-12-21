package com.example.myrecipeapp

import java.lang.invoke.TypeDescriptor

data class Category(val idCategories: String,
                    val strCategory : String ,
                    val strCategoryThumb : String ,
                    val strCategoryDescriptor: String
    )

data class CategoriesResponse(val categories : List<Category>)
