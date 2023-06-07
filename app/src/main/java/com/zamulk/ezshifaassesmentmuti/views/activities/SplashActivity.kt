package com.zamulk.ezshifaassesmentmuti.views.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewModelScope
import com.zamulk.ezshifaassesmentmuti.databinding.ActivitySplashBinding
import com.zamulk.ezshifaassesmentmuti.viewModels.SplashViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val splashViewModel: SplashViewModel by viewModels()
    private var permissionsRequestCode = 1
    private var permissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (!hasPermissions(this, permissions)) {
            Log.e("TAG", "onCreate: !hasPermissions")
            ActivityCompat.requestPermissions(this, permissions, permissionsRequestCode)
        } else {
            //Both permission already granted
            launchTimer()
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                var allPermissionsGranted = false
                if (grantResults.isNotEmpty() && permissions.size == grantResults.size) {
                    var i = 0
                    while (i < permissions.size) {
                        allPermissionsGranted = grantResults[i] == PackageManager.PERMISSION_GRANTED
                        if (!allPermissionsGranted)
                            break
                        i++
                    }
                } else {
                    allPermissionsGranted = false
                }
                if (allPermissionsGranted) {
                    launchTimer()
                } else {
                    binding.pbLoading.visibility= View.GONE
                    Toast.makeText(
                        this@SplashActivity,
                        "Camera and Storage Permission is required to proceed",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun launchTimer() {
        splashViewModel.viewModelScope.launch(Dispatchers.Main) {
            delay(3000L)
            startActivity(Intent(this@SplashActivity, BaseActivity::class.java))
            finish()
            withContext(Dispatchers.Main){
                binding.pbLoading.visibility= View.GONE
            }
            //Activity will not remain added in backstack due to android:noHistory="true" in manifest
        }
    }


    private fun hasPermissions(context: Context, permissions: Array<String>): Boolean {
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }
}