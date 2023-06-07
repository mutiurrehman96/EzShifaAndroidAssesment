package com.zamulk.ezshifaassesmentmuti.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.zamulk.ezshifaassesmentmuti.databinding.FragmentPostsBinding
import com.zamulk.ezshifaassesmentmuti.viewModels.BaseActivityViewModel
import com.zamulk.ezshifaassesmentmuti.views.activities.BaseActivity
import com.zamulk.ezshifaassesmentmuti.views.adapters.PostsAdapter

class PostsFragment : Fragment() {

    private val baseActivityViewModel: BaseActivityViewModel by activityViewModels()
    private lateinit var baseActivity: BaseActivity
    private var binding: FragmentPostsBinding? = null
    private lateinit var postsAdapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity = activity as BaseActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPostsRecycler()

        baseActivityViewModel.getPosts().observeOnce(viewLifecycleOwner) {
            it.let {
                binding?.pbLoadingPosts?.visibility = View.GONE
                postsAdapter.updatePostItems(it as ArrayList)
            }

        }
        baseActivityViewModel.getPostErrors().observeOnce(viewLifecycleOwner) {
            it.let {
                binding?.pbLoadingPosts?.visibility = View.GONE
                baseActivity.showToast(it)
            }
        }
    }

    private fun initPostsRecycler() {
        binding?.rvPosts?.setHasFixedSize(true)
        postsAdapter = PostsAdapter(baseActivity)
        binding?.rvPosts?.adapter = postsAdapter
    }

    private fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
        observe(lifecycleOwner, object : Observer<T> {

            override fun onChanged(value: T) {
                if (value != null) {
                    observer.onChanged(value)
                }
                removeObserver(this)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}