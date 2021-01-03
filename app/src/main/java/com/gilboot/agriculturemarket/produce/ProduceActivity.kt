package com.gilboot.agriculturemarket.produce

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.gilboot.agriculturemarket.R
import com.gilboot.agriculturemarket.databinding.ActivityProduceBinding
import com.gilboot.agriculturemarket.longSnackbar
import com.gilboot.agriculturemarket.repository

/**
 * Activity responsible for registering new users.
 */
class ProduceActivity : AppCompatActivity() {
    val produceViewModel: ProduceViewModel by viewModels {
        ProduceViewModelFactory(repository)
    }

    lateinit var binding: ActivityProduceBinding
    private lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_produce
            )
        // set fragments that will be treated as home fragments
        // fragments that are not part of the appBarConfiguration will have a back icon
        appBarConfiguration = AppBarConfiguration(setOf(R.id.produceListFragment))
        navController = findNavController(R.id.myNavHostFragment)

        setObservers()
    }

    // Allow navigation to previous fragments using up arrow in actionbar
    // AppbarConfiguration provides top level destinations
    override fun onSupportNavigateUp() =
        navController.navigateUp(appBarConfiguration)

}


/**
 * Observe snack message
 */
fun ProduceActivity.observeSnackMessage() {
    produceViewModel.snackMessageLiveData.observe(this, Observer {
        it?.let { event ->
            event.getContentIfNotHandledOrReturnNull()?.let { msg ->
                longSnackbar(msg)
            }
        }
    })
}


/**
 * call observer functions we have
 */
fun ProduceActivity.setObservers() {
    observeSnackMessage()
}