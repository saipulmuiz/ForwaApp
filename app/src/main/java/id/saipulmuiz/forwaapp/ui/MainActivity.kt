package id.saipulmuiz.forwaapp.ui

import android.os.Bundle
import android.os.Handler
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.ViewBinding
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.saipulmuiz.forwaapp.R
import id.saipulmuiz.forwaapp.data.model.ViewPlaceholder
import id.saipulmuiz.forwaapp.databinding.*
import id.saipulmuiz.forwaapp.ui.base.BaseActivity
import id.saipulmuiz.forwaapp.ui.dashboard.DashboardFragment
import id.saipulmuiz.forwaapp.ui.event.EventFragment
import id.saipulmuiz.forwaapp.ui.members.member.MemberFragment
import id.saipulmuiz.forwaapp.ui.notifications.NotificationsFragment
import id.saipulmuiz.forwaapp.util.show
import kotlinx.android.synthetic.main.activity_main.*

// MainActivity implements DaggerAppCompatActivity
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    lateinit var mDialog: SweetAlertDialog

    override var getLayoutId: Int = R.layout.activity_main
    override var getViewModel: Class<MainViewModel> = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set mDialog
        mDialog = SweetAlertDialog(this).apply {
            setCancelable(false)
        }

        setToolbar()
        setUpNavigation()
        // setBottomNavigation()
        // Handler().postDelayed({
        //     nav_view.show()
        // }, 3000)
    }

    // Function : for set navigation controller
    private fun setUpNavigation() {
        val navController = findNavController(R.id.main_nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
        nav_view.setupWithNavController(navController)
    }

    private fun setBottomNavigation() {
        nav_view.setOnNavigationItemSelectedListener(onBottomNavigationListener)
    }

    private val onBottomNavListener = BottomNavigationView.OnNavigationItemSelectedListener { i ->
        var selectedFr: Fragment = DashboardFragment()

        when (i.itemId) {
            R.id.dashboardFragment -> {
                selectedFr = DashboardFragment()
            }
            R.id.memberFragment -> {
                selectedFr = MemberFragment()
                nav_view.getOrCreateBadge(R.id.memberFragment).apply {
                    number = 0
                    isVisible = false
                }
            }
            R.id.eventFragment -> {
                selectedFr = EventFragment()
            }
            R.id.profileFragment -> {
                selectedFr = NotificationsFragment()
            }
        }
        val fr = supportFragmentManager.beginTransaction()
        fr.replace(R.id.main_nav_host_fragment, selectedFr)
        fr.addToBackStack(null).commit()
        true
    }

    private val onBottomNavigationListener = BottomNavigationView.OnNavigationItemSelectedListener { i ->
        loadFragment(DashboardFragment())
        when (i.itemId) {
            R.id.dashboardFragment -> {
                loadFragment(DashboardFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.memberFragment -> {
                loadFragment(MemberFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.eventFragment -> {
                loadFragment(EventFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.profileFragment -> {
                loadFragment(NotificationsFragment())
                return@OnNavigationItemSelectedListener true
            }
            else -> {
                return@OnNavigationItemSelectedListener false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().also { fragmentTransaction ->
            fragmentTransaction.replace(R.id.main_nav_host_fragment, fragment)
            fragmentTransaction.commit()
        }
    }

    // Function : for set toolbar
    private fun setToolbar() {
        mViewBinding.apply {
            setSupportActionBar(mainToolbar)
            mainToolbarTittle.text = getString(R.string.app_name)
        }

        supportActionBar?.setDisplayShowTitleEnabled(false)
    }


    // Function : for change tittle toolbar
    fun changeToolbarTitle(title: String) {
        mViewBinding.mainToolbarTittle.text = title
    }

    // Function : for set content placeholder view
    fun setContentPlaceholder(condition: Int, binding: ViewBinding) {
        val data = ViewPlaceholder()

        data.apply {
            when (condition) {
                1 -> {
                    // View All when data is success load
                    show = false
                }
                2 -> {
                    // View All when data is error load
                    show = true
                    image = R.drawable.il_home_search_error
                    tittle = R.string.placeholder_error_tittle
                    message = R.string.placeholder_error_message
                }
                3 -> {
                    // View Home when waiting to search
                    show = true
                    image = R.drawable.il_home_search
                    tittle = R.string.placeholder_waiting_recent_tittle
                    message = R.string.placeholder_waiting_recent_message
                }
                4 -> {
                    // View Home when data user is not found
                    show = true
                    image = R.drawable.il_home_search_not_found
                    tittle = R.string.placeholder_error_tittle
                    message = R.string.placeholder_not_found_message
                }
                5 -> {
                    // View Profile when data followers is null
                    show = true
                    image = R.drawable.il_home_search_not_found
                    tittle = R.string.placeholder_error_tittle
                    message = R.string.placeholder_not_found_followers_message
                }
                6 -> {
                    // View Profile when data following is null
                    show = true
                    image = R.drawable.il_home_search_not_found
                    tittle = R.string.placeholder_error_tittle
                    message = R.string.placeholder_not_found_following_message
                }
                7 -> {
                    // View Favorite when data is null
                    show = true
                    image = R.drawable.il_home_search_not_found
                    tittle = R.string.placeholder_error_tittle
                    message = R.string.placeholder_not_found_favorite_message
                }
            }
        }

        when (binding) {
            is FragmentMemberBinding -> {
                binding.placeholder = data
            }
            is FragmentMemberDetailBinding -> {
                binding.placeholder = data
            }
            is FragmentEventBinding -> {
                binding.placeholder = data
            }
            is FragmentEventDetailBinding -> {
                binding.placeholder = data
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean =
        findNavController(R.id.main_nav_host_fragment).navigateUp()

    override fun onBackPressed() {
        supportFragmentManager.apply {
            if ((findFragmentById(R.id.main_nav_host_fragment)?.childFragmentManager?.backStackEntryCount) ?: 0 > 1)
                super.onBackPressed()
            else
                finish()
        }
    }
}
