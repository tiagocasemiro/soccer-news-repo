package com.news.repository.remote.exame

import com.rometools.rome.feed.synd.SyndFeed
import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import java.net.URL

class ExameRepository(private val syndFeedInput: SyndFeedInput) {

    fun feed(): SyndFeed {
        return syndFeedInput.build(XmlReader(URL("https://exame.abril.com.br/feed")))
    }
}