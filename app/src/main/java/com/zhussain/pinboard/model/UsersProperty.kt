package com.zhussain.pinboard.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class UsersProperty(
    val id: String,
    @Json(name = "created_at") val createdAt: Date = Date(0),
    val likes: Int = 0,
    @Json(name = "user")val userInfo: User
) : Parcelable