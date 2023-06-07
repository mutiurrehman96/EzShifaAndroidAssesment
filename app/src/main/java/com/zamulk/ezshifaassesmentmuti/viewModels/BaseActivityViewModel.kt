package com.zamulk.ezshifaassesmentmuti.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zamulk.ezshifaassesmentmuti.models.PostsModel
import com.zamulk.ezshifaassesmentmuti.network.Repository
import com.zamulk.ezshifaassesmentmuti.network.RetrofitService
import com.zamulk.ezshifaassesmentmuti.models.ServicesModel

class BaseActivityViewModel : ViewModel() {
    private var postMutableLiveData = MutableLiveData<List<PostsModel>>()
    private var servicesMutableLiveData = MutableLiveData<List<ServicesModel>>()
    private var errorMutableLiveData = MutableLiveData<String>()

    fun getPosts(): LiveData<List<PostsModel>> {
        postMutableLiveData = Repository.getPosts(RetrofitService.endPointsService().getAllPosts())
        return postMutableLiveData
    }

    fun getPostErrors(): LiveData<String> {
        errorMutableLiveData = Repository.error
        return errorMutableLiveData
    }

    fun getServicesData(): LiveData<List<ServicesModel>> {
        servicesMutableLiveData = Repository.getServices()
        return servicesMutableLiveData
    }


}