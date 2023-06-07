package com.zamulk.ezshifaassesmentmuti.network

import androidx.lifecycle.MutableLiveData
import com.zamulk.ezshifaassesmentmuti.models.PostsModel
import com.zamulk.ezshifaassesmentmuti.models.ServicesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository {
    const val tourismUrl = "https://cdn-icons-png.flaticon.com/128/719/719372.png"
    const val transportUrl = "https://cdn-icons-png.flaticon.com/128/3058/3058953.png"
    private const val transportCarUrl = "https://cdn-icons-png.flaticon.com/128/3774/3774278.png"
    private const val tourismMosqueUrl = "https://cdn-icons-png.flaticon.com/128/1908/1908082.png"
    const val bankingUrl = "https://cdn-icons-png.flaticon.com/128/2830/2830289.png"
    const val multiMediaUrl = "https://cdn-icons-png.flaticon.com/128/3141/3141285.png"

    private const val hospitalUrl = "https://cdn-icons-png.flaticon.com/128/4320/4320371.png"
    private const val restaurantsUrl = "https://cdn-icons-png.flaticon.com/128/1830/1830318.png"
    private const val universitiesUrl = "https://cdn-icons-png.flaticon.com/128/8074/8074800.png"
    private const val schoolsUrl = "https://cdn-icons-png.flaticon.com/128/8074/8074788.png"

    private const val collegesUrl = "https://cdn-icons-png.flaticon.com/128/8074/8074794.png"
    private const val educationUrl = "https://cdn-icons-png.flaticon.com/128/3976/3976631.png"

    const val homeUrl = "https://cdn-icons-png.flaticon.com/128/553/553416.png"
    const val postsUrl = "https://cdn-icons-png.flaticon.com/128/2921/2921222.png"
    const val servicesUrl = "https://cdn-icons-png.flaticon.com/128/1514/1514666.png"
    const val careCenterUrl = "https://cdn-icons-png.flaticon.com/128/2382/2382461.png"
    const val healthUrl = "https://cdn-icons-png.flaticon.com/128/3004/3004458.png"
    const val mechanicalUrl = "https://cdn-icons-png.flaticon.com/128/8598/8598993.png"
    const val emergencyUrl = "https://cdn-icons-png.flaticon.com/128/2785/2785693.png"
    const val servicesBoxUrl = "https://cdn-icons-png.flaticon.com/128/3631/3631163.png"


    val error = MutableLiveData<String>()
    fun getPosts(call: Call<List<PostsModel>>): MutableLiveData<List<PostsModel>> {
        val postsMutableLiveData = MutableLiveData<List<PostsModel>>()
        call.enqueue(object : Callback<List<PostsModel>> {
            override fun onResponse(
                call: Call<List<PostsModel>>, response: Response<List<PostsModel>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        postsMutableLiveData.value = it
                    }
                }
            }

            override fun onFailure(call: Call<List<PostsModel>>, t: Throwable) {
                t.localizedMessage?.let {
                    error.value = it
                }
                t.printStackTrace()
            }

        })
        return postsMutableLiveData
    }

    fun getServices(): MutableLiveData<List<ServicesModel>> {
        val servicesMutableLiveData = MutableLiveData<List<ServicesModel>>()
        servicesMutableLiveData.value = prepareServicesDummy()
        return servicesMutableLiveData

    }

    private fun prepareServicesDummy(): List<ServicesModel> {
        val servicesList = ArrayList<ServicesModel>().also {
            it.add(
                ServicesModel(
                    tourismMosqueUrl,
                    "Tourism",
                    transportCarUrl,
                    "transport",
                    bankingUrl,
                    "banking",
                    multiMediaUrl,
                    "multiMedia",
                    hospitalUrl,
                    "hospital",
                    restaurantsUrl,
                    "restaurants",
                    universitiesUrl,
                    "universities",
                    schoolsUrl,
                    "schools",
                    collegesUrl,
                    "colleges",
                    educationUrl,
                    "education"
                )
            )
            it.add(
                ServicesModel(
                    tourismMosqueUrl,
                    "Tourism",
                    transportCarUrl,
                    "transport",
                    bankingUrl,
                    "banking",
                    multiMediaUrl,
                    "multiMedia",
                    hospitalUrl,
                    "hospital",
                    restaurantsUrl,
                    "restaurants",
                    universitiesUrl,
                    "universities",
                    schoolsUrl,
                    "schools",
                    collegesUrl,
                    "colleges",
                    educationUrl,
                    "education"
                )
            )
            it.add(
                ServicesModel(
                    tourismMosqueUrl,
                    "Tourism",
                    transportCarUrl,
                    "transport",
                    bankingUrl,
                    "banking",
                    multiMediaUrl,
                    "multiMedia",
                    hospitalUrl,
                    "hospital",
                    restaurantsUrl,
                    "restaurants",
                    universitiesUrl,
                    "universities",
                    schoolsUrl,
                    "schools",
                    collegesUrl,
                    "colleges",
                    educationUrl,
                    "education"
                )
            )
        }
        return servicesList
    }
}