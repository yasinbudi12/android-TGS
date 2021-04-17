package id.conversion.app.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.android.support.DaggerAppCompatActivity
import id.conversion.app.constant.Nav
import id.conversion.ext.activity.adjustFontScale
import javax.inject.Inject

abstract class BaseActivity<VM : ViewModel, VDB : ViewDataBinding>(private val viewModelClass: Class<VM>) : DaggerAppCompatActivity() {

    @Inject
    lateinit var mFactory: ViewModelProvider.Factory

    lateinit var viewModel: VM
    lateinit var binding: VDB

    val navController: NavController by lazy {
        Navigation.findNavController(this@BaseActivity, navResources())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adjustFontScale(resources.configuration)

        setupViewModel()
        setupArguments()

        attachViewDataBinding()
        setupStatusBar()

        setupViewPager()
        setupAdapter()

        setupListener()
        setupObserver()

        onViewCreated()
        initAPI()
    }

    private fun setupStatusBar() {
        window.apply {
            if (fullStatusBar()) {
                setFlags(android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            } else {
                clearFlags(android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, mFactory).get(viewModelClass)
    }

    private fun attachViewDataBinding() {
        DataBindingUtil.setContentView<VDB>(this@BaseActivity, layoutResources()).apply {
            setFinishOnTouchOutside(false)
            binding = this
        }
    }

    protected abstract fun onViewCreated()

    protected open fun setupArguments() {}
    protected open fun setupAdapter() {}
    protected open fun setupViewPager() {}

    protected open fun setupListener() {}
    protected open fun setupObserver() {}

    protected open fun initAPI() {}

    protected open fun fullStatusBar(): Boolean = false
    protected open fun navResources(): Int = Nav.Conversion

    protected fun setupToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    @LayoutRes
    protected abstract fun layoutResources(): Int

}