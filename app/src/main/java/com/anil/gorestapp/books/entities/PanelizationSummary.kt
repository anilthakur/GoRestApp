package com.anil.gorestapp.books.entities

import com.google.gson.annotations.SerializedName

data class PanelizationSummary(

	@field:SerializedName("containsImageBubbles")
	val containsImageBubbles: Boolean? = null,

	@field:SerializedName("containsEpubBubbles")
	val containsEpubBubbles: Boolean? = null
)