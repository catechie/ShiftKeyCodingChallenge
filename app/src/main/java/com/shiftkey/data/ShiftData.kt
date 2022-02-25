package com.shiftkey.data

import com.shiftkey.data.Data
import com.shiftkey.data.Meta

data class ShiftData(
    val `data`: List<Data>,
    val links: List<Any>,
    val meta: Meta
)