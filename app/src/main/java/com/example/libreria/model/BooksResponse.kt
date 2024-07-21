package com.example.libreria.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json = Json { allowStructuredMapKeys = true }
// val book = json.parse(Book.serializer(), jsonString)


@Serializable
data class BooksResponse (
    val kind: String? = null,
    val totalItems: Long? = null,
    val items: List<Item>
)

@Serializable
data class Item (
    val kind: String? = null,
    val id: String? = null,
    val etag: String? = null,
    val selfLink: String? = null,
    val volumeInfo: VolumeInfo? = null,
    val saleInfo: SaleInfo? = null,
    val accessInfo: AccessInfo? = null
)

@Serializable
data class AccessInfo (
    val country: String? = null,
    val viewability: String? = null,
    val embeddable: Boolean? = null,
    val publicDomain: Boolean? = null,
    val textToSpeechPermission: String? = null,
    val epub: Epub? = null,
    val pdf: PDF? = null,
    val webReaderLink: String? = null,
    val accessViewStatus: String? = null,
    val quoteSharingAllowed: Boolean? = null
)

@Serializable
data class Epub (
    val isAvailable: Boolean? = null,
    val downloadLink: String? = null
)

@Serializable
data class PDF (
    val isAvailable: Boolean? = null
)

@Serializable
data class SaleInfo (
    val country: String? = null,
    val saleability: String? = null,
    val isEbook: Boolean? = null,
    val buyLink: String? = null
)

@Serializable
data class VolumeInfo (
    val title: String? = null,
    val authors: List<String>? = null,
    val publishedDate: String? = null,
    val description: String? = null,
    val industryIdentifiers: List<IndustryIdentifier>? = null,
    val readingModes: ReadingModes? = null,
    val pageCount: Long? = null,
    val printType: String? = null,
    val maturityRating: String? = null,
    val allowAnonLogging: Boolean? = null,
    val contentVersion: String? = null,
    val panelizationSummary: PanelizationSummary? = null,
    val imageLinks: ImageLinks? = null,
    val language: String? = null,
    val previewLink: String? = null,
    val infoLink: String? = null,
    val canonicalVolumeLink: String? = null
)

@Serializable
data class ImageLinks (
    val smallThumbnail: String? = null,
    val thumbnail: String? = null
)

@Serializable
data class IndustryIdentifier (
    val type: String? = null,
    val identifier: String? = null
)

@Serializable
data class PanelizationSummary (
    val containsEpubBubbles: Boolean? = null,
    val containsImageBubbles: Boolean? = null
)

@Serializable
data class ReadingModes (
    val text: Boolean? = null,
    val image: Boolean? = null
)