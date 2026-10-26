package com.example.btvn_tuan41.thuvien.model

data class Book (
    val id: String,
    val title: String,
    val author: String,
    val category: BookCategory,
    val publishedYear: Int,
    var totalCopies: Int = 5,
    var borrowedCopies: Int = 0
) {
    val availableCopies: Int
        get() = totalCopies - borrowedCopies

    val isAvailable: Boolean
        get() = availableCopies > 0

    fun borrowOne(): Boolean {
        return if (isAvailable){
            borrowedCopies ++
            true
        } else{
            false
        }
    }


    fun returnOne(): Boolean {
        return if (borrowedCopies > 0){
            borrowedCopies --
            true
        } else{
            false
        }
    }



}
