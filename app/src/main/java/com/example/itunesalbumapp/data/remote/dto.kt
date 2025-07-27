package com.example.itunesalbumapp.data.remote

import com.squareup.moshi.Json

/**
 * Represents the DTO (Data Transfer Object) for an album feed.
 *
 * @property feed The [FeedDto] containing album information.
 */
data class AlbumFeedDto(
    @Json(name = "feed")
    val feed: FeedDto
)

/**
 * Represents the DTO (Data Transfer Object) for a feed.
 *
 * @property author The [AuthorDto] representing the feed's author.
 * @property entries The list of [EntryDto] representing the entries in the feed.
 */
data class FeedDto(
    @Json(name = "author")
    val author: AuthorDto,

    @Json(name = "entry")
    val entries: List<EntryDto>
)

/**
 * Represents the DTO (Data Transfer Object) for an author.
 *
 * @property name The [LabelDto] representing the author's name.
 * @property uri The [LabelDto] representing the author's URI.
 */
data class AuthorDto(
    @Json(name = "name")
    val name: LabelDto,

    @Json(name = "uri")
    val uri: LabelDto
)

/**
 * Represents the DTO (Data Transfer Object) for a label.
 *
 * @property label The label value.
 */
data class LabelDto(
    @Json(name = "label") val label: String
)

/**
 * Represents the DTO (Data Transfer Object) for an image.
 *
 * @property url The URL of the image.
 * @property attributes The [ImageAttributesDto] representing the image's attributes.
 */
data class ImageDto(
    @Json(name = "label") val url: String,
    @Json(name = "attributes") val attributes: ImageAttributesDto?
)

/**
 * Represents the DTO (Data Transfer Object) for image attributes.
 *
 * @property height The height of the image.
 */
data class ImageAttributesDto(
    @Json(name = "height") val height: String
)

/**
 * Represents the DTO (Data Transfer Object) for a price.
 *
 * @property label The label value.
 * @property attributes The [PriceAttributesDto] representing the price's attributes.
 */
data class PriceDto(
    @Json(name = "label") val label: String,
    @Json(name = "attributes") val attributes: PriceAttributesDto
)

/**
 * Represents the DTO (Data Transfer Object) for price attributes.
 *
 * @property amount The amount of the price.
 * @property currency The currency of the price.
 */
data class PriceAttributesDto(
    @Json(name = "amount") val amount: String,
    @Json(name = "currency") val currency: String
)

/**
 * Represents the DTO (Data Transfer Object) for a content type.
 *
 * @property contentType The [TypeLabelDto] representing the content type.
 * @property attributes The [LabelTermDto] representing the content type's attributes.
 */
data class ContentTypeDto(
    @Json(name = "im:contentType") val contentType: TypeLabelDto,
    @Json(name = "attributes") val attributes: LabelTermDto
)

/**
 * Represents the DTO (Data Transfer Object) for a type label.
 *
 * @property attributes The [LabelTermDto] representing the type label's attributes.
 */
data class TypeLabelDto(
    @Json(name = "attributes") val attributes: LabelTermDto
)

/**
 * Represents the DTO (Data Transfer Object) for a label term.
 *
 * @property term The term value.
 * @property label The label value.
 */
data class LabelTermDto(
    @Json(name = "term") val term: String,
    @Json(name = "label") val label: String
)

/**
 * Represents the DTO (Data Transfer Object) for a link.
 *
 * @property attributes The [LinkAttributesDto] representing the link's attributes.
 */
data class LinkDto(
    @Json(name = "attributes") val attributes: LinkAttributesDto
)

/**
 * Represents the DTO (Data Transfer Object) for link attributes.
 *
 * @property rel The relationship of the link.
 * @property type The type of the link.
 * @property href The URL of the link.
 */
data class LinkAttributesDto(
    @Json(name = "rel") val rel: String,
    @Json(name = "type") val type: String,
    @Json(name = "href") val href: String
)

/**
 * Represents the DTO (Data Transfer Object) for an ID.
 *
 * @property label The label value.
 * @property attributes The [IdAttributesDto] representing the ID's attributes.
 */
data class IdDto(
    @Json(name = "label") val label: String,
    @Json(name = "attributes") val attributes: IdAttributesDto
)

/**
 * Represents the DTO (Data Transfer Object) for ID attributes.
 *
 * @property id The ID value.
 */
data class IdAttributesDto(
    @Json(name = "im:id") val id: String
)

/**
 * Represents the DTO (Data Transfer Object) for an artist.
 *
 * @property label The label value.
 * @property attributes The [ArtistAttributesDto] representing the artist's attributes.
 */
data class ArtistDto(
    @Json(name = "label") val label: String,
    @Json(name = "attributes") val attributes: ArtistAttributesDto?
)

/**
 * Represents the DTO (Data Transfer Object) for artist attributes.
 *
 * @property href The URL of the artist.
 */
data class ArtistAttributesDto(
    @Json(name = "href") val href: String
)

/**
 * Represents the DTO (Data Transfer Object) for a category.
 *
 * @property attributes The [CategoryAttributesDto] representing the category's attributes.
 */
data class CategoryDto(
    @Json(name = "attributes") val attributes: CategoryAttributesDto
)

/**
 * Represents the DTO (Data Transfer Object) for category attributes.
 *
 * @property id The ID of the category.
 * @property term The term value.
 * @property scheme The scheme value.
 * @property label The label value.
 */
data class CategoryAttributesDto(
    @Json(name = "im:id") val id: String,
    @Json(name = "term") val term: String,
    @Json(name = "scheme") val scheme: String,
    @Json(name = "label") val label: String
)

/**
 * Represents the DTO (Data Transfer Object) for a release date.
 *
 * @property date The date value.
 * @property attributes The [LabelDto] representing the release date's attributes.
 */
data class ReleaseDateDto(
    @Json(name = "label") val date: String,
    @Json(name = "attributes") val attributes: LabelDto
)

/**
 * Represents the DTO (Data Transfer Object) for an entry.
 *
 * @property name The [LabelDto] representing the entry's name.
 * @property images The list of [ImageDto] representing the entry's images.
 * @property itemCount The [LabelDto] representing the entry's item count.
 * @property price The [PriceDto] representing the entry's price.
 * @property contentType The [ContentTypeDto] representing the entry's content type.
 * @property rights The [LabelDto] representing the entry's rights.
 * @property title The [LabelDto] representing the entry's title.
 * @property link The [LinkDto] representing the entry's link.
 * @property id The [IdDto] representing the entry's ID.
 * @property artist The [ArtistDto] representing the entry's artist.
 * @property category The [CategoryDto] representing the entry's category.
 * @property releaseDate The [ReleaseDateDto] representing the entry's release date.
 */
data class EntryDto(
    @Json(name = "im:name") val name: LabelDto,
    @Json(name = "im:image") val images: List<ImageDto>,
    @Json(name = "im:itemCount") val itemCount: LabelDto?,
    @Json(name = "im:price") val price: PriceDto?,
    @Json(name = "im:contentType") val contentType: ContentTypeDto?,
    @Json(name = "rights") val rights: LabelDto?,
    @Json(name = "title") val title: LabelDto,
    @Json(name = "link") val link: LinkDto,
    @Json(name = "id") val id: IdDto,
    @Json(name = "im:artist") val artist: ArtistDto,
    @Json(name = "category") val category: CategoryDto,
    @Json(name = "im:releaseDate") val releaseDate: ReleaseDateDto
)
