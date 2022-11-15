package com.udacity.asteroidradar.screens.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    val viewModel: VM_Main by lazy {
        ViewModelProvider(this, VM_Main.Factory(requireActivity().application))[VM_Main::class.java]
    }
    lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater)

        binding.x = viewModel
        binding.lifecycleOwner = this

        binding.asteroidRecycler.adapter = RV_Adapter(RV_Adapter.AsteroidListener {
            viewModel.goDetails(it)
        })
        binding.asteroidRecycler.layoutManager?.scrollToPosition(0)

        viewModel.go_with_details.observe(viewLifecycleOwner, Observer {
            Log.w("hhhh", "went")
            it?.let {
                this.findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
                viewModel.went()
            }
        })

//        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**Menu Setup**/
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_overflow_menu, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.show_week_menu -> {
                        viewModel.weekSelected()
                        true
                    }
                    R.id.show_today_menu -> {
                        viewModel.todaySelected()
                        true
                    }
                    R.id.show_save_menu -> {
                        viewModel.allSelected()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


    /*class ExampleFragment : Fragment(R.layout.fragment_detail) {

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            val menuHost: MenuHost = requireActivity()
            menuHost.addMenuProvider(object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    // Add menu items here
                    menuInflater.inflate(R.menu.main_overflow_menu, menu)
                }
                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    // Handle the menu selection
                    return when (menuItem.itemId) {
                        R.id.show_week_menu -> {

                            true
                        }
                        R.id.show_today_menu -> {
                            // loadTasks(true)
                            true
                        }
                        R.id.show_save_menu -> {
                            // loadTasks(true)
                            true
                        }
                        else -> false
                    }
                }
            }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        }

    }*/
}

/*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.asteroidRecycler.adapter = RV_Adapter()
}

override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.main_overflow_menu, menu)
    super.onCreateOptionsMenu(menu, inflater)
}

override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return true
}*/
//setHasOptionsMenu(true)