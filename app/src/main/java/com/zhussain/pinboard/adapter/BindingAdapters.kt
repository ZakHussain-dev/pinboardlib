package com.zhussain.pinboard.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zhussain.pinboard.R
import com.zhussain.pinboard.Util
import com.zhussain.pinboard.viewmodel.UsersApiStatus
import com.zhussain.pinboard.model.UsersProperty
import com.zhussain.pinboardlib.PinboardLib

/**
 * Adapter to bind layout with data..
 */

/**
 * When there is no Users property data (data is null), hide the [RecyclerView], otherwise show it.
 */

@BindingAdapter("userlistData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<UsersProperty>?) {
    val adapter = recyclerView.adapter as UserListAdapter
    adapter.submitList(data)
}


@BindingAdapter("zakImg")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        hasUrl-> PinboardLib.getInstance(imgView.context,Util.MEMORY_SIZE)
        .displayImage(imgUrl,imgView, R.drawable.loading_animation)
    }
}

/**
 * This binding adapter displays the [UsersApiStatus] of the network request in an image view.  When
 * the request is loading, it displays a loading_animation.  If the request has an error, it
 * displays a broken image to reflect the connection error.  When the request is finished, it
 * hides the image view.
 */
@BindingAdapter("usersApiStatus")
fun bindStatus(statusImageView: ImageView, status: UsersApiStatus?){
    when (status) {
        UsersApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        UsersApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        UsersApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

