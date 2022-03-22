package com.nattfall.bildr

import com.nattfall.bildr.data.requestRepsonse.flickr.*
import io.mockk.every
import io.mockk.mockkClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FlickrResultDataMapperTest {

    // note, only testing happy paths here, but given more time I would create
    // tests for edge cases and fault tolerant behaviour

    @Test
    fun givenValidString_whenConvertingToImageUrl_thenExpectStaticFlickrUrl() {
        val expected = "https://live.staticflickr.com/7372/12502775644_acfd415fa7_b.jpg"

        // Note: Mockk library seems to mess up so I had to do this mock manually.
        val mockPhoto: PhotoData = mockkClass(PhotoData::class) {
            every { id } returns "12502775644"
            every { secret } returns "acfd415fa7"
            every { server } returns "7372"
        }

        val actual = mockPhoto.toImageUrl(FlickrImageSuffix.LARGE)

        assert(expected == actual)
    }

    @Test
    fun givenValidUrl_whenConvertingToDeepLinkFriendlyUrl_thenExpectSameValue() {
        val expected = "https://live.staticflickr.com/7372/12502775644_acfd415fa7_b.jpg"

        val actual = expected
            .splitUrlToDeepLink()
            .joinUrlFromDeepLink()

        assert(expected == actual)
    }

}