package com.app.weather.presentation.util

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
    this function is due to a bug with FragmentContainerView our dear friends at google caused
    and not fixed yet
    look at officially accepted solution here for more info
    https://stackoverflow.com/questions/50502269/illegalstateexception-link-does-not-have-a-navcontroller-set
 */

//fun AppCompatActivity.findNavControllerFromNavHost(@IdRes id : Int) =
//    (supportFragmentManager.findFragmentById(id) as NavHostFragment).navController
//
//fun AppCompatActivity.initBottomNav(binding: ActivityHomeBinding) {
//    binding.bottomNavigationView.inflateMenu(R.menu.menu_bottom_nav)
//    NavigationUI.setupWithNavController(
//        binding.bottomNavigationView, findNavControllerFromNavHost(R.id.navHostFragment)
//    )
//}


fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)


inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }