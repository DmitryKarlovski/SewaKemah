package com.app.sewakemah.data

import java.io.Serializable

data class RentedEntity (
    val rentedName: String,
    val rentedType: String,
    val rentedAddr: String,
    val rentedPrice: Int,
    val rentedDays: Int,
    val rentedImg: Int,
    val rentedDesc: String,
    val rentedTotal: Int
): Serializable
