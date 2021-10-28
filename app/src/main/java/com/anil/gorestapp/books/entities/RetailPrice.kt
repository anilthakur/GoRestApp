package com.anil.gorestapp.books.entities

import com.google.gson.annotations.SerializedName

data class RetailPrice(

	@field:SerializedName("amount")
	val amount: Double? = null,

	@field:SerializedName("currencyCode")
	val currencyCode: String? = null,

	@field:SerializedName("amountInMicros")
	val amountInMicros: Int? = null
)