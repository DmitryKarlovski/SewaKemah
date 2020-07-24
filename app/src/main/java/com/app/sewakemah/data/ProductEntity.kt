package com.app.sewakemah.data

import java.io.Serializable

data class ProductEntity (
        val productName: String,
        val productType: String,
        val productStore: String,
        val productAddr: String,
        val productPrice: Int,
        val productDays: Int,
        val productImg: Int,
        val productDesc: String
):Serializable
