package com.alokomkar.wikisearch.data.local

data class SearchContent( var searchPageId : Int = -1,
                          var searchHeader : String = "",
                          var searchSubHeader : String = "",
                          var searchImage : String = "",
                          var searchUrl : String = "" ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SearchContent

        if (searchPageId != other.searchPageId) return false

        return true
    }

    override fun hashCode(): Int {
        return searchPageId
    }
}
