package com.alokomkar.wikisearch.base

interface AdapterClickListener<in T> {
    fun onItemClick( position : Int, item : T )
}