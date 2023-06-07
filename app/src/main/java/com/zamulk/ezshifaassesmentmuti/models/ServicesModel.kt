package com.zamulk.ezshifaassesmentmuti.models

import com.google.gson.annotations.SerializedName

data class ServicesModel(
    @SerializedName("tourismUrl") val tourismUrl: String,
    @SerializedName("tourism") val tourism: String,
    @SerializedName("transportUrl") val transportUrl: String,
    @SerializedName("transport") val transport: String,
    @SerializedName("bankingUrl") val bankingUrl: String,
    @SerializedName("banking") val banking: String,
    @SerializedName("multiMediaUrl") val multiMediaUrl: String,
    @SerializedName("multiMedia") val multiMedia: String,
    @SerializedName("hospitalUrl") val hospitalUrl: String,
    @SerializedName("hospital") val hospital: String,
    @SerializedName("restaurantsUrl") val restaurantsUrl: String,
    @SerializedName("restaurants") val restaurants: String,
    @SerializedName("universitiesUrl") val universitiesUrl: String,
    @SerializedName("universities") val universities: String,
    @SerializedName("schoolsUrl") val schoolsUrl: String,
    @SerializedName("schools") val schools: String,
    @SerializedName("collagesUrl") val collagesUrl: String,
    @SerializedName("collages") val collages: String,
    @SerializedName("educationUrl") val educationUrl: String,
    @SerializedName("education") val education: String,
)

