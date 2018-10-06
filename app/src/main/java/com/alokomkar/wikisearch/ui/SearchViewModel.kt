package com.alokomkar.wikisearch.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.alokomkar.wikisearch.data.Repository
import com.alokomkar.wikisearch.data.local.SearchContent
import com.alokomkar.wikisearch.data.remote.EntityMapper
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SearchViewModel
@Inject constructor( private val repository: Repository,
                     private val entityMapper: EntityMapper ) : ViewModel() {

    var searchResults: MutableLiveData<List<SearchContent>> = MutableLiveData()

    fun searchWiki( searchString: String ) {
        repository.getSearchResultsFromApi( searchString )
                .subscribeOn(Schedulers.newThread())
                .flatMap { t -> ObservableSource<ArrayList<SearchContent>> {
                        entityMapper.mapFromCached(t)
                } }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce( 400, TimeUnit.MILLISECONDS )
                .subscribe { t -> searchResults.postValue(t) }
    }

}