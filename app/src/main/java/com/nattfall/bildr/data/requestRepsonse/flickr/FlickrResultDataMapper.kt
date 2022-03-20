package com.nattfall.bildr.data.requestRepsonse.flickr

fun PhotoData.toDomainModel(): PhotoDomainData {
    return PhotoDomainData(
        photo = this,
        thumbnailUrl = this.toImageUrl(FlickrImageSuffix.SMALL),
        fullImageUrl = this.toImageUrl(FlickrImageSuffix.LARGE)
    )
}

private fun PhotoData.toImageUrl(
    suffix: FlickrImageSuffix,
    staticImageDomain: String = "https://live.staticflickr.com",
): String = "$staticImageDomain/${server}/${id}_${secret}_${suffix.value}.jpg"