package com.zhussain.pinboard.fragment


import android.app.ActivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.zhussain.pinboard.adapter.UserListAdapter
import com.zhussain.pinboard.databinding.FragmentUserBinding
import com.zhussain.pinboard.viewmodel.UserViewModel

/**
 * A simple [Fragment] subclass.
 */
class UserFragment : Fragment() {

    private lateinit var userviewModel : UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /**
         * Inflates the layout with Data Binding, sets its lifecycle owner to the UserFragment
         * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
         */
        val binding = FragmentUserBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.setLifecycleOwner(this)

        userviewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        // Giving the binding access to the userModel in layout-file
        binding.userModel = userviewModel

        // Sets the adapter of the usersList RecyclerView
        binding.usersList.adapter = UserListAdapter()

        /*userviewModel.response.observe(this, Observer {
            hasResponse->
            binding.textView.text = hasResponse
        })*/

        return binding.root;
    }


}
