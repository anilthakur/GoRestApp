package com.anil.gorestapp.books.entities

import com.google.gson.annotations.SerializedName

data class ImageLinks(

	@field:SerializedName("thumbnail")
	val thumbnail: String? = null,

	@field:SerializedName("smallThumbnail")
	val smallThumbnail: String? = null
)