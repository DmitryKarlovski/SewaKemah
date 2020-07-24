package com.app.sewakemah.data.listener

import com.app.sewakemah.data.RentedEntity

interface RentedStuffListener {
    fun onClick(product: RentedEntity)
}