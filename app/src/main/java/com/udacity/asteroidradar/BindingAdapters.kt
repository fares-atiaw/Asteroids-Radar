package com.udacity.asteroidradar

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.models.Asteroid
import com.udacity.asteroidradar.screens.main.RV_Adapter

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    val context = imageView.context
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
        imageView.contentDescription = String.format(context.getString(R.string.asteroid_icon_content_description_format), "Hazardous")
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
        imageView.contentDescription = String.format(context.getString(R.string.asteroid_icon_content_description_format), "Safe")
    }
}

@BindingAdapter("setDescription")                     /**  COOL  **/
fun bindTextWithImageView(imageView: ImageView, text: String?) {
    val context = imageView.context
    imageView.contentDescription = String.format(context.getString(R.string.nasa_picture_of_day_content_description_format), text)
}

@BindingAdapter("setIconDescription")                     /**  COOL  **/
fun bindTextWithIcon(imageView: ImageView, isHazardous: Boolean) {
    val context = imageView.context
    if (isHazardous) {
        imageView.contentDescription = String.format(context.getString(R.string.asteroid_icon_content_description_format), "Hazardous")
    } else {
        imageView.contentDescription = String.format(context.getString(R.string.asteroid_icon_content_description_format), "Safe")
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}

@BindingAdapter("listData")								/**  COOL  **/
fun bindList(rv : RecyclerView, data : List<Asteroid>?)
{
    data?.let {
        val myAdapter = rv.adapter as RV_Adapter
        myAdapter.submitList(data)
    }
//    rv.adapter = myAdapter // nope
}

@BindingAdapter("imageUrl")
fun bindImage(imageView : ImageView, imageUrl :String?)             //  check the type ????
{
    imageUrl?.let {
        val image = it.toUri().buildUpon().scheme("https").build()
        GlideApp.with(imageView.context)
            .load(image)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
            .into(imageView)

        /*
        Glide.with(imageView.context)
            .load(image)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imageView)
         */

/** Do this first**/
        /*// Glide
        implementation ("com.github.bumptech.glide:glide:4.13.2") {
            exclude group: "com.android.support"
        }
        kapt 'androidx.annotation:annotation:1.4.0'
        kapt 'com.github.bumptech.glide:compiler:4.13.2'
        implementation ("com.github.bumptech.glide:glide:4.13.2@aar") {
            transitive = true
        }*/
    /** plus **/
        /*import com.bumptech.glide.annotation.GlideModule
        import com.bumptech.glide.module.AppGlideModule

        @GlideModule
        class MyGlideApp : AppGlideModule()*/

    }
}

