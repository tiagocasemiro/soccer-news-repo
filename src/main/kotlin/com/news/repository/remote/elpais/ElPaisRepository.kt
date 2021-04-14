package com.news.repository.remote.elpais

import com.rometools.rome.feed.synd.SyndFeed
import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import java.net.URL

class ElPaisRepository(private val syndFeedInput: SyndFeedInput) {
    fun feed(): SyndFeed {
        return syndFeedInput.build(XmlReader(URL("https://feeds.elpais.com/mrss-s/pages/ep/site/brasil.elpais.com/portada")))
    }
}