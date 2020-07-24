package com.app.sewakemah.data.listener

import com.app.sewakemah.data.ProductEntity

interface ProductListener {
    fun onClick(product: ProductEntity)
}
