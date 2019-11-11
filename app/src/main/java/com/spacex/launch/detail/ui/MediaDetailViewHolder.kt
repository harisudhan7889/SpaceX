package com.spacex.launch.detail.ui

import android.view.View
import com.spacex.launch.common.AppUtil
import com.spacex.launch.common.recycler.BaseViewHolder
import com.spacex.launch.common.recycler.OnItemClickListener
import com.spacex.launch.detail.model.Detail
import com.spacex.launch.detail.model.MediaDetail
import kotlinx.android.synthetic.main.view_media_detail.view.*

/**
 * Viewholder that holds views to display the media items
 *
 * @see MediaDetail
 *
 * @author Hari Hara Sudhan.N
 */
class MediaDetailViewHolder(view: View?)
    : BaseViewHolder<Detail, OnItemClickListener<Detail>>(view),
        View.OnClickListener {

    private var listener: OnItemClickListener<Detail>? = null
    private var mediaDetail: MediaDetail? = null

    override fun onBind(detail: Detail?, listener: OnItemClickListener<Detail>?) {
        mediaDetail = detail as? MediaDetail
        mediaDetail?.let {
            AppUtil.loadImageToView(itemView.context, itemView.videoThumbnail,
                    it.videoThumbnailUrl)
            itemView.videoDocument.setOnClickListener(this)
            itemView.wikiDocument.setOnClickListener(this)
            itemView.articleDocument.setOnClickListener(this)
        }
        this.listener = listener
    }

    /**
     * Click events are handled here.
     */
    override fun onClick(mediaView: View) {
        when (mediaView) {
            itemView.videoDocument -> {
                listener?.onItemClicked(mediaDetail?.createMediaItem(MediaDetail.VIDEO))
            }
            itemView.wikiDocument -> {
                listener?.onItemClicked(mediaDetail?.createMediaItem(MediaDetail.WIKI))
            }
            itemView.articleDocument -> {
                listener?.onItemClicked(mediaDetail?.createMediaItem(MediaDetail.ARTICLE))
            }
        }
    }

    /**
     * Creates a new instance to media object based on the media type.
     *
     * @param mediaType
     * @see MediaDetail.Companion.MediaType to understand about different media types.
     */
    private fun MediaDetail.createMediaItem(@MediaDetail.Companion.MediaType mediaType: Int):
            MediaDetail {
        return when (mediaType) {
            MediaDetail.VIDEO -> {
                MediaDetail(videoLink = videoLink, mediaType = mediaType)
            }
            MediaDetail.WIKI -> {
                MediaDetail(wikipediaLink = wikipediaLink, mediaType = mediaType)
            }
            MediaDetail.ARTICLE -> {
                MediaDetail(articleLink = articleLink, mediaType = mediaType)
            }
            else -> {
                MediaDetail(mediaType = mediaType)
            }
        }
    }
}