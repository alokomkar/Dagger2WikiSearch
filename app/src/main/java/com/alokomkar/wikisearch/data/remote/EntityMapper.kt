package com.alokomkar.wikisearch.data.remote

import com.alokomkar.wikisearch.base.BaseEntityMapper
import com.alokomkar.wikisearch.data.local.SearchContent
import javax.inject.Inject

class EntityMapper @Inject constructor() : BaseEntityMapper<Response, SearchContent> {

    override fun mapFromCached(type: Response): ArrayList<SearchContent> {
        val contentList = ArrayList<SearchContent>()
        for( page in type.query?.pages!! ) {
            contentList.add(SearchContent(
                    page.pageid!!,
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