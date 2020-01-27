package com.zhussain.pinboard.fragment


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.zhussain.pinboard.R
import com.zhussain.pinboard.databinding.FragmentTestViewBinding
import kotlinx.android.synthetic.main.fragment_test_view.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

/**
 * A simple [Fragment] subclass.
 */
class TestView : Fragment() {
    lateinit var binding: FragmentTestViewBinding
    private val uiHandler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTestViewBinding.inflate(inflater, container, false);

        val editText = binding.editTextUrl.editText

        editText?.let {
            it.setOnClickListener {
                binding.textviewError.text = "" // clear error message..
                binding.progressBar.visibility = View.INVISIBLE
            }

        }

        binding.submitButton.setOnClickListener {

            editText?.text.toString().let {

                if (!it.toString().isEmpty()) {
                    hasImgUrlAddressValid((it))
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.textviewError.text = "Please enter Url."
                }

            }
        }


        return binding.root;
    }

    // Background Thread, Only for Testing Purpose.....
    fun hasImgUrlAddressValid(url: String) {
        val executorService =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
        executorService.submit {
            try {
                val _url = URL(url)
                val conn: HttpURLConnection = _url.openConnection() as HttpURLConnection
                var bitmap = BitmapFactory.decodeStream(conn.inputStream)
                conn.disconnect()
                if (bitmap != null) {
                    navigateDisplay(url)
                } else {
                    updateTextError()
                }
            } catch (e: Exception) {
                updateTextError()
                Log.e("Error: ", e.toString())
            }
        }
    }

    fun updateTextError() {
        uiHandler.post {
            binding.progressBar.visibility = View.INVISIBLE
            binding.textviewError.text = "Please enter valid image Url only..."
        }
    }

    fun navigateDisplay(url: String) {
        uiHandler.post {
            findNavController().navigate(
                TestViewDirections.actionTestViewToDisplayResult(
                    url
                )
            )
        }
    }
}
