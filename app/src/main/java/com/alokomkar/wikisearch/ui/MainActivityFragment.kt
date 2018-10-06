package com.alokomkar.wikisearch.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alokomkar.wikisearch.R
import com.alokomkar.wikisearch.base.AdapterClickListener
import com.alokomkar.wikisearch.data.local.SearchContent
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment(), AdapterClickListener<SearchContent> {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var searchViewModel: SearchViewModel

    private val contentList = ArrayList<SearchContent>()
    private lateinit var contentRvAdapter: ContentRvAdapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject( this )
        searchViewModel = ViewModelProviders.of(this, viewModelFactory).get(
                SearchViewModel::class.java)
        rvSearchContent.apply {
            layoutManager = LinearLayoutManager( context )
            contentRvAdapter = ContentRvAdapter( contentList, this@MainActivityFragment )
            adapter = contentRvAdapter
        }
    }

    override fun onItemClick(position: Int, item: SearchContent) {}
}
