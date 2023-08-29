package com.abg.testapp.model

import kotlinx.serialization.Serializable

@Serializable
class Root(
    var success:Boolean = false,
    var data: DataCamera? = null
)