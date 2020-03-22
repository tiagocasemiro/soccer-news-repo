package com.news.repository.remote.nexo

import com.rometools.rome.feed.synd.SyndFeed
import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import java.net.URL

class NexoRepository(private val syndFeedInput: SyndFeedInput) {

    fun feed(): SyndFeed {
        return syndFeedInput.build(XmlReader(URL("https://www.nexojornal.com.br/rss.xml")))
    }
}