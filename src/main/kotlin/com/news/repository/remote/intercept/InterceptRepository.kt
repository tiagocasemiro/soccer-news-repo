package com.news.repository.remote.intercept

import com.rometools.rome.feed.synd.SyndFeed
import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import java.net.URL

class InterceptRepository(private val syndFeedInput: SyndFeedInput) {

    fun feed(): SyndFeed {
        return syndFeedInput.build(XmlReader(URL("https://theintercept.com/feed/?lang=pt")))
    }
}