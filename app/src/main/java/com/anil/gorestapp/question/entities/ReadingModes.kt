package com.anil.gorestapp.question.entities

import com.google.gson.annotations.SerializedName

data class ReadingModes(

	@field:SerializedName("image")
	val image: Boolean? = null,

	@field:SerializedName("text")
	val text: Boolean? = null
)