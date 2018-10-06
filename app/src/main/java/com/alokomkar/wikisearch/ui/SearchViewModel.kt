package com.alokomkar.wikisearch.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.alokomkar.wikisearch.data.Repository
import com.alokomkar.wikisearch.data.local.SearchContent
import com.alokomkar.wikisearch.data.remote.EntityMapper
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SearchViewModel
@Inject constructor( private val repository: Repository,
                     private val entityMapper: EntityMapper ) : ViewModel() {

    val searchResults = MutableLiveData<List<SearchContent>>()
    val error = MutableLiveData<String>()
    private var disposableObserver : DisposableObserver<ArrayList<SearchContent>> ?= null

    fun searchWiki( searchString: String ) {

        disposableObserver = object : DisposableObserver<ArrayList<SearchContent>>() {
            override fun onComplete() {

            }

            override fun onNext(t: ArrayList<SearchContent>) {
                searchResults.postValue(t)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                error.postValue(e.message?: "Unable to complete search")
            }
        }

        repository.getSearchResultsFromApi( searchString )
                .subscribeOn( Schedulers.newThread() )
                .flatMap {
                    t -> ObservableSource<ArrayList<SearchContent>> {
                    entityMapper.mapFromCached(t)
                } }
                .subscribeOn( Schedulers.computation() )
                .observeOn( AndroidSchedulers.mainThread() )
                .debounce( 400, TimeUnit.MILLISECONDS )
                .subscribe( disposableObserver!! )
    }

    fun disposeElements() {
        if( disposableObserver != null && !disposableObserver?.isDisposed!! ) disposableObserver?.dispose()
    }

}