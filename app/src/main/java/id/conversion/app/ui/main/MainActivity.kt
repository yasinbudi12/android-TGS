package id.conversion.app.ui.main

import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import id.conversion.app.R
import id.conversion.app.base.BaseActivity
import id.conversion.app.databinding.ActivityMainBinding
import id.conversion.ext.common.launchDelayedFunction

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class.java) {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onViewCreated() {
        binding.run {
            lifecycleOwner = this@MainActivity
            setSupportActionBar(binding.toolbar)
        }

        launchDelayedFunction { setupAppBar() }
    }

    override fun navResources(): Int {
        return R.id.nav_host_conversion
    }

    override fun layoutResources(): Int {
        return R.layout.activity_main
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        val currentDestinationId = navController.currentDestination?.id
        if (currentDestinationId != null && currentDestinationId == R.id.nav_weight) finish()
        else super.onBackPressed()
    }

    private fun setupAppBar() {
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_weight, R.id.nav_distance, R.id.nav_volume, R.id.nav_time, R.id.nav_currency), binding.drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

}