package com.example.freegamesapp.model.response

import com.google.gson.annotations.SerializedName

data class MinimumSystemRequirementsRaw(
    @SerializedName("os") var os: String? = null,
    @SerializedName("processor") var processor: String? = null,
    @SerializedName("memory") var memory: String? = null,
    @SerializedName("graphics") var graphics: String? = null,
    @SerializedName("storage") var storage: String? = null
)