package com.alokomkar.wikisearch.data.remote

import com.alokomkar.wikisearch.base.BaseEntityMapper
import com.alokomkar.wikisearch.data.local.SearchContent

class EntityMapper : BaseEntityMapper<Response, SearchContent> {

    override fun mapFromCached(type: Response): ArrayList<SearchContent> {
        val contentList = ArrayList<SearchContent>()
        for( page in type.query?.pages!! ) {
            contentList.add(SearchContent(
                    page.title!!,
                    page.terms?.description!![0],
                    page.thumbnail?.source!!,
                    "https://en.wikipedia.org/?curid=" + page.pageid))
        }
        return contentList
    }

    override fun mapToCached(url: String, type: ArrayList<SearchContent>): Response
        = Response()

}