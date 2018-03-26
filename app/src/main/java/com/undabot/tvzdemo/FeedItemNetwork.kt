package com.undabot.tvzdemo

import com.google.gson.annotations.SerializedName

data class FeedItemNetwork(

        val color: String,
        val description: String?,
        val height: Number,
        val id: String,
        val likes: Int,
        val links: Links,
        val urls: Urls,
        val user: User,
        val width: Number
)

data class Links(
        val download: String,
        val download_location: String,
        val html: String,
        val self: String
)

data class Urls(
        val full: String,
        val raw: String,
        val regular: String,
        val small: String,
        val thumb: String
)

data class User(
        val bio: String,
        @SerializedName("first_name")
        val firstName: String,
        val id: String,
        @SerializedName("instagram_username")
        val instagramUsername: String,
        @SerializedName("last_name")
        val lastName: String,
        val location: String,
        val name: String,
        val portfolioUrl: String,
        @SerializedName("profile_image")
        val profileImage: ProfileImage,
        val twitter_username: String,
        val username: String
)

data class ProfileImage(
        val large: String,
        val medium: String,
        val small: String
)

