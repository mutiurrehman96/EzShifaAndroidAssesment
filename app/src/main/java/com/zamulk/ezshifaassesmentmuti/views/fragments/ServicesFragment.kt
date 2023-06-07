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
import com.zamulk.ezshifaassesmentmuti.databinding.FragmentServicesBinding
import com.zamulk.ezshifaassesmentmuti.viewModels.BaseActivityViewModel
import com.zamulk.ezshifaassesmentmuti.views.activities.BaseActivity
import com.zamulk.ezshifaassesmentmuti.views.adapters.ServicesAdapter

class ServicesFragment : Fragment() {
    private val baseActivityViewModel: BaseActivityViewModel by activityViewModels()
    private lateinit var baseActivity: BaseActivity
    private var binding: FragmentServicesBinding? = null
    private lateinit var servicesAdapter: ServicesAdapter
    private val TAG = "ServicesFragment"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity = activity as BaseActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentServicesBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initServicesRecycler()

        baseActivityViewModel.getServicesData().observeOnce(viewLifecycleOwner) {
            it.let {
                binding?.pbLoadingServices?.visibility = View.GONE
                servicesAdapter.updatePostItems(it as ArrayList)
            }

        }
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
    private fun initServicesRecycler() {
        binding?.rvServices?.setHasFixedSize(true)
        servicesAdapter = ServicesAdapter(baseActivity)
        binding?.rvServices?.adapter = servicesAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}