package com.nubank.urlshortener.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data class for Alias object
 *
 * Follows next structure of response
 * {
 *  "alias":"<url alias>",
 *  "_links": {
 *      "self":"<original url>",
 *      "short":"<short url>"
 *  }
 * }
 */

data class Alias(
    @SerializedName("alias") val alias: String?,
    @SerializedName("_links") val links: Links?) {
    data class Links(
        @SerializedName("self") val self: String?,
        @SerializedName("short") val short: String?
    )
}
