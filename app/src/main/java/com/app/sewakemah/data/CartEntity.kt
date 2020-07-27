package com.app.sewakemah.data

import java.io.Serializable

data class CartEntity (
    val cartName: String,
    val cartDays: String,
    val cartStart: String,
    val cartReturn: String,
    val cartSummary: String,
    val cartTotal: Int,
    val productImg: Int
): Serializable