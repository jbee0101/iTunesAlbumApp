package com.example.itunesalbumapp.data.remote

import com.squareup.moshi.Json

data class AlbumFeedDto(
    @Json(name = "feed")
    val feed: FeedDto
)

data class FeedDto(
    @Json(name = "author")
    val author: AuthorDto,

    @Json(name = "entry")
    val entries: List<EntryDto>
)

data class AuthorDto(
    @Json(name = "name")
    val name: LabelDto,

    @Json(name = "uri")
    val uri: LabelDto
)
data class LabelDto(
    @Json(name = "label") val label: String
)

data class ImageDto(
    @Json(name = "label") val url: String,
    @Json(name = "attributes") val attributes: ImageAttributesDto?
)

data class ImageAttributesDto(
    @Json(name = "height") val height: String
)

data class PriceDto(
    @Json(name = "label") val label: String,
    @Json(name = "attributes") val attributes: PriceAttributesDto
)

data class PriceAttributesDto(
    @Json(name = "amount") val amount: String,
    @Json(name = "currency") val currency: String
)

data class ContentTypeDto(
    @Json(name = "im:contentType") val contentType: TypeLabelDto,
    @Json(name = "attributes") val attributes: LabelTermDto
)

data class TypeLabelDto(
    @Json(name = "attributes") val attributes: LabelTermDto
)

data class LabelTermDto(
    @Json(name = "term") val term: String,
    @Json(name = "label") val label: String
)

data class LinkDto(
    @Json(name = "attributes") val attributes: LinkAttributesDto
)

data class LinkAttributesDto(
    @Json(name = "rel") val rel: String,
    @Json(name = "type") val type: String,
    @Json(name = "href") val href: String
)

data class IdDto(
    @Json(name = "label") val label: String,
    @Json(name = "attributes") val attributes: IdAttributesDto
)

data class IdAttributesDto(
    @Json(name = "im:id") val id: String
)

data class ArtistDto(
    @Json(name = "label") val label: String,
    @Json(name = "attributes") val attributes: ArtistAttributesDto?
)

data class ArtistAttributesDto(
    @Json(name = "href") val href: String
)

data class CategoryDto(
    @Json(name = "attributes") val attributes: CategoryAttributesDto
)

data class CategoryAttributesDto(
    @Json(name = "im:id") val id: String,
    @Json(name = "term") val term: String,
    @Json(name = "scheme") val scheme: String,
    @Json(name = "label") val label: String
)

data class ReleaseDateDto(
    @Json(name = "label") val date: String,
    @Json(name = "attributes") val attributes: LabelDto
)

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
