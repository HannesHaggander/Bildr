package com.nattfall.bildr.data.requestRepsonse.flickr

fun PhotoData.toDomainModel(): PhotoDomainData {
    return PhotoDomainData(
        photo = this,
        previewImage = this.toImageUrl(FlickrImageSuffix.SMALL),
        fullImageUrl = this.toImageUrl(FlickrImageSuffix.LARGE)
    )
}

fun PhotoData.toImageUrl(
    suffix: FlickrImageSuffix,
    staticImageDomain: String = STATIC_IMAGE_DOMAIN
): String = "$staticImageDomain/${server}/${id}_${secret}_${suffix.value}.jpg"

fun String.splitUrlToDeepLink(): String {
    return substring(STATIC_IMAGE_DOMAIN.length).replace("/", "\\")
}

fun String.joinUrlFromDeepLink(): String {
    return STATIC_IMAGE_DOMAIN.plus(replace("\\", "/"))
}

private const val STATIC_IMAGE_DOMAIN = "https://live.staticflickr.com"