package com.zamulk.ezshifaassesmentmuti.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zamulk.ezshifaassesmentmuti.R
import com.zamulk.ezshifaassesmentmuti.models.ServicesModel
import com.zamulk.ezshifaassesmentmuti.databinding.ListItemsServicesBinding

class ServicesAdapter(private val mContext: Context) :
    RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder>() {
    private var allServicesList: ArrayList<ServicesModel>

    init {
        allServicesList = ArrayList()
    }

    inner class ServicesViewHolder(val binding: ListItemsServicesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int): Int {
        return position
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        val binding =
            ListItemsServicesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServicesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {
        val serviceList: ServicesModel = allServicesList[position]

        with(holder)
        {
            with(binding) {
                with(serviceList)
                {
                    glideImage(tourismUrl, ivTourism)
                    tvTourism.text = tourism

                    glideImage(transportUrl, ivTransport)
                    tvTransport.text = transport

                    glideImage(bankingUrl, ivBanking)
                    tvBanking.text = banking

                    glideImage(multiMediaUrl, ivMultimedia)
                    tvMultiMedia.text = multiMedia

                    glideImage(hospitalUrl, ivTHospitals)
                    tvHospitals.text = hospital

                    glideImage(restaurantsUrl, ivRestaurants)
                    tvRestaurants.text = restaurants

                    glideImage(universitiesUrl, ivUniversities)
                    tvUniversities.text = universities

                    glideImage(schoolsUrl, ivSchools)
                    tvSchools.text = schools

                    glideImage(collagesUrl, ivCollages)
                    tvCollages.text = collages

                    glideImage(educationUrl, ivEducation)
                    tvEducation.text = education

                }
            }
        }
    }

    private fun glideImage(url: String, imgView: ImageView) {
        Glide
            .with(mContext)
            .load(url)
            .error(R.drawable.logo)
            .into(imgView)
    }

    fun updatePostItems(servicesList: ArrayList<ServicesModel>) {
        this.allServicesList = servicesList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return allServicesList.size
    }

}
