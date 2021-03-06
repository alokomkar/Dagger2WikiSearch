package com.alokomkar.wikisearch.ui

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.alokomkar.wikisearch.R
import com.alokomkar.wikisearch.base.AdapterClickListener
import com.alokomkar.wikisearch.data.local.SearchContent
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@SuppressLint("InflateParams")
class ContentRvAdapter(private val contentList : ArrayList<SearchContent>,
                       private val adapterClickListener: AdapterClickListener<SearchContent> ) : RecyclerView.Adapter<ContentRvAdapter.ViewHolder>() {

    private val requestOptions = RequestOptions()
    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int): ViewHolder {
        requestOptions.error(R.mipmap.ic_launcher)
        requestOptions.placeholder(R.drawable.ic_launcher_background)
        return ViewHolder( LayoutInflater.from( parent.context ).inflate(R.layout.item_search, null, false))
    }


    override fun getItemCount(): Int
        = contentList.size

    override fun onBindViewHolder( holder : ViewHolder, position : Int )
        = holder.bindData( contentList[position] )

    inner class ViewHolder( itemView : View ) : RecyclerView.ViewHolder( itemView ), View.OnClickListener {


        private val tvHeader = itemView.findViewById<TextView>( R.id.tvHeader )
        private val tvSubHeader = itemView.findViewById<TextView>( R.id.tvSubHeader )
        private val ivContent = itemView.findViewById<ImageView>( R.id.ivContent )

        init {
            itemView.setOnClickListener( this )
        }

        fun bindData(searchContent: SearchContent) {
            tvHeader.text = searchContent.searchHeader
            tvSubHeader.text = searchContent.searchSubHeader
            Glide.with(ivContent)
                    .asBitmap()
                    .apply( requestOptions )
                    .load( searchContent.searchImage )
                    .into( ivContent )
        }

        override fun onClick( view : View? ) {
            val position = adapterPosition
            if( position != RecyclerView.NO_POSITION ) {
                adapterClickListener.onItemClick( position, contentList[position] )
            }
        }

    }
}