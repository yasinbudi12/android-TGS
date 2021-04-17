package id.conversion.app.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.activity.addCallback
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.android.support.DaggerFragment
import id.conversion.app.R
import id.conversion.app.constant.Nav
import id.conversion.ext.view.backgroundColor
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel, VDB : ViewDataBinding>(private val viewModelClass: Class<VM>) : DaggerFragment() {

    @Inject
    lateinit var mFactory: ViewModelProvider.Factory

    lateinit var viewModel: VM
    lateinit var binding: VDB

    private val appCompatActivity by lazy { activity as AppCompatActivity }

    val navController: NavController by lazy {
        Navigation.findNavController(requireActivity(), navResources())
    }

    protected var inProcess: Boolean = false

    protected var isLoggedIn: Boolean = false
    protected var isLoggedOut: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResources(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupWindowBackground()
        setupStatusBar()

        setupViewModel()
        setupArguments()

        setupAdapter()
        setupViewPager()

        setupListener()
        setupObserver()

        setupBackPressed()
        setupAnimation()

        onViewCreated()
        initAPI()
    }

    private fun setupStatusBar() {
        appCompatActivity.window.apply {
            if (fullStatusBar()) {
                setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            } else {
                clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, mFactory).get(viewModelClass)
    }

    private fun setupBackPressed() {
        activity?.run activity@{
            onBackPressedDispatcher.addCallback(this) {
                this@BaseFragment.onBackPressed()
            }
        }
    }

    private fun setupWindowBackground() {
        appCompatActivity.run {
            if (windowBackground() == 0) return
            window.decorView.backgroundColor(windowBackground())
        }
    }

    private fun setupAnimation() {
        if (!animateLayout()) return
        binding.root.run {
            animation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_right)
            animate()
        }
    }

    protected abstract fun onViewCreated()

    protected open fun setupArguments() {}
    protected open fun setupViewPager() {}
    protected open fun setupAdapter() {}
    protected open fun setupListener() {}
    protected open fun setupObserver() {}

    protected open fun initAPI() {}

    @ColorRes
    protected open fun windowBackground(): Int = 0
    protected open fun navResources(): Int = Nav.Conversion

    protected open fun fullStatusBar(): Boolean = false
    protected open fun animateLayout(): Boolean = false

    protected fun setupToolbar(toolbar: Toolbar) {
        appCompatActivity.run {
            setSupportActionBar(toolbar)

            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            toolbar.setNavigationOnClickListener { onBackPressed() }
        }
    }

    protected open fun onBackPressed() {
        when {
            inProcess -> return
            isLoggedIn || isLoggedOut -> finish()
            else -> try {
                navController.popBackStack()
            } catch (e: Exception) {
                return
            }
        }
    }

    fun finish() {
        activity?.finish()
    }

    @LayoutRes
    protected abstract fun layoutResources(): Int

}