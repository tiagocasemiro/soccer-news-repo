package com.news.repository.remote.techmundo

import com.rometools.rome.feed.synd.SyndFeed
import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import java.net.URL


class TechMundoRepository(private val syndFeedInput: SyndFeedInput) {

    fun feed(): SyndFeed {
        return syndFeedInput.build(XmlReader(URL("https://rss.tecmundo.com.br/feed")))
    }
}