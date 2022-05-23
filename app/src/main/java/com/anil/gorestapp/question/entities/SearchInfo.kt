package com.anil.gorestapp.question.entities

import com.google.gson.annotations.SerializedName

data class SearchInfo(

	@field:SerializedName("textSnippet")
	val textSnippet: String? = null
)