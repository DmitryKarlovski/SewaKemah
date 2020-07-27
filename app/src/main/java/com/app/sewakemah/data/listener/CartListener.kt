package com.app.sewakemah.data.listener

import com.app.sewakemah.data.CartEntity

interface CartListener {
    fun onClick(cart: CartEntity)
}
