package com.alokomkar.wikisearch.ui

import android.arch.lifecycle.Observer
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
import android.widget.Toast
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher


/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment(), AdapterClickListener<SearchContent>, Observer<List<SearchContent>> {


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
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager( context )
            contentRvAdapter = ContentRvAdapter( contentList, this@MainActivityFragment )
            adapter = contentRvAdapter
        }

        searchViewModel.searchResults.observeForever( this )
        searchViewModel.error.observeForever({ error -> Toast.makeText(context,
                error,
                Toast.LENGTH_LONG).show()})

        tvSearch.addTextChangedListener( object : TextWatcher {

            override fun afterTextChanged( editable : Editable?) {
                if( editable != null ) {
                    val searchString = editable.toString()
                    if( searchString.isEmpty() )
                        onChanged( ArrayList() )
                    else
                        searchViewModel.searchWiki( searchString )

                }

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        })
    }

    override fun onChanged(t: List<SearchContent>?) {
        contentList.clear()
        contentList.addAll(t?:ArrayList() )
        contentRvAdapter.notifyDataSetChanged()
    }

    override fun onItemClick(position: Int, item: SearchContent) {
        try {
            val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.searchUrl))
            startActivity(myIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context,
                    R.string.no_application_found,
                    Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        searchViewModel.disposeElements()
    }
}
