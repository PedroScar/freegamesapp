package com.example.freegamesapp.model.response

import com.google.gson.annotations.SerializedName

data class GameDetailsRaw(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("thumbnail") var thumbnail: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("short_description") var shortDescription: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("game_url") var gameUrl: String? = null,
    @SerializedName("genre") var genre: String? = null,
    @SerializedName("platform") var platform: String? = null,
    @SerializedName("publisher") var publisher: String? = null,
    @SerializedName("developer") var developer: String? = null,
    @SerializedName("release_date") var releaseDate: String? = null,
    @SerializedName("freetogame_profile_url") var freetogameProfileUrl: String? = null,
    @SerializedName("minimum_system_requirements") var minimumSystemRequirementsRaw: MinimumSystemRequirementsRaw? = MinimumSystemRequirementsRaw(),
    @SerializedName("screenshots") var screenshots: ArrayList<Screenshots>? = arrayListOf()
)
