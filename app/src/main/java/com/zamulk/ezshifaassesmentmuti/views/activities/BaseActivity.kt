package com.zamulk.ezshifaassesmentmuti.views.activities

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.google.android.material.navigation.NavigationView
import com.zamulk.ezshifaassesmentmuti.R
import com.zamulk.ezshifaassesmentmuti.databinding.ActivityBaseBinding
import com.zamulk.ezshifaassesmentmuti.network.Repository
import com.zamulk.ezshifaassesmentmuti.views.fragments.HomeFragment
import com.zamulk.ezshifaassesmentmuti.views.fragments.PostsFragment
import com.zamulk.ezshifaassesmentmuti.views.fragments.ServicesFragment


class BaseActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener,
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityBaseBinding
    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var actionBar: ActionBar
    private lateinit var navMenu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actionBar = supportActionBar!!
        navMenu = binding.navView.menu
        binding.navView.setNavigationItemSelectedListener(this)
        loadNavDrawerMenuIcons()
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.nav_open,
            R.string.nav_close
        )
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        actionBar.setDisplayHomeAsUpEnabled(true)
        fragmentManager = supportFragmentManager
        fragmentManager.addOnBackStackChangedListener(this)
        loadFirstFragment()
    }

    private fun loadNavDrawerMenuIcons() {
        Glide.with(this)
            .asBitmap()
            .load(Repository.homeUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                ) {
                    navMenu.getItem(0).icon = BitmapDrawable(
                        resources, resource
                    )
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
        Glide.with(this)
            .asBitmap()
            .load(Repository.servicesUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                ) {
                    navMenu.getItem(1).icon = BitmapDrawable(
                        resources, resource
                    )
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
        Glide.with(this)
            .asBitmap()
            .load(Repository.postsUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                ) {
                    navMenu.getItem(2).icon = BitmapDrawable(
                        resources, resource
                    )
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun loadFirstFragment() {
        addFragment(
            HomeFragment(), resources.getString(R.string.fragment_tag_home)
        )
    }

    private var doubleBackToExitPressedOnce = false

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity()
        } else {
            if (getBackStackEntryCount == 1) {
                showToast(getString(R.string.are_you_sure_to_exit))
                doubleBackToExitPressedOnce = true
                Handler(Looper.getMainLooper()).postDelayed(
                    { doubleBackToExitPressedOnce = false }, 2000
                )
            } else if (getBackStackEntryCount < 1) {
                loadFirstFragment()
            } else {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun addTitle(title: String?) {
        actionBar.title = title
    }

    fun showToast(toastMessage: String) {
        Toast.makeText(this@BaseActivity, toastMessage, Toast.LENGTH_SHORT).show()
    }

    fun addFragment(fragment: Fragment, fragmentTag: String) {
        fragmentTransaction = supportFragmentManager.beginTransaction()
        with(fragmentTransaction) {
            replace(R.id.fragmentContainerView, fragment, fragmentTag)
            addToBackStack(fragmentTag)
            commit()
        }
    }

    override fun onBackStackChanged() {
        if (checkFragmentTags() != null) addTitle(checkFragmentTags())
    }

    private val getBackStackEntryCount: Int
        get() {
            return fragmentManager.backStackEntryCount
        }

    private fun checkFragmentTags(): String? {
        var tag: String? = null
        try {
            if (supportFragmentManager.backStackEntryCount > 0) {
                tag =
                    supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1).name
                if (tag != null) {
                    return tag
                }
            } else {
                return null
            }
        } catch (e: Resources.NotFoundException) {
            e.printStackTrace()
        }
        return tag
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_drawer_menu_home -> addFragment(
                HomeFragment(),
                resources.getString(R.string.fragment_tag_home)
            )

            R.id.nav_drawer_menu_posts -> addFragment(
                PostsFragment(), resources.getString(
                    R.string.fragment_tag_all_posts
                )
            )

            R.id.nav_drawer_menu_services -> addFragment(
                ServicesFragment(), resources.getString(
                    R.string.fragment_tag_services
                )
            )
        }
        closeDrawer()
        return true
    }
    private fun closeDrawer() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
    }
}