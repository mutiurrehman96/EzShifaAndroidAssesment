package com.zamulk.ezshifaassesmentmuti.views.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.zamulk.ezshifaassesmentmuti.R
import com.zamulk.ezshifaassesmentmuti.network.Repository.homeUrl
import com.zamulk.ezshifaassesmentmuti.network.Repository.postsUrl
import com.zamulk.ezshifaassesmentmuti.network.Repository.servicesUrl
import com.zamulk.ezshifaassesmentmuti.databinding.FragmentHomeBinding
import com.zamulk.ezshifaassesmentmuti.network.Repository
import com.zamulk.ezshifaassesmentmuti.network.Repository.servicesBoxUrl
import com.zamulk.ezshifaassesmentmuti.views.activities.BaseActivity

class HomeFragment : Fragment() {
    private lateinit var baseActivity: BaseActivity
    private var binding: FragmentHomeBinding? = null
    private var menu:Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity = activity as BaseActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menu = binding?.bnvHome?.menu
        binding?.bnvHome?.itemIconTintList = null
        binding?.bnvHome?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_menu_home -> baseActivity.addFragment(HomeFragment(),resources.getString(R.string.fragment_tag_home))

                R.id.nav_menu_posts -> baseActivity.addFragment(
                    PostsFragment(),resources.getString(
                        R.string.fragment_tag_all_posts
                    ))

                R.id.nav_menu_services -> baseActivity.addFragment(
                    ServicesFragment(),resources.getString(
                        R.string.fragment_tag_services
                    ))
            }
            true
        }
        loadBottomMenuIcons()
        loadGovtServicesAndOtherServices()
       binding?.let {
           with(it.ilGovtServices) {
               clTourism.setOnClickListener {
                   baseActivity.addFragment(
                       ServicesFragment(),
                       getString(R.string.fragment_tag_services)
                   )
               }
               clTransport.setOnClickListener {
                   baseActivity.addFragment(
                       ServicesFragment(),
                       getString(R.string.fragment_tag_services)
                   )
               }
               clBanking.setOnClickListener {
                   baseActivity.addFragment(
                       ServicesFragment(),
                       getString(R.string.fragment_tag_services)
                   )
               }
               clMultiMedia.setOnClickListener {
                   baseActivity.addFragment(
                       ServicesFragment(),
                       getString(R.string.fragment_tag_services)
                   )
               }
           }
       }

    }

    private fun loadGovtServicesAndOtherServices() {
        binding?.let {
            Glide
                .with(baseActivity)
                .load(Repository.tourismUrl)
                .error(R.drawable.logo)
                .into(it.ilGovtServices.ivTourism)
            Glide
                .with(baseActivity)
                .load(Repository.transportUrl)
                .into(it.ilGovtServices.ivTransport)
            Glide
                .with(baseActivity)
                .load(Repository.bankingUrl)
                .error(R.drawable.logo)
                .into(it.ilGovtServices.ivBanking)
            Glide
                .with(baseActivity)
                .load(Repository.multiMediaUrl)
                .error(R.drawable.logo)
                .into(it.ilGovtServices.ivMultimedia)

            Glide
                .with(baseActivity)
                .load(Repository.careCenterUrl)
                .error(R.drawable.logo)
                .into(it.ilOtherServices.ivCareCenter)
            Glide
                .with(baseActivity)
                .load(Repository.healthUrl)
                .into(it.ilOtherServices.ivHealth)
            Glide
                .with(baseActivity)
                .load(Repository.mechanicalUrl)
                .error(R.drawable.logo)
                .into(it.ilOtherServices.ivMechanical)
            Glide
                .with(baseActivity)
                .load(Repository.emergencyUrl)
                .error(R.drawable.logo)
                .into(it.ilOtherServices.ivEmergency)
        }

    }

    private fun loadBottomMenuIcons()
    {
        Glide.with(baseActivity)
            .asBitmap()
            .load(homeUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                ) {
                    menu?.getItem(0)?.icon = BitmapDrawable(
                        resources, resource
                    )
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
        Glide.with(baseActivity)
            .asBitmap()
            .load(servicesBoxUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                ) {
                    menu?.getItem(1)?.icon = BitmapDrawable(
                        resources, resource
                    )
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
        Glide.with(baseActivity)
            .asBitmap()
            .load(postsUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                ) {
                    menu?.getItem(2)?.icon = BitmapDrawable(
                        resources, resource
                    )
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}