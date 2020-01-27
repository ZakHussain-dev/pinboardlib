package com.zhussain.pinboard.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zhussain.pinboard.R
import com.zhussain.pinboard.Util
import com.zhussain.pinboard.databinding.FragmentDisplayResultBinding
import com.zhussain.pinboardlib.PinboardLib

/**
 * A simple [Fragment] subclass.
 */
class DisplayResult : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var binding = FragmentDisplayResultBinding.inflate(inflater,container,false)


        val args = DisplayResultArgs.fromBundle(arguments!!)
        val pinboardLib = PinboardLib.getInstance(activity!!,Util.MEMORY_SIZE)

        // Display image...
        pinboardLib.displayImage(args.url,binding.imageview,R.drawable.loading_animation)

        return binding.root;
    }
}
