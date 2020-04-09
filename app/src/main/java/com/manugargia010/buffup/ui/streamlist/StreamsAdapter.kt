package com.manugargia010.buffup.ui.streamlist

import com.manugargia010.domain.model.stream.Stream
import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manugargia010.buffup.R
import com.manugargia010.buffup.ui.extension.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_stream_item.view.*
import kotlin.properties.Delegates

class StreamsAdapter(val listener: OnStreamClickedListener) : RecyclerView.Adapter<StreamsAdapter.ViewHolder>() {

    var items: List<Stream> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.view_stream_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(stream: Stream) {
            with(containerView) {
                streamTitle.text = stream.title
                streamDescription.text = stream.description
                setOnClickListener { listener.onStreamClicked(stream) }
            }
        }
    }

    interface OnStreamClickedListener {
        fun onStreamClicked(stream: Stream)
    }

}
