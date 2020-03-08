package com.anil.gorestapp.data.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Avatar(

	@SerializedName("href")
	@Expose
	val href: String? = null
)