package com.udacity.asteroidradar.screens.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.databinding.ItemAstroidBinding
import com.udacity.asteroidradar.models.Asteroid

class RV_Adapter(val clickListener: AsteroidListener) : ListAdapter<Asteroid, RV_Adapter.MyViewHolder>(Diff_Asteroid()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.fromInflating(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class MyViewHolder private constructor(private val binding: ItemAstroidBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Asteroid, clickListener: AsteroidListener) {  // use this instead of using transactions for each view here (Binding Adapter).
            binding.data = data
            binding.action = clickListener
            binding.executePendingBindings()
        }

        /** private methods**/


        /** static variable(s) or method(s)**/
        companion object {
            fun fromInflating(parent: ViewGroup): MyViewHolder {
                val binding: ItemAstroidBinding = ItemAstroidBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return MyViewHolder(binding)
            }
        }
    }


    class Diff_Asteroid : DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean =
            (oldItem.id == newItem.id)

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean =
            (oldItem == newItem)
    }

    class AsteroidListener(val listener : (asteroid :Asteroid) -> Unit)
    {
        fun onClick(asteroid: Asteroid) {
            listener(asteroid)
        }
    }
}


/** (By using kotlin extensions) Binding Adapter which means adding attribute(s) to the View itself and write its related implementation.
And in the XML, use the variable that related to the data-class to bind it with the new attribute there [ @{x} ] **/

//@BindingAdapter("sleepDurationFormatted")
//fun TextView.setSleepDurationFormatted(data :SleepNight?){
//    data?.let {
//        text = convertDurationToFormatted(data.startTimeMilli, data.endtTimeMilli, context.resources)
//        if(data.sleepQuality <= 1 && data.sleepQuality != -1) setTextColor(Color.RED)
//        else setTextColor(Color.BLACK)
//    }
//}
//@BindingAdapter("sleepQualityString")
//fun TextView.setSleepQualityString(data :SleepNight?){
//    data?.let {
//        text = convertNumericQualityToString(data.sleepQuality, context.resources)
//        if(data.sleepQuality <= 1 && data.sleepQuality != -1) setTextColor(Color.RED)
//        else setTextColor(Color.BLACK)
//    }
//}
//@BindingAdapter("sleepImage")
//fun ImageView.setSleepImage(data :SleepNight?){
//    data?.let {
//        setImageResource(
//            when (data.sleepQuality) {
//                0 -> R.drawable.ic_sleep_0
//                1 -> R.drawable.ic_sleep_1
//                2 -> R.drawable.ic_sleep_2
//                3 -> R.drawable.ic_sleep_3
//                4 -> R.drawable.ic_sleep_4
//                5 -> R.drawable.ic_sleep_5
//                else -> R.drawable.ic_launcher_sleep_tracker_background
//            })
//    }
//}