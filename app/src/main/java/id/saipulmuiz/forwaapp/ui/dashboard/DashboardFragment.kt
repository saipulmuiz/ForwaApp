package id.saipulmuiz.forwaapp.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import id.saipulmuiz.forwaapp.R
import id.saipulmuiz.forwaapp.databinding.FragmentDashboardBinding
import id.saipulmuiz.forwaapp.ui.base.BaseFragment
import id.saipulmuiz.forwaapp.util.changeNavigation

class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {

    override var getLayoutId: Int = R.layout.fragment_dashboard
    override var getViewModel: Class<DashboardViewModel> = DashboardViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Dashboard")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configure ViewBinding
        mViewBinding.apply {

            // Inflate options menu & set optionsClickListener
            dashboardToolbar.apply {
                inflateMenu(R.menu.main_menu)
                menu.removeItem(R.id.action_home_favorite)
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.action_home_setting -> {
                            val action =
                                DashboardFragmentDirections.actionDashboardFragmentToSettingFragment()
                            view.changeNavigation(action)
                        }
                        R.id.action_home_notif -> {
                            val action =
                                DashboardFragmentDirections.actionDashboardFragmentToNotificationsFragment()
                            view.changeNavigation(action)
                        }
                    }
                    false
                }
            }
        }
    }
}