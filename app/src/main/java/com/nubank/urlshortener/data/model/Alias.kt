package com.nubank.urlshortener.data.model

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

data class Alias(val alias: String, val _links: Links) {
    data class Links(
        val self: String,
        val short: String
    )
}
