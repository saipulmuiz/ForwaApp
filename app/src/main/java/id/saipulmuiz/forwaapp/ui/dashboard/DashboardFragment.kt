package id.saipulmuiz.forwaapp.ui.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.saipulmuiz.forwaapp.R
import id.saipulmuiz.forwaapp.databinding.FragmentDashboardBinding
import id.saipulmuiz.forwaapp.databinding.FragmentMemberBinding
import id.saipulmuiz.forwaapp.ui.base.BaseFragment
import id.saipulmuiz.forwaapp.ui.members.member.MemberFragmentDirections
import id.saipulmuiz.forwaapp.ui.members.member.MemberViewModel
import id.saipulmuiz.forwaapp.util.SP_KEY_REMINDER
import id.saipulmuiz.forwaapp.util.changeNavigation
import id.saipulmuiz.forwaapp.util.show
import kotlinx.android.synthetic.main.activity_main.*

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