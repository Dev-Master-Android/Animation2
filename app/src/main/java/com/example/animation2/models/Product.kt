package com.example.animation2.models

import android.os.Parcel
import android.os.Parcelable


data class Product(val imageResource: Int, val name: String, val price: Double) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(imageResource)
        parcel.writeString(name)
        parcel.writeDouble(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }


        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}
