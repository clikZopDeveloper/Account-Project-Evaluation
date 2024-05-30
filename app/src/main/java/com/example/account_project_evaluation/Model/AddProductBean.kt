package com.example.kktext_testing.Model


data class AddProductBean(

    var catName: String,
    val subCatName: String,
    val description: String,
    val qty: String,
    val price: String,
    val gst: String,
    val comissionPerQty: String
    ) {

}