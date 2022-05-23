package com.anil.gorestapp.question.entities

import com.google.gson.annotations.SerializedName

data class IndustryIdentifiersItem(

	@field:SerializedName("identifier")
	val identifier: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)