package br.com.albertosql.programming_challenge.domain

import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("Source")
    val source: String?,
    @SerializedName("Value")
    val value: String?
)